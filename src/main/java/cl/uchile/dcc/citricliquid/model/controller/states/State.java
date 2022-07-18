package cl.uchile.dcc.citricliquid.model.controller.states;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.exceptions.UnsupportedStateOperationException;
import org.jetbrains.annotations.NotNull;

/**
 * To handle the states of the game.
 */
public class State {
  protected final Controller context;

  /**
   * Constructor.
   *
   * @param context the context.
   */
  public State(@NotNull Controller context) {
    this.context = context;
    context.setActualState(this);
  }

  /**
   * If we want to start the turn.
   *
   * @throws UnsupportedStateOperationException in case we cannot do that.
   */
  public void itIsYourTurn() throws UnsupportedStateOperationException {
    throw new UnsupportedStateOperationException("You cannot begin your turn now.");
  }
}
