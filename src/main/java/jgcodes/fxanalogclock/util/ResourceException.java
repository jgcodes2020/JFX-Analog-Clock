package jgcodes.fxanalogclock.util;

/**
 * Thrown if there is a problem fetching resources.
 */
public class ResourceException extends Exception {
  public ResourceException() {
    super();
  }
  
  public ResourceException(String message) {
    super(message);
  }
  
  public ResourceException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public ResourceException(Throwable cause) {
    super(cause);
  }
}
