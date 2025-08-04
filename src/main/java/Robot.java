package main.java;
import java.awt.Color;
import javalib.worldimages.*;


//represents the player character robot
public class Robot {
  //default rustiness is no rustiness
  double rustiness = 0.5;
  //colors representing the least and most rusty
  Color unrustyColor = new Color(182, 187, 191);
  Color rustyColor = new Color(182, 89, 55);
  //current color
  Color currentColor = unrustyColor;
  //default movement speed
  double fullSpeed = 16.0;
  //fraction of full speed
  double speedLevel = 1.0;
  //robot dimensions
  int width = 40;
  int height = 80;
  //robot location
  int x = 500;
  int y = 400;
  //eye color
  Color eyeColor = new Color(252, 238, 172);
  
  //default constructor
  Robot() {}
  
  //constructor specifying rustiness
  Robot(double rustiness) {
    this.rustiness = rustiness;
  }
  
  //constructor specifying rustiness and x coordinate
  Robot(double rustiness, int x) {
    this.rustiness = rustiness;
    this.x = x;
  }
  
  //creates an image of the robot
  WorldImage drawRobot() {
    this.rustEffect();
    WorldImage base = new RectangleImage(this.width, this.height, OutlineMode.SOLID, this.currentColor);
    WorldImage eye = new RectangleImage(10, 16, OutlineMode.SOLID, this.eyeColor);
    base = new OverlayOffsetImage(eye, -8, 8, base);
    base = new OverlayOffsetImage(eye, 8, 8, base);
    return base;
  }
  
  //sets the currentColor and speedLevel based on rustiness
  // EFFECT: mutates currentColor and speedLevel
  void rustEffect() {
    int gDiff = this.unrustyColor.getGreen() - this.rustyColor.getGreen();
    int bDiff = this.unrustyColor.getBlue() - this.rustyColor.getBlue();
    int gNew = (int) (this.unrustyColor.getGreen() - (gDiff * this.rustiness));
    int bNew = (int) (this.unrustyColor.getBlue() - (bDiff * this.rustiness));

    this.currentColor = new Color(182, gNew, bNew);
  }
  

}
