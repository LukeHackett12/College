class Player {
  float xpos; float prevXPos; float ypos; float vel;
  int lives;
  int velTime;
  color paddlecolor = color(255);
  Player(float screen_y){
    xpos=SCREENX/2;
    ypos=screen_y;
    vel = 0;
    velTime = millis();
    lives=NUMLIVES;
  }

  void move(float x){
    if(x>SCREENX-PADDLEWIDTH) xpos = SCREENX-PADDLEWIDTH;
    else xpos=x;

    thePlayer.vel = thePlayer.xpos - thePlayer.prevXPos;
    thePlayer.prevXPos = thePlayer.xpos;
  }

  void draw(){
    fill(paddlecolor);
    rect(xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
  }

}
