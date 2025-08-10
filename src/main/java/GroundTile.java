package main.java;

import java.awt.Color;

//tile representing standable ground
public class GroundTile extends Tile{

  @Override
  Color tileColor() {
    return Color.DARK_GRAY;
  }

  //determines if the tile is standable ground
  @Override
  boolean isStandable() {
    return true;
  }

}
