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
  
  public static void main(String[] args) {
    ShakingTheRustOff ex = new ShakingTheRustOff();
    ex.bigBang(ex.width, ex.height, ex.tickRate);
  }

  @Override
  // draws the board
  // EFFECT: mutates the WorldScene to reflect the current game
  public WorldScene makeScene() {
    // TODO Auto-generated method stub
    WorldScene scene = new WorldScene(this.width, this.height);
    return scene;
  }
  
}