package cl.uchile.dcc.citricliquid.model.exceptions;

/**
 * In case we can't go from a state to another one.
 */
public class UnsupportedStateOperationException extends Exception {
  public UnsupportedStateOperationException(String text) {
    super(text);
  }
}
