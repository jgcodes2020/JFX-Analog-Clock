package jgcodes.fxanalogclock;

import jgcodes.fxanalogclock.scene.ClockDrawer;
import jgcodes.fxanalogclock.scene.ClockView;
import jgcodes.fxanalogclock.scene.ResizableCanvas;
import jgcodes.fxanalogclock.util.Resources;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Launcher extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  
  @Override
  public void start(Stage stage) throws Exception {
    ClockView clockView = new ClockView(false);
    Scene scene = new Scene(clockView, 300, 300);
    stage.setScene(scene);
    
    stage.getIcons().addAll(
      new Image(Resources.loadFromContext("clock-big.png").toString()),
      new Image(Resources.loadFromContext("clock-64.png").toString()),
      new Image(Resources.loadFromContext("clock-32.png").toString()),
      new Image(Resources.loadFromContext("clock-16.png").toString())
    );
    
    stage.setTitle("JavaFX Analog Clock");
    stage.show();
    stage.setFullScreenExitHint("Hit F11 or Escape to exit fullscreen");
    scene.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case F11 -> stage.setFullScreen(!stage.isFullScreen());
        case ESCAPE -> stage.setFullScreen(false);
      }
    });
  }
}
