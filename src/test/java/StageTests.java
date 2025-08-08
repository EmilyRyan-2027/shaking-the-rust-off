package test.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javalib.impworld.WorldScene;
import main.java.ShakingTheRustOff;
import main.java.Tile;

/**
 * Tests for {@link main.java.Stage}.
 */
public class StageTests {
  ShakingTheRustOff ex;
  
  //initializes the data
  void initData() {
    ex = new ShakingTheRustOff(4, 4);
  }
  
  //tests drawGrid
  @Test
  void drawGridTest() {
    initData();
    WorldScene scn = new WorldScene(ex.width, ex.height);
    //rows
    for (int i = 0; i < ex.heightTiles; i++) {
      //cols
      for (int j = 0; j < ex.widthTiles; j++) {
        Tile currentTile = ex.firstStage.tiles.get(i).get(j);
        scn.placeImageXY(currentTile.drawTile(ex), j*ex.tileLength, i*ex.tileLength);
      }
    }

    Assertions.assertEquals(scn, ex.firstStage.drawGrid(ex, new WorldScene(ex.width, ex.height)));
  }
  
}
