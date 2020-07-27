package jgcodes.fxanalogclock;

import jgcodes.fxanalogclock.util.Range;

import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.time.LocalTime;

import static java.lang.Math.PI;

public class ClockDrawer extends AnimationTimer {
  public static final Stop[] borderGradient = new Stop[] {
    new Stop(0, Color.CORNFLOWERBLUE),
    new Stop(1, Color.CORNFLOWERBLUE.darker())
  };
  
  private final GraphicsContext gc;
  
  public ClockDrawer(Canvas canvas) {
    gc = canvas.getGraphicsContext2D();
    gc.setLineCap(StrokeLineCap.ROUND);
    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);
  }
  
  @Override
  public void handle(long animTime) {
    final double width = gc.getCanvas().getWidth();
    final double height = gc.getCanvas().getHeight();
    
    final double centerX = width / 2;
    final double centerY = height / 2;
    
    gc.clearRect(0, 0, width, height);
    
    gc.setFill(new LinearGradient(0, 0, width, height, false, CycleMethod.NO_CYCLE,
      borderGradient));
    gc.fillOval(0, 0, width, height);
    gc.setFill(Color.WHITE);
    gc.fillOval(width / 15, height / 15, width * 13 / 15, height * 13 / 15);
    
    gc.setStroke(Color.web("#333"));
    gc.setLineWidth(width / 100);
    for (int i: new Range(0, 59)) {
      final double x = (width * 2 / 5) * Math.cos(i * PI / 30) + centerX;
      final double y = -(height * 2 / 5) * Math.sin(i * PI / 30) + centerY;
      final double x2 = (width * 23 / 60) * Math.cos(i * PI / 30) + centerX;
      final double y2 = -(height * 23 / 60) * Math.sin(i * PI / 30) + centerY;
      
      if (i % 5 == 0) gc.strokeLine(x, y, x2, y2);
      else gc.strokeLine(x, y, x, y);
    }
    gc.setFont(new Font("Arial", width / 15));
    gc.setFill(Color.web("#333"));
    gc.setStroke(Color.web("#000", 0));
    for (int i: new Range(1, 12)) {
      final double sin = Math.sin(i * PI / 6 + PI / 2);
      final double cos = Math.cos(i * PI / 6 + PI / 2);
      switch (i) {
        case 3, 9 -> gc.fillText(Integer.toString(i),
          -(width * 17 / 50) * cos + centerX, -(height * 17 / 50) * sin + centerY);
        default -> gc.fillText(Integer.toString(i),
          -(width / 3) * cos + centerX, -(height / 3) * sin + centerY);
      }
    }
    
    LocalTime time = LocalTime.now();
    
    double secondAngle = time.getSecond() * PI / 30;
    double minuteAngle = time.getMinute() * PI / 30 + secondAngle / 60;
    double hourAngle = (time.getHour() % 12) * PI / 6 + minuteAngle / 12;
    
    secondAngle = -(secondAngle - PI / 2);
    minuteAngle = -(minuteAngle - PI / 2);
    hourAngle = -(hourAngle - PI / 2);
    
    gc.setLineWidth(width * 7 / 500);
    gc.setStroke(Color.BLACK);
    gc.strokeLine(centerX, centerY, (width * 7 / 30) * Math.cos(hourAngle) + centerX, -(width * 7 / 30) * Math.sin(hourAngle) + centerY);
    
    gc.setLineWidth(width * 7 / 600);
    gc.strokeLine(centerX, centerY, (width * 11 / 30) * Math.cos(minuteAngle) + centerX, -(height * 11 / 30) * Math.sin(minuteAngle) + centerY);
    
    gc.setLineWidth(width / 300);
    gc.setStroke(Color.RED);
    final double secondHandX1 = -(width / 30) * Math.cos(secondAngle) + centerX;
    gc.strokeLine(secondHandX1, (height / 30) * Math.sin(secondAngle) + centerY,
      (width * 19 / 50) * Math.cos(secondAngle) + centerX, -(height * 19 / 50) * Math.sin(secondAngle) + centerY);
    gc.setLineWidth(width / 100);
    gc.strokeLine(-(width * 2 / 15) * Math.cos(secondAngle) + centerX, (width * 2 / 15) * Math.sin(secondAngle) + centerY,
      secondHandX1, (width / 30) * Math.sin(secondAngle) + centerY);
    
    gc.setFill(Color.BLACK);
    gc.fillOval(width * 71 / 150, height * 71 / 150, width * 4 / 75, height * 4 / 75);
  }
}
