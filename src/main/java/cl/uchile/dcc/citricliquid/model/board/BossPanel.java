package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.BossUnit;
import cl.uchile.dcc.citricliquid.model.units.BossUnitType;
import cl.uchile.dcc.citricliquid.model.units.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a boss panel in the board of the game.
 */
public class BossPanel extends Panel {
  private final ArrayList<BossUnitType> rivalsBoss = new ArrayList<>();
  private BossUnit bossUnitInThisPanel;

  /**
   * Creates a Boss panel.
   */
  public BossPanel() {
    super(PanelType.BOSS);
    this.setBossUniInThisPanel();
  }

  /**
   * Returns the boss unit of this panel.
   */
  public BossUnit getBossUnitInThisPanel() {
    return bossUnitInThisPanel;
  }

  /**
   * Set the boss unit of this panel.
   */
  public void setBossUniInThisPanel() {
    Collections.addAll(rivalsBoss, BossUnitType.values());
    int numberOfUnits = rivalsBoss.size();
    int randomPosition = new Random().nextInt(numberOfUnits);
    this.bossUnitInThisPanel = createBoss(rivalsBoss.get(randomPosition));
  }

  /**
   * Creates a new boss unit.
   *
   * @param unit can be one of the three types of bosses.
   */
  public BossUnit createBoss(BossUnitType unit) {
    return switch (unit) {
      case STORE -> new BossUnit("Store Manager", 8, 3, 2, -1);
      case SHIFU -> new BossUnit("Shifu Robot", 7, 2, 3, -2);
      case CASTLE -> new BossUnit("Flying Castle", 10, 2, 1, -3);
    };
  }

  /**
   * Execute a little battle between player and the boss unit of this panel.
   *
   * @param player will be the one that attacks first.
   */
  public void shortBattleVsBoss(@NotNull Player player) {
    player.attack(bossUnitInThisPanel);
    if (bossUnitInThisPanel.isAlive()) {
      bossUnitInThisPanel.attack(player);
    }
  }
}
