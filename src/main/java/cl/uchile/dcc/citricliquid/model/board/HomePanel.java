package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a home panel in the board of the game.
 */
public class HomePanel extends Panel {
  public HomePanel() {
    super(PanelType.HOME);
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
}
