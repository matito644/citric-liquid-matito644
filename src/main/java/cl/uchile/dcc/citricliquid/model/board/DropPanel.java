package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a drop panel in the board of the game.
 */
public class DropPanel extends Panel {

  /**
   * Creates a Drop panel.
   */
  public DropPanel() {
    super(PanelType.DROP);
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the player's norma level.
   */
  @Override
  public void activatedBy(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }
}