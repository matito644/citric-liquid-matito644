package cl.uchile.dcc.citricliquid.model.controller.states;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import org.jetbrains.annotations.NotNull;

/**
 * To handle the start.
 */
public class BeginningState extends State {

  /**
   * Constructor.
   *
   * @param context the context.
   */
  public BeginningState(@NotNull Controller context) {
    super(context);
  }

  /**
   * According to the instructions, the player gets some stars.
   */
  @Override
  public void itIsYourTurn() {
    context.starsPlease();
  }
}
