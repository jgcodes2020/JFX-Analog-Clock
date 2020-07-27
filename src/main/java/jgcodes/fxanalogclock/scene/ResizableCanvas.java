package jgcodes.fxanalogclock.scene;

import javafx.scene.canvas.Canvas;

public class ResizableCanvas extends Canvas {
  public ResizableCanvas(double initialWidth, double initialHeight) {
    super(initialWidth, initialHeight);
  }
  
  @Override
  public double minWidth(double height) {
    return 1.0;
  }
  
  @Override
  public double minHeight(double width) {
    return 1.0;
  }
  
  @Override
  public double maxWidth(double width) {
    return Double.MAX_VALUE;
  }
  
  @Override
  public double maxHeight(double width) {
    return Double.MAX_VALUE;
  }
  
  @Override
  public void resize(double width, double height) {
    this.setWidth(width);
    this.setHeight(height);
  }
}
