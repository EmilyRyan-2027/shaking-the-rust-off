package test.java;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.OverlayOffsetImage;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import main.java.ShakingTheRustOff;

//tests for Robot class
/**
 * Tests for {@link main.java.Robot}.
 */
public class RobotTests {
  
  ShakingTheRustOff ex;
  
  //initializes the data
  void initData() {
    ex = new ShakingTheRustOff(4, 4);

  }
  
  //tests drawRobot
  @Test
  void drawRobotTest() {
    initData();
    WorldImage base = new RectangleImage(40, 80, OutlineMode.SOLID, new Color(182, 187, 191));
    WorldImage eye = new RectangleImage(10, 16, OutlineMode.SOLID, new Color(252, 238, 172));
    base = new OverlayOffsetImage(eye, -8, 8, base);
    base = new OverlayOffsetImage(eye, 8, 8, base);

    Assertions.assertEquals(base, ex.bot.drawRobot());
  }
  
  //tests rust when rust <1.0
  @Test
  void rustTest() {
    initData();
    double before = ex.bot.rustiness;
    ex.bot.rust();
    double after = ex.bot.rustiness;
    Assertions.assertTrue(before < after);
  }
  
  //tests rust when rust is already 1.0
  @Test
  void rustFullTest() {
    initData();
    while (ex.bot.rustiness <= 1.0 - ex.bot.rustSpeed) {
      ex.bot.rust();
    }
    ex.bot.rust();
    ex.bot.rust();
    ex.bot.rust();
    Assertions.assertEquals(ex.bot.rustiness, 1.0, 0.000001);
  }
  
  //tests rust color change effect
  @Test
  void rustEffectColorTest() {
    initData();
    Color before = ex.bot.currentColor;
    ex.bot.rust();
    ex.bot.rustEffect();
    Color after = ex.bot.currentColor;
    boolean didColorChange = (after.getGreen() < before.getGreen()) && (after.getBlue() < before.getBlue());

    Assertions.assertTrue(didColorChange);
  }
  
  //tests rust speed change effect
  @Test
  void rustEffectSpeedTest() {
    initData();
    double before = ex.bot.speedLevel;
    ex.bot.rust();
    ex.bot.rustEffect();
    double after = ex.bot.speedLevel;

    Assertions.assertTrue(before > after);
  }
  
  //tests left horiz movement
  @Test
  void horizMoveLeftTest() {
    initData();
    int before = ex.bot.x;
    ex.onKeyEvent("left");
    int after = ex.bot.x;
    
    Assertions.assertTrue(before > after);
  }
  
  //tests right horiz movement
  @Test
  void horizMoveRightTest() {
    initData();
    int before = ex.bot.x;
    ex.onKeyEvent("right");
    int after = ex.bot.x;
    
    Assertions.assertTrue(before < after);
  }
  
  //tests derust when rusted more than one shake clears
  @Test
  void derustVeryRustyTest() {
    initData();
    double before = ex.bot.rustiness;
    while (ex.bot.rustiness <= ex.bot.shakeAmount) {
      ex.bot.rust();
    }
    ex.bot.rust();
    ex.onKeyEvent("up");
    double after = ex.bot.rustiness;
    Assertions.assertTrue((before < after) && (after >= 0.0));
  }
  
  //tests derust when rusted less than one shake clears
  @Test
  void derustLessRustyTest() {
    initData();
    ex.bot.rust();
    while (ex.bot.rustiness > ex.bot.shakeAmount) {
      ex.onKeyEvent("up");
    }
    ex.onKeyEvent("up");
    Assertions.assertTrue(ex.bot.rustiness >= 0.0);
  }
  
  
  
  
}
