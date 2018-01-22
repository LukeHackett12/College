class Ball {
  float x; 
  float y;
  float dx; 
  float dy;
  int radius;
  color ballColor = color(200, 100, 50);

  Ball() {
    x = random(SCREENX/4, SCREENX/2);
    y = random(SCREENY/4, SCREENY/2);
    dx = random(1, 2); 
    dy = random(1, 2);
    radius=BALLRADIUS;
  }

  void move() {
    x = x+dx; 
    y = y+dy;
  }

  void draw() {
    fill(ballColor);
    ellipse(int(x), int(y), radius, 
      radius);
  }

  void collidePlayer(Player tp) {
    if (y+radius >= tp.ypos && y-radius<=tp.ypos+PADDLEHEIGHT && x >= tp.xpos && x <= tp.xpos+PADDLEWIDTH) {
      println("collided!");
      dy=-dy;
    }
  }
  
   void collideEnemy(Player ep) {
    if (y-radius <= ep.ypos+PADDLEHEIGHT && y-radius>=ep.ypos && x >= ep.xpos && x <= ep.xpos+PADDLEWIDTH) {
      println("collided!");
      dy=-dy;
    }
  }
    
    void collideWall(){
      if (x-radius <= 0) dx=-dx;
      else if (x+radius >= SCREENX) dx=-dx;
    }
}