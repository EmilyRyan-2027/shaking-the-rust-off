package test.java;

import java.awt.Color;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javalib.worldimages.OutlineMode;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import main.java.AirTile;
import main.java.GroundTile;
import main.java.ShakingTheRustOff;
import main.java.Tile;

//tests for Tile class
/**
 * Tests for {@link main.java.Tile}.
 */
public class TileTests {
  
  ShakingTheRustOff ex;
  Tile exAT;
  Tile exGT;
  
  //initializes the data
  void initData() {
    ex = new ShakingTheRustOff(4, 4);
    exAT = new AirTile();
    exGT = new GroundTile();
  }
  
  //tests drawTile
  @Test
  void drawAirTileTest() {
    initData();
    Assertions.assertEquals(new RectangleImage(ex.tileLength, ex.tileLength, OutlineMode.SOLID, new Color(220, 232, 247)).movePinholeTo(new Posn(ex.tileLength/-2, ex.tileLength/-2)), exAT.drawTile(ex));
  }
  
  @Test
  void drawGroundTileTest() {
    initData();
    Assertions.assertEquals(new RectangleImage(ex.tileLength, ex.tileLength, OutlineMode.SOLID, Color.DARK_GRAY).movePinholeTo(new Posn(ex.tileLength/-2, ex.tileLength/-2)), exGT.drawTile(ex));
  }


}
