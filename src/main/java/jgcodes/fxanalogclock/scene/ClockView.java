package jgcodes.fxanalogclock.scene;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

/**
 * Displays an analog clock with the option to mute it.
 */
public class ClockView extends StackPane {
  private final ClockDrawer clockDrawer;
  public ClockView(boolean muted) {
    Canvas canvas = new ResizableCanvas(300, 300);
    canvas.widthProperty().bind(Bindings.min(this.widthProperty(), this.heightProperty()));
    canvas.heightProperty().bind(canvas.widthProperty());
    clockDrawer = new ClockDrawer(canvas);
    if (clockDrawer.hasSound()) clockDrawer.setMuted(muted);
    clockDrawer.start();
    
    super.getChildren().add(canvas);
  }
  public ClockView() {
    this(true);
  }
  
  @Override
  public ObservableList<Node> getChildren() {
    return FXCollections.unmodifiableObservableList(super.getChildren());
  }
  
  public void setMuted(boolean muted) {
    clockDrawer.setMuted(muted);
  }
  public boolean isMuted() {
    return clockDrawer.isMuted();
  }
  public boolean hasSound() {
    return clockDrawer.hasSound();
  }
}
