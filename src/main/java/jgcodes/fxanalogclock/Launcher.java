package jgcodes.fxanalogclock;

import jgcodes.fxanalogclock.scene.ResizableCanvas;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.net.URL;

public class Launcher extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  
  public static URL getResource(String name) {
    return Thread.currentThread().getContextClassLoader().getResource(name);
  }
  
  @Override
  public void start(Stage stage) {
    StackPane sp = new StackPane();
    Canvas canvas = new ResizableCanvas(300, 300);
    canvas.widthProperty().bind(Bindings.min(sp.heightProperty(), sp.widthProperty()));
    canvas.heightProperty().bind(canvas.widthProperty());
    
    sp.getChildren().add(canvas);
  
    ClockDrawer clockDrawer = new ClockDrawer(canvas);
    clockDrawer.start();
    
    Scene scene = new Scene(sp, 300, 300);
    stage.setScene(scene);
    
    stage.getIcons().addAll(
      new Image(getResource("clock-big.png").toString()),
      new Image(getResource("clock-64.png").toString()),
      new Image(getResource("clock-32.png").toString()),
      new Image(getResource("clock-16.png").toString())
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
