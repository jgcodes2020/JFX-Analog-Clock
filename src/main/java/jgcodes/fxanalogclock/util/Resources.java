package jgcodes.fxanalogclock.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

/**
 * Utility class for getting and loading resources.
 */
public class Resources {
  public static URL loadFromContext(String path) throws ResourceException {
    try {
      return Objects.requireNonNull(
        Thread.currentThread().getContextClassLoader().getResource(path)
      );
    }
    catch (NullPointerException e) {
      throw new ResourceException(String.format("Resource at %1s could not be loaded.", path));
    }
  }
  public static InputStream loadStreamFromContext(String path) throws ResourceException{
    try {
      return Objects.requireNonNull(
        Thread.currentThread().getContextClassLoader().getResourceAsStream(path)
      );
    }
    catch (NullPointerException e) {
      throw new ResourceException(String.format("Resource at %1s could not be loaded.", path));
    }
  }
}
