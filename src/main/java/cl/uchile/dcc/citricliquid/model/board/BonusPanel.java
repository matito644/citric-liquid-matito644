package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a bonus panel in the board of the game.
 */
public class BonusPanel extends Panel {

  /**
   * Creates a Bonus panel.
   */
  public BonusPanel() {
    super(PanelType.BONUS);
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   *
   * @param player the one who activated the effect.
   */
  public void activatedBy(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}
