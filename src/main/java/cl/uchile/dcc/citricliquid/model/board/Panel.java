package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import java.util.HashSet;
import java.util.Set;


/**
 * Class that represents a neutral panel in the board of the game.
 */
public class Panel {
  private final PanelType type;
  private final Set<Panel> nextPanels = new HashSet<>();
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

  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }

  /**
   * Returns the type of this panel.
   */
  public PanelType getType() {
    return type;
  }
}
