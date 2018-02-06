import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SpaceInvaders extends PApplet {

/* Declare an array of Aliens */
Alien[] aliens;
PImage alienImageMain;
int frameRate;

public void settings(){
  size(SCREENX, SCREENY);
}

public void setup(){
/* create a new alien array */
/* load the images */
/* initialise the array */
    aliens = new Alien[10];
    alienImageMain = loadImage("spacer.GIF");
    init_aliens(alienImageMain);
    frameRate = 30;
    frameRate(frameRate);
}

public void init_aliens(PImage alienImage){
/* for each position in the array,  create a new alien at an appropriate
starting point on the screen */
    int xpos = 40;
    int ypos = 30;

    for(int i = 0; i < aliens.length; i++){
        aliens[i] = new Alien(xpos, ypos, alienImage);
        xpos += 40;
    }
}

public void draw(){
/* clear the screen */
/* for each alien in the array - move the alien, then draw the alien */
    background(0);
    if (aliens[aliens.length-1].xpos + alienImageMain.width < SCREENX && aliens[aliens.length-1].dir == A_FORWARD || aliens[0].xpos == 0 && aliens[0].dir == A_FORWARD){
        for(Alien a : aliens){
            a.dir = A_FORWARD;
        }
    } else if (aliens[0].xpos < SCREENX && aliens[0].xpos > 0 && aliens[0].dir == A_BACKWARD || aliens[aliens.length-1].xpos ==  SCREENX - alienImageMain.width && aliens[aliens.length-1].dir == A_BACKWARD){
        for(Alien a : aliens){
            a.dir = A_BACKWARD;
        }
    } else if (aliens[0].dir != A_DOWN || aliens[aliens.length - 1].dir != A_DOWN){
        for(Alien a : aliens){
            a.dir = A_DOWN;
            a.ysaved = a.ypos;
        }
    }

    if(millis()%20 == 0){
        frameRate(++frameRate);
    }

    int i = 0;
    for(Alien a : aliens){
        int rand = (int)random(4000);
        if(rand == 1 && !a.exploded){
            a.explode();
        } else {
            if(i%2 == 1) tint(100, 50, 50);
            else tint(50, 50, 100);
            a.move();
            a.draw();
        }
        i++;
    }
}
final int A_FORWARD = 0;
final int A_BACKWARD = 1;
final int A_DOWN = 2;

class Alien {
    /* declare variables for alien position, direction of movement and appearance */
    int xpos;
    float ypos;
    float ysaved;
    int vel;
    int dir;
    float sin;
    boolean exploded;
    PImage alienImage;
    /* Constructor is passed the x and y position where the alien is to
    be created, plus the image to be used to draw the alien */

    Alien(int xpos, float ypos, PImage alienImage){
    /* set up the new alien object */
        this.xpos = xpos;
        this.ypos = ypos;
        this.alienImage = alienImage;
        dir = 1;
        exploded = false;
        sin = 0;
        vel = 2;
        imageMode(CORNER);
    }

    public void explode(){
        image(loadImage("exploding.GIF"), xpos, ypos);
        exploded = true;
    }

    public void move(){
    /* Move the alien according to the instructions in the exercise */
        if(dir == A_DOWN){
            if(ypos - ysaved < alienImage.height) ypos += vel;
            else if(xpos == 0){
                dir = A_FORWARD;
            }
            else if(xpos == SCREENX - alienImage.width){
                 dir = A_BACKWARD;
             }
        }

        else if(dir == A_FORWARD){
            xpos += vel;
            ypos += sin(sin) * 4;
            sin += 0.1f;
        }
        else if(dir == A_BACKWARD){
             xpos -= vel;
             ypos += sin(sin) * 4;
             sin += 0.1f;
        }
    }

    public void draw(){
    /* Draw the alien using the image() method demonstrated in class */
        if(!exploded) image(alienImage, xpos, ypos);
    }
}
final int SCREENX = 1000;
final int SCREENY = 1000;
final int NUMLIVES = 3;
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SpaceInvaders" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}