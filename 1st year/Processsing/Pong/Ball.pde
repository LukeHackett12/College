class Ball {
  float x;
  float y;
  float dx;
  float dy;
  float time;
  int radius;
  PImage cat;
  color ballColor = color(200, 100, 50);

  Ball() {
    x = random(SCREENX/4, SCREENX/2);
    y = random(SCREENY/4, SCREENY/2);
    dx = random(2, 4);
    dy = random(2, 4);
    time = millis();
    radius=BALLRADIUS;
    cat = loadImage("cat.png");
  }

  void move() {
    x = x+dx;
    y = y+dy;

    if(millis() > time + 300){
      dx *= 1.02;
      dy *= 1.02;
      time = millis();
    }
  }

  void draw() {
    //fill(ballColor);
    //ellipse(int(x), int(y), radius, radius);
    imageMode(CENTER);
    image(cat, int(x), int(y));
  }

  void collidePlayer(Player tp) {
      //Y collission
      if (y+radius >= tp.ypos && y-radius<=tp.ypos+PADDLEHEIGHT && x >= tp.xpos && x <= tp.xpos+PADDLEWIDTH) {
        //println("collided!");
        dy=-dy;
        dx += 0.2*tp.vel;
      }
  }

   void collideEnemy(Player ep) {
    if (y-radius <= ep.ypos+PADDLEHEIGHT && y-radius>=ep.ypos && x >= ep.xpos && x <= ep.xpos+PADDLEWIDTH) {
      //println("collided!");
      dy=-dy;
      dx += 0.3*ep.vel;
    }
  }

    void collideWall(){
      if (x-radius <= 0) dx=-dx;
      else if (x+radius >= SCREENX) dx=-dx;
    }
}
