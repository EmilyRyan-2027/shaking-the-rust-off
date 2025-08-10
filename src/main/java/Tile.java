package main.java;

import java.awt.Color;
import javalib.worldimages.*;

//represents a tile on the stage
public abstract class Tile {
  
  //assign a base color to this tile
  abstract Color tileColor();
  
  //draw a tile with the right color
  public WorldImage drawTile(ShakingTheRustOff wrld) {
    WorldImage square = new RectangleImage(wrld.tileLength, wrld.tileLength, OutlineMode.SOLID, this.tileColor());
    square = square.movePinholeTo(new Posn(wrld.tileLength/-2, wrld.tileLength/-2));
    return square;
  }
  
  //determines if the tile is standable ground
  abstract boolean isStandable();

}
