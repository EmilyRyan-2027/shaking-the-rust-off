package main.java;

import java.util.ArrayList;

import javalib.impworld.WorldScene;
//represents the stuff on the screen excluding the robot
public class Stage {
  public ArrayList<ArrayList<Tile>> tiles;
  
  //constructor specifying world
  Stage(ShakingTheRustOff world) {
    makeStage(world);
  }
  
  //constructor specifying world and floor height
  Stage(ShakingTheRustOff world, int x) {
    makeStage(world);
    this.addFloor(world, x);
  }
  
  
  //makes a basic stage: 2D grid of tiles
  // EFFECT: mutates tiles to be this stage
  void makeStage(ShakingTheRustOff wrld) {
    ArrayList<ArrayList<Tile>> grid = new ArrayList<ArrayList<Tile>>();
    //rows
    for (int i = 0; i < wrld.heightTiles; i++) {
      grid.add(new ArrayList<Tile>());
      //cols
      for (int j = 0; j < wrld.widthTiles; j++) {
        grid.get(i).add(new AirTile());
      }
    }
    this.tiles = grid;
  }
  
  //adds a floor of height x to this stage
  void addFloor(ShakingTheRustOff wrld, int x) {
    //rows
    for (int i = wrld.heightTiles - x; i < wrld.heightTiles; i++) {
      //cols
      for (int j = 0; j < wrld.widthTiles; j++) {
        this.tiles.get(i).set(j, new GroundTile());
      }
    }
  }
  
  //places the tiles grid onto the scene
  public WorldScene drawGrid(ShakingTheRustOff wrld, WorldScene scn) {
    //rows
    for (int i = 0; i < wrld.heightTiles; i++) {
      //cols
      for (int j = 0; j < wrld.widthTiles; j++) {
        Tile currentTile = this.tiles.get(i).get(j);
        scn.placeImageXY(currentTile.drawTile(wrld), j*wrld.tileLength, i*wrld.tileLength);
      }
    }
    return scn;
  }
  
}
