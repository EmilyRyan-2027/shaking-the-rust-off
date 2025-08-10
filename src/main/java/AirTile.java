package main.java;

import java.awt.Color;

//tile with nothing in it
public class AirTile extends Tile{
  
  @Override
  Color tileColor() {
    return new Color(220, 232, 247);
  }

  //determines if the tile is standable ground
  @Override
  boolean isStandable() {
    return false;
  }

}
