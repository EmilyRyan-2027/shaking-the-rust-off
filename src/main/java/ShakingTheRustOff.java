package main.java;

import javalib.impworld.*;

//represents the rust game
public class ShakingTheRustOff extends World {
  public int widthTiles = 80;
  public int heightTiles = 60;
  public int tileLength = 10;
  //the width and height of the window
  public int width = widthTiles * tileLength;
  public int height = heightTiles * tileLength;
  
  double tickRate = 0.005;
  
  //in case you wanted some bots
  //ArrayList<Robot> bots = new ArrayList<Robot>();
  
  //our player character robot
  public Robot bot = new Robot(0.0, this.width/2);
  
  //the first screen we see
  public Stage firstStage = new Stage(this, 3);
  public Stage currentStage = this.firstStage;
  
  //default constructor
  public ShakingTheRustOff () {}
  
  //Acceleration due to gravity
  public int g = 1;
  
  //constructor specifying stage dimensions
  public ShakingTheRustOff (int widthTiles, int heightTiles) {
    this.widthTiles = widthTiles;
    this.heightTiles = heightTiles;
  }
  
  public static void main(String[] args) {
    ShakingTheRustOff ex = new ShakingTheRustOff();
    ex.bigBang(ex.width, ex.height, ex.tickRate);
  }
  
  @Override
  // draws the board
  // EFFECT: mutates the WorldScene to reflect the current game
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width, this.height);
    scene = this.currentStage.drawGrid(this, scene);
    scene.placeImageXY(this.bot.drawRobot(), this.bot.x, this.bot.y);
    return scene;
  }
  
  //updates the world according to time
  public void onTick() {
    this.bot.fall(this, currentStage);
    this.bot.rust();
    this.bot.rustEffect();
  }
  
  //updates the world according to the key event
  public void onKeyEvent(String key) {
    if (key.equals("right")) {
      this.bot.horizMove(1);
    } else if (key.equals("left")) {
      this.bot.horizMove(-1);
    } else if (key.equals("up")) {
      if (this.bot.isStanding(this, currentStage, this.bot.getBottomLeft())) {
        this.bot.jump();
      }
    }
  }
  
}