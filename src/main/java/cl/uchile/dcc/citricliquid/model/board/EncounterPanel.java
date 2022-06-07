package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.WildUnit;
import cl.uchile.dcc.citricliquid.model.WildUnitType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.jetbrains.annotations.NotNull;


/**
 * Class that represents an encounter panel in the board of the game.
 */
public class EncounterPanel extends Panel {
  private final ArrayList<WildUnitType> rivalsWild = new ArrayList<>();
  private WildUnit wildUniInThisPanel;

  /**
   * Creates an Encounter panel.
   */
  public EncounterPanel() {
    super(PanelType.ENCOUNTER);
    this.setWildUniInThisPanel();
  }

  /**
   * Return the wild unit of this panel.
   */
  public WildUnit getWildUniInThisPanel() {
    return wildUniInThisPanel;
  }

  /**
   * Set the wild unit of this panel.
   */
  public void setWildUniInThisPanel() {
    Collections.addAll(rivalsWild, WildUnitType.values());
    int numberOfUnits = rivalsWild.size();
    int randomPosition = new Random().nextInt(numberOfUnits);
    this.wildUniInThisPanel = createWild(rivalsWild.get(randomPosition));
  }

  /**
   * Creates a new wild unit.
   *
   * @param unit can be one of the three types.
   */
  public WildUnit createWild(WildUnitType unit) {
    return switch (unit) {
      case CHICKEN -> new WildUnit("Chicken", 3, -1, -1, 1);
      case ROBO -> new WildUnit("Robo Ball", 3, -1, 1, -1);
      case SEAGULL -> new WildUnit("Seagull", 3, 1, -1, -1);
    };
  }

  /**
   * Executes a short battle between the wild unit of this panel and player.
   *
   * @param player will be the one that attacks first.
   */
  public void shortBattleVsWild(@NotNull Player player) {
    player.attack(wildUniInThisPanel);
    if (wildUniInThisPanel.isAlive()) {
      wildUniInThisPanel.attack(player);
    }
  }
}
