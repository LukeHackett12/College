class Player {

    /* Insert your code from week 2 here to begin with, again you need
    to remember the position and appearance of the Player, a constructor,
    methods to move the player, and to draw the player */

    float xpos; float ypos;
    int lives;
    color playerColor = color(255);
    PImage player;
    Player(float screen_y){
        xpos=SCREENX/2;
        ypos=screen_y;
        lives=NUMLIVES;
        player = loadImage("player.GIF");
    }

    void move(float x){
        if(x>SCREENX-PLAYERWIDTH) xpos = SCREENX-PLAYERWIDTH;
        else xpos=x;
    }

    void draw(){
        tint(255, 255, 255);
        image(player, xpos, ypos);
    }
}
