class Ball {
  float x;
  float y;
  float dx;
  float dy;
  float time;
  int radius;
  color ballColor = color(200, 100, 50);

  Ball() {
    x = random(SCREENX/4, SCREENX/2);
    y = random(SCREENY/4, SCREENY/2);
    dx = random(1, 2);
    dy = random(1, 2);
    time = millis();
    radius=BALLRADIUS;
  }

  void move(Player tp) {
    x = x+dx;
    y = y+dy;

    if(millis() > time + 300){
      dx *= 1.01;
      dy *= 1.01;
      time = millis();
    }
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
      dx += 0.2*tp.vel;
    }
  }

   void collideEnemy(Player ep) {
    if (y-radius <= ep.ypos+PADDLEHEIGHT && y-radius>=ep.ypos && x >= ep.xpos && x <= ep.xpos+PADDLEWIDTH) {
      println("collided!");
      dy=-dy;
      dx += 0.5*ep.vel;
    }
  }

    void collideWall(){
      if (x-radius <= 0) dx=-dx;
      else if (x+radius >= SCREENX) dx=-dx;
    }
}
