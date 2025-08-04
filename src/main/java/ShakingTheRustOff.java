package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

//represents the rust game
class ShakingTheRustOff extends World {
  //the width and height of the window
  int width = 1000;
  int height = 800;
  
  double tickRate = 0.05;
  
  ArrayList<Robot> bots = new ArrayList<Robot>();
  
  public static void main(String[] args) {
    ShakingTheRustOff ex = new ShakingTheRustOff();
    ex.bigBang(ex.width, ex.height, ex.tickRate);
    ex.makeBots(20);
  }

  //make some bots
  void makeBots(int numberBots) {
    int margin = 20;
    int spacing = (this.width - margin*2)/(numberBots+1);
    double gradation = 1.0/numberBots;
    for (int i = 1; i <= numberBots; i++) {
      bots.add(new Robot((gradation * i), (spacing * i) + margin));
    }
  }
  
  @Override
  // draws the board
  // EFFECT: mutates the WorldScene to reflect the current game
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.width, this.height);
    for (Robot bot : bots) {
      scene.placeImageXY(bot.drawRobot(), bot.x, bot.y);
    }
    return scene;
  }
  
}