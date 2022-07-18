package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a home panel in the board of the game.
 */
public class HomePanel extends Panel {
  private final Player owner;

  /**
   * Creates a Home panel with an owner.
   *
   * @param player will be the owner of this panel.
   */
  public HomePanel(Player player) {
    super(PanelType.HOME);
    this.owner = player;
  }

  /**
   * Restores a player's HP in 1.
   */
  public static void applyHealTo(final @NotNull Player player) {
    player.setCurrentHp(player.getCurrentHp() + 1);
  }

  /**
   * Executes the norma check process only taking care of the number of stars.
   *
   * @param player the player we are interested in.
   */
  public void normaCheckStars(final @NotNull Player player) {
    int level = player.getNormaLevel();
    int stars = player.getStars();
    if ((level == 1 && stars >= 10) || (level == 2 && stars >= 30) || (level == 3 && stars >= 70)
            || (level == 4 && stars >= 120) || (level == 5 && stars >= 200)) {
      player.normaClear();
    }
  }

  /**
   * Executes the norma check process but only the number of wins matters.
   *
   * @param player the player we are interested in.
   */
  public void normaCheckWins(final @NotNull Player player) {
    int level = player.getNormaLevel();
    int wins = player.getWins();
    if ((level == 2 && wins >= 2) || (level == 3 && wins >= 5) || (level == 4 && wins >= 9)
        || (level == 5 && wins >= 14)) {
      player.normaClear();
    }
  }

  /**
   * Executes the norma check process.
   *
   * @param player the player we are interested in.
   */
  public void normaCheck(final @NotNull Player player) {
    this.normaCheckStars(player);
    this.normaCheckWins(player);
  }

  /**
   * Returns the owner of this panel.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * Restores the hp and executes the norma check process.
   *
   * @param player the one who activated the effect.
   */
  @Override
  public void activatedBy(final @NotNull Player player) {
    applyHealTo(player);
    normaCheck(player);
  }

  /**
   * Now they can be different because of the owner.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HomePanel homePanel)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    return Objects.equals(owner, homePanel.owner);
  }
}
