package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.board.BonusPanel;
import cl.uchile.dcc.citricliquid.model.board.BossPanel;
import cl.uchile.dcc.citricliquid.model.board.DropPanel;
import cl.uchile.dcc.citricliquid.model.board.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.board.HomePanel;
import cl.uchile.dcc.citricliquid.model.board.Ipanel;
import cl.uchile.dcc.citricliquid.model.board.Panel;
import cl.uchile.dcc.citricliquid.model.controller.listeners.NormaListener;
import cl.uchile.dcc.citricliquid.model.controller.states.State;
import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.Player;
import cl.uchile.dcc.citricliquid.model.units.WildUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * Controller of the game.
 */
public class Controller {
  private final Set<Player> players = new HashSet<>();
  private final Set<Ipanel> board = new HashSet<>();
  private final List<Player> nowPlaying = new ArrayList<>();
  private State actualState;
  private Player ownerTurn;
  private int turn = 1;
  private int chapter = 1;
  private int steps;
  private boolean isItOver = false;
  private final NormaListener normaListener = new NormaListener(this);

  /**
   * Constructor.
   */
  public Controller() {
  }

  /**
   * Creates a neutral panel.
   */
  public Panel createPanel() {
    Panel panel = new Panel();
    board.add(panel);
    return panel;
  }

  /**
   * Creates a bonus panel.
   */
  public BonusPanel createBonusPanel() {
    BonusPanel bonusPanel = new BonusPanel();
    board.add(bonusPanel);
    return bonusPanel;
  }

  /**
   * Creates a drop panel.
   */
  public DropPanel createDropPanel() {
    DropPanel dropPanel = new DropPanel();
    board.add(dropPanel);
    return dropPanel;
  }

  /**
   * Creates an encounter panel.
   */
  public EncounterPanel createEncounterPanel() {
    EncounterPanel encounterPanel = new EncounterPanel();
    board.add(encounterPanel);
    return encounterPanel;
  }

  /**
   * Creates a boss panel.
   */
  public BossPanel createBossPanel() {
    BossPanel bossPanel = new BossPanel();
    board.add(bossPanel);
    return bossPanel;
  }

  /**
   * Creates a new player, a home panel and puts the player on the panel.
   *
   * @param name the name of the player.
   * @param hp life points of the player.
   * @param atk atk point of the player.
   * @param def def point of the player.
   * @param evd evd point of the player.
   */
  public Pair<Player, Ipanel> createPlayer(String name, int hp, int atk, int def, int evd) {
    Player newPlayer = new Player(name, hp, atk, def, evd);
    players.add(newPlayer);
    nowPlaying.add(newPlayer);
    HomePanel homePanel = new HomePanel(newPlayer);
    board.add(homePanel);
    homePanel.addPlayer(newPlayer);
    return new Pair<>(newPlayer, homePanel);
  }

  /**
   * Set the neighbour next to a panel.
   *
   * @param panel the panel we want to put a panel next to it.
   * @param otherPanel the neighbour.
   */
  public void setNextPanel(@NotNull Ipanel panel, Ipanel otherPanel) {
    panel.addNextPanel(otherPanel);
  }

  /**
   * Returns a copy of the panels next to the one we are interested.
   *
   * @param panel the one we want to know its neighbours.
   */
  public Set<Ipanel> getNextPanels(@NotNull Ipanel panel) {
    return Set.copyOf(panel.getNextPanels());
  }

  /**
   * Creates a new wild unit.
   *
   * @param name the name of the wild unit.
   * @param hp life points of the wild unit.
   * @param atk atk point of the wild unit.
   * @param def def point of the wild unit.
   * @param evd evd point of the wild unit.
   */
  public WildUnit createWild(String name, int hp, int atk, int def, int evd) {
    return new WildUnit(name, hp, atk, def, evd);
  }

  /**
   * Creates a new boss unit.
   *
   * @param name the name of the boss unit.
   * @param hp life points of the boss unit.
   * @param atk atk point of the boss unit.
   * @param def def point of the boss unit.
   * @param evd evd point of the boss unit.
   */
  public BossUnit createBoss(String name, int hp, int atk, int def, int evd) {
    return new BossUnit(name, hp, atk, def, evd);
  }

  /**
   * Returns a copy of all the panels.
   */
  public Set<Ipanel> getAllPanels() {
    return Set.copyOf(board);
  }

  /**
   * Sets the state of the game.
   *
   * @param state the actual state.
   */
  public void setActualState(State state) {
    this.actualState = state;
  }

  /**
   * Returns the chapter of the game.
   */
  public int getChapter() {
    return chapter;
  }

  /**
   * Returns the turn of the game.
   */
  public int getTurn() {
    return turn;
  }

  /**
   * Increments the turn and the chapter (when it is necessary) and change
   * the owner of the turn.
   */
  public void nextTurn() {
    nowPlaying.add(ownerTurn);
    if (turn == 4) {
      chapter++;
      turn = 1;
    } else {
      turn++;
    }
    nowPlaying.remove(0);
    setOwnerTurn();
  }

  /**
   * Returns the owner of the turn.
   */
  public Player getOwnerTurn() {
    return ownerTurn;
  }

  /**
   * Set the owner of the turn.
   */
  public void setOwnerTurn() {
    this.ownerTurn = nowPlaying.get(0);
    ownerTurn.addNormaListener(normaListener);
  }

  /**
   * If this happens the game is over.
   */
  public void someoneJustWon() {
    if (ownerTurn.getNormaLevel() == 6) {
      this.isItOver = true;
    }
  }

  /**
   * Returns if the game is over.
   */
  public boolean gameOver() {
    return isItOver;
  }

  /**
   * Set the number of moves to do.
   */
  public void setSteps() {
    this.steps = ownerTurn.roll();
  }

  /**
   * At the beginning of each turn the player gets an amount of stars.
   */
  public void starsPlease() {
    ownerTurn.increaseStarsBy((int) (Math.floor(getChapter() * 0.2) + 1));
  }
}
