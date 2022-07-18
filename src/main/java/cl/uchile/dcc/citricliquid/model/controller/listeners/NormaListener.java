package cl.uchile.dcc.citricliquid.model.controller.listeners;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import java.beans.PropertyChangeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * To check the norma.
 */
public class NormaListener implements Ilisteners {
  private final Controller context;

  /**
   * It is necessary a context.
   *
   * @param context the context.
   */
  public NormaListener(Controller context) {
    this.context = context;
  }

  @Override
  public void propertyChange(@NotNull PropertyChangeEvent evt) {
    context.someoneJustWon();
  }
}
