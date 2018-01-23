class Player {
  float xpos; float prevXPos; float ypos; float vel;
  int lives;
  color paddlecolor = color(255);
  Player(float screen_y){
    xpos=SCREENX/2;
    ypos=screen_y;
    vel = 0;
    lives=NUMLIVES;
  }

  void move(float x){
    if(x>SCREENX-PADDLEWIDTH) xpos = SCREENX-PADDLEWIDTH;
    else xpos=x;
  }

  void draw(){
    fill(paddlecolor);
    rect(xpos, ypos, PADDLEWIDTH, PADDLEHEIGHT);
  }
  
}
