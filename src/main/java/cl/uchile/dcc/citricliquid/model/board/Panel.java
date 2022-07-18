package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.units.Player;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a neutral panel in the board of the game.
 */
public class Panel implements Ipanel {
  private final PanelType type;
  private final Set<Ipanel> nextPanels = new HashSet<>();
  private final Set<Player> playersHere = new HashSet<>();

  /**
   * Creates a panel if no parameters were given.
   */
  public Panel() {
    this(PanelType.NEUTRAL);
  }

  /**
   * Creates a new panel.
   *
   * @param type the type of the panel.
   */
  public Panel(PanelType type) {
    this.type = type;
  }


  @Override
  public Set<Ipanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  @Override
  public void addNextPanel(final Ipanel panel) {
    nextPanels.add(panel);
  }

  @Override
  public PanelType getType() {
    return type;
  }

  @Override
  public Set<Player> getPlayersHere() {
    return Set.copyOf(playersHere);
  }

  @Override
  public void addPlayer(Player player) {
    playersHere.add(player);
  }

  @Override
  public void removePlayer(Player player) {
    playersHere.remove(player);
  }

  /**
   * Does nothing because this is a neutral panel.
   *
   * @param player the one who is on the panel.
   */
  @Override
  public void activatedBy(final @NotNull Player player) {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Panel panel)) {
      return false;
    }
    return type == panel.type && Objects.equals(nextPanels, panel.nextPanels);
  }
}
