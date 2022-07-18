package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * Interface that shows the behavior of a panel.
 */
public interface Ipanel {

  /**
   * Returns a copy of the panel's next ones.
   */
  Set<Ipanel> getNextPanels();

  /**
   * Adds a new adjacent panel.
   *
   * @param panel the panel to be added.
   */
  void addNextPanel(Ipanel panel);

  /**
   * Returns the type of the panel.
   */
  PanelType getType();

  /**
   * Returns all the players that are on the panel.
   */
  Set<Player> getPlayersHere();

  /**
   * Adds a new player to the panel.
   *
   * @param player the player to be added.
   */
  void addPlayer(Player player);

  /**
   * Removes a player from the panel.
   *
   * @param player the player to be removed.
   */
  void removePlayer(Player player);

  /**
   * Executes the effect of the panel.
   *
   * @param player the one who is on the panel.
   */
  void activatedBy(final @NotNull Player player);
}
