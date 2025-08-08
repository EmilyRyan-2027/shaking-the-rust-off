package test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javalib.impworld.WorldScene;
import main.java.Robot;
import main.java.ShakingTheRustOff;

/**
 * Tests for {@link main.java.ShakingTheRustOff}.
 */
public class ShakingTheRustOffTests {
  ShakingTheRustOff ex;
  
  //initializes the data
  void initData() {
    ex = new ShakingTheRustOff(4, 4);
  }
  
  
  //tests makeScene
  @Test
  void makeSceneTest() {
    initData();
    
    WorldScene scene = new WorldScene(ex.width, ex.height);
    scene = ex.firstStage.drawGrid(ex, scene);
    scene.placeImageXY(ex.bot.drawRobot(), ex.bot.x, ex.bot.y);

    Assertions.assertEquals(ex.makeScene(), scene);
  }
  
  //tests onTick
  @Test
  void onTickTest() {
    initData();
    Robot compareBot = ex.bot;
    compareBot.rust();
    compareBot.rustEffect();
    ex.onTick();

    Assertions.assertEquals(ex.bot, compareBot);
  }
  
}
