package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.controller.states.BeginningState;
import cl.uchile.dcc.citricliquid.model.controller.states.State;
import cl.uchile.dcc.citricliquid.model.exceptions.UnsupportedStateOperationException;
import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.Player;
import cl.uchile.dcc.citricliquid.model.units.WildUnit;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
  private final Controller controller = new Controller();
  private Player holmes;
  private Player watson;
  private Player pame;
  private Player brayita;
  private WildUnit seagull;
  private BossUnit castle;
  private Ipanel neutral;
  private Ipanel bonus;
  private Ipanel drop;
  private Ipanel encounter;
  private Ipanel boss;
  private Ipanel homeH;
  private Ipanel homeW;
  private Ipanel homeP;
  private Ipanel homeB;
  private Set<Ipanel> board;

  @BeforeEach
  public void setUp() {
    Pair<Player, Ipanel> parH = controller.createPlayer("holmes", 5, 5, 5, 5);
    Pair<Player, Ipanel> parW = controller.createPlayer("watson", 4, 4, 4, 4);
    Pair<Player, Ipanel> parP = controller.createPlayer("pame", 4, 4, 4, 4);
    Pair<Player, Ipanel> parB = controller.createPlayer("brayita", 4, 4, 4, 4);
    holmes = parH.getKey();
    homeH = parH.getValue();
    watson = parW.getKey();
    homeW = parW.getValue();
    pame = parP.getKey();
    homeP = parP.getValue();
    brayita = parB.getKey();
    homeB = parB.getValue();
    seagull = controller.createWild("Seagull", 3, 1, -1, -1);
    castle = controller.createBoss("Flying Castle", 10, 2, 1, -3);
    neutral = controller.createPanel();
    bonus = controller.createBonusPanel();
    drop = controller.createDropPanel();
    encounter = controller.createEncounterPanel();
    boss = controller.createBossPanel();
    board = controller.getAllPanels();
  }

  @Test
  public void constructorTest() {
    Player expectedHolmes = new Player("holmes", 5, 5, 5, 5);
    Player expectedWatson = new Player("watson", 4, 4, 4, 4);
    Player expectedPame = new Player("pame", 4, 4, 4, 4);
    Player expectedBrayita = new Player("brayita", 4, 4, 4, 4);
    WildUnit expectedSeagull = new WildUnit("Seagull", 3, 1, -1, -1);
    BossUnit expectedCastle = new BossUnit("Flying Castle", 10, 2, 1, -3);
    Ipanel expectedNeutral = new Panel();
    Ipanel expectedBonus = new BonusPanel();
    Ipanel expectedDrop = new DropPanel();
    Ipanel expectedEncounter = new EncounterPanel();
    Ipanel expectedBoss = new BossPanel();
    Ipanel expectedHomeH = new HomePanel(expectedHolmes);
    Ipanel expectedHomeW = new HomePanel(expectedWatson);
    Ipanel expectedHomeP = new HomePanel(expectedPame);
    Ipanel expectedHomeB = new HomePanel(expectedBrayita);

    assertEquals(expectedHolmes, holmes);
    assertEquals(expectedWatson, watson);
    assertEquals(expectedPame, pame);
    assertEquals(expectedBrayita, brayita);
    assertEquals(expectedSeagull, seagull);
    assertEquals(expectedCastle, castle);
    assertEquals(expectedNeutral, neutral);
    assertEquals(expectedBonus, bonus);
    assertEquals(expectedDrop, drop);
    assertEquals(expectedEncounter, encounter);
    assertEquals(expectedBoss, boss);
    assertEquals(expectedHomeH, homeH);
    assertEquals(expectedHomeW, homeW);
    assertEquals(expectedHomeP, homeP);
    assertEquals(expectedHomeB, homeB);
  }

  @Test
  public void getBoardAndGetNextPanelsTest() {
    assertEquals(Set.of(neutral, bonus, drop, encounter, boss, homeH, homeW, homeP, homeB), board);

    assertTrue(bonus.getNextPanels().isEmpty());
    assertTrue(encounter.getNextPanels().isEmpty());
    assertTrue(homeH.getNextPanels().isEmpty());

    controller.setNextPanel(homeH, drop);
    controller.setNextPanel(encounter, neutral);
    controller.setNextPanel(encounter, homeB);
    controller.setNextPanel(bonus, homeP);
    controller.setNextPanel(bonus, boss);
    controller.setNextPanel(bonus, homeW);

    assertEquals(Set.of(drop), controller.getNextPanels(homeH));
    assertEquals(Set.of(neutral, homeB), controller.getNextPanels(encounter));
    assertEquals(Set.of(homeP, boss, homeW), controller.getNextPanels(bonus));
  }

  @Test
  public void turnTest() {
    controller.setOwnerTurn();
    assertEquals("holmes", controller.getOwnerTurn().getName());
    assertEquals(1, controller.getTurn());

    controller.nextTurn();
    assertEquals("watson", controller.getOwnerTurn().getName());
    assertEquals(2, controller.getTurn());

    controller.nextTurn();
    assertEquals("pame", controller.getOwnerTurn().getName());
    assertEquals(3, controller.getTurn());

    controller.nextTurn();
    assertEquals("brayita", controller.getOwnerTurn().getName());
    assertEquals(4, controller.getTurn());

    controller.nextTurn();
    assertEquals("holmes", controller.getOwnerTurn().getName());
    assertEquals(1, controller.getTurn());
    assertEquals(2, controller.getChapter());
  }

  @Test
  public void gameOverTest() {
    controller.setOwnerTurn();
    for (int i=0; i<6; i++) {
      controller.getOwnerTurn().normaClear();
    }
    assertTrue(controller.gameOver());
  }

  @Test
  public void stateTest() {
    State state = new State(controller);
    assertEquals(State.class, state.getClass());
    controller.setOwnerTurn();
    assertEquals(0, controller.getOwnerTurn().getStars());
    BeginningState initState = new BeginningState(controller);
    initState.itIsYourTurn();
    assertEquals(1, controller.getOwnerTurn().getStars());
  }

  @Test
  public void unsupportedStateTest() {
    State state = new State(controller);
    try {
      state.itIsYourTurn();
    } catch (UnsupportedStateOperationException e) {
      e.printStackTrace();
    }
  }
}
