class Player {
  float xpos; float prevXPos; float ypos; float vel;
  int lives;
  color paddlecolor = color(255);
  PImage paddle;
  Player(float screen_y){
    xpos=SCREENX/2;
    ypos=screen_y;
    vel = 0;
    lives=NUMLIVES;
    paddle = loadImage("cat.png");
  }

  void move(float x){
    if(x>SCREENX-PADDLEWIDTH) xpos = SCREENX-PADDLEWIDTH;
    else xpos=x;

    thePlayer.vel = thePlayer.xpos - thePlayer.prevXPos;
    thePlayer.prevXPos = thePlayer.xpos;
  }

  void draw(){
    //fill(paddlecolor);
    //rect(xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
    imageMode(CORNER);
    image(paddle, xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
  }

}
