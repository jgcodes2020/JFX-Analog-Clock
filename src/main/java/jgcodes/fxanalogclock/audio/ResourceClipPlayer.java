package jgcodes.fxanalogclock.audio;

import jgcodes.fxanalogclock.util.ResourceException;
import jgcodes.fxanalogclock.util.Resources;

import javax.sound.sampled.*;
import java.io.IOException;

public class ResourceClipPlayer {
  private final Clip clip;
  private final LineListener notifyOnFinish = event -> {
    if (event.getType() == LineEvent.Type.STOP) {
      this.notify();
    }
  };
  public ResourceClipPlayer(String path) throws ResourceException, IOException, UnsupportedAudioFileException, LineUnavailableException {
    AudioInputStream inputStream =
      AudioSystem.getAudioInputStream(Resources.loadFromContext(path));
    clip = AudioSystem.getClip();
    clip.open(inputStream);
  }
  public void play() {
    clip.setFramePosition(0);
    clip.start();
  }
  public void playAndWait() {
    clip.setFramePosition(0);
    clip.addLineListener(notifyOnFinish);
    clip.start();
    try {
      this.wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    clip.removeLineListener(notifyOnFinish);
  }
}
