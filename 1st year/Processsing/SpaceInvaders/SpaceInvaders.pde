/* Declare an array of Aliens */
Alien[] aliens;
PImage alienImageMain;
int frameRate;

void settings(){
  size(SCREENX, SCREENY);
}

void setup(){
/* create a new alien array */
/* load the images */
/* initialise the array */
    aliens = new Alien[10];
    alienImageMain = loadImage("spacer.GIF");
    init_aliens(alienImageMain);
    frameRate = 30;
    frameRate(frameRate);
}

void init_aliens(PImage alienImage){
/* for each position in the array,  create a new alien at an appropriate
starting point on the screen */
    int xpos = 40;
    int ypos = 30;

    for(int i = 0; i < aliens.length; i++){
        aliens[i] = new Alien(xpos, ypos, alienImage);
        xpos += 40;
    }
}

void draw(){
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
