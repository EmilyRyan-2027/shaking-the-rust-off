package main.java;
import java.awt.Color;
import javalib.worldimages.*;


//represents the player character robot
public class Robot {
  //default rustiness is no rustiness
  public double rustiness = 0.0;
  //speed of rusting
  public double rustSpeed = 0.0005;
  //represents the amount of rust 'shaking' removes
  public double shakeAmount = 0.2;
  //colors representing the least and most rusty
  Color unrustyColor = new Color(182, 187, 191);
  Color rustyColor = new Color(182, 89, 55);
  //current color
  public Color currentColor = unrustyColor;
  //default movement speed
  double fullSpeed = 32.0;
  //fraction of full speed
  public double speedLevel = 1.0;
  //robot dimensions
  int width = 40;
  int height = 80;
  //robot location
  public int x = 500;
  public int y = 100;
  //eye color
  Color eyeColor = new Color(252, 238, 172);
  //downward velocity
  int downVelocity = 0;
  
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
  public WorldImage drawRobot() {
    WorldImage base = new RectangleImage(this.width, this.height, OutlineMode.SOLID, this.currentColor);
    WorldImage eye = new RectangleImage(10, 16, OutlineMode.SOLID, this.eyeColor);
    base = new OverlayOffsetImage(eye, -8, 8, base);
    base = new OverlayOffsetImage(eye, 8, 8, base);
    return base;
  }
  
  //sets the currentColor and speedLevel based on rustiness
  // EFFECT: mutates currentColor and speedLevel
  public void rustEffect() {
    int gDiff = this.unrustyColor.getGreen() - this.rustyColor.getGreen();
    int bDiff = this.unrustyColor.getBlue() - this.rustyColor.getBlue();
    int gNew = (int) (this.unrustyColor.getGreen() - (gDiff * this.rustiness));
    int bNew = (int) (this.unrustyColor.getBlue() - (bDiff * this.rustiness));
    this.currentColor = new Color(182, gNew, bNew);
    this.speedLevel = 1.0 - this.rustiness;
  }
  
  //increases rustiness until rustiness hits 1
  // EFFECT: mutates rustiness
  public void rust() {
    if (this.rustiness <= 1.0 - this.rustSpeed) {
      this.rustiness = this.rustiness + this.rustSpeed; 
    }
  }
  
  //horizontal movement
  // EFFECT: mutates x
  void horizMove(int direction) {
    int speed = (int) (this.fullSpeed * this.speedLevel);
    this.x = this.x + (speed * direction);
  }
  
  //jump
  // EFFECT: mutates velocity, initiates derust
  void jump() {
    this.y = this.y - 1;
    this.downVelocity = -20;
    this.derust();
  }
  
  //derust
  // EFFECT: mutates rustiness
  public void derust() {
    if (this.rustiness < this.shakeAmount) {
      this.rustiness = 0.0;
    } else {
      this.rustiness = this.rustiness - this.shakeAmount;
    }
  }
  
  //getBottomLeft
  //returns a Posn of the robot's bottom left corner
  public Posn getBottomLeft() {
    int x = this.x - (this.width/2);
    int y = this.y + (this.height/2);
    return new Posn(x, y);
  }
  
  //fall
  // EFFECT: mutates y
  public void fall(ShakingTheRustOff wrld, Stage stg) {
    this.downVelocity = this.downVelocity + wrld.g;
    int deltaY = 0;
    if (this.downVelocity > 0) {
      while ((!this.isStanding(wrld, stg, 
          new Posn(this.getBottomLeft().x, this.getBottomLeft().y + deltaY))) && 
          deltaY < this.downVelocity) {
        deltaY = deltaY + 1;
      }
    } else {
      deltaY = this.downVelocity;
    }
    this.y = this.y + deltaY;
  }
  
  //isStanding
  //determines if the given position is on the ground
  public boolean isStanding(ShakingTheRustOff wrld, Stage stg, Posn psn) {
    if ((stg.isGround(wrld, psn.x, psn.y)) && 
        (stg.isGround(wrld, psn.x + this.width, psn.y))) {
      return true;
    } else {
      return false;
    }
  }
  
  //isInGround
  //determines if the given position is in the ground
  public boolean isInGround(ShakingTheRustOff wrld, Stage stg, Posn psn) {
    Posn oneBelow = new Posn(psn.x, psn.y + 1);
    return this.isStanding(wrld, stg, oneBelow);
  }
  

}
