final int A_FORWARD = 0;
final int A_BACKWARD = 1;
final int A_DOWN = 2;

class Alien {
    /* declare variables for alien position, direction of movement and appearance */
    int xpos;
    int ypos;
    int ysaved;
    int dir;
    boolean exploded;
    PImage alienImage;
    /* Constructor is passed the x and y position where the alien is to
    be created, plus the image to be used to draw the alien */

    Alien(int xpos, int ypos, PImage alienImage){
    /* set up the new alien object */
        this.xpos = xpos;
        this.ypos = ypos;
        this.alienImage = alienImage;
        dir = 1;
        exploded = false;
        imageMode(CORNER);
    }

    void explode(){
        image(loadImage("exploding.GIF"), xpos, ypos);
        exploded = true;
    }

    void move(){
    /* Move the alien according to the instructions in the exercise */
        if(dir == A_DOWN){
            if(ypos - ysaved < alienImage.height)ypos += 2;
            else if(xpos == 0){
                dir = A_FORWARD;
            }
            else if(xpos == SCREENX - alienImage.width){
                 dir = A_BACKWARD;
             }
        }
        else if(dir == A_FORWARD) xpos += 2;
        else if(dir == A_BACKWARD) xpos -= 2;
    }

    void draw(){
    /* Draw the alien using the image() method demonstrated in class */
        if(!exploded) image(alienImage, xpos, ypos);
    }
}
