Player thePlayer;
Player computer;
Ball theBall;
int time;
String playerLives;
String computerLives;

void settings(){
  size(SCREENX, SCREENY);
}

void setup(){
  thePlayer = new Player(SCREENY-MARGIN-PADDLEHEIGHT);
  computer = new Player(MARGIN);
  theBall = new Ball();
  playerLives = Integer.toString(NUMLIVES);
  computerLives = Integer.toString(NUMLIVES);
  ellipseMode(RADIUS);
  textAlign(CENTER);
  frameRate(60);
  computer.vel = 2 ;//+ (3 - computer.lives);
  time = millis();
}

void computerMove(){
  if(computer.xpos + PADDLEWIDTH/2 < theBall.x) computer.xpos += computer.vel;
  else if(computer.xpos + PADDLEWIDTH/2 > theBall.x) computer.xpos -= computer.vel;

  if(millis() > time + 100){
    if(computer.vel <= theBall.dx + 1.2) computer.vel *= 1.015;
    time = millis();
  }
}

void mousePressed(){
  loop();
}

void reset(){
  thePlayer = new Player(SCREENY-MARGIN-PADDLEHEIGHT);
  computer = new Player(MARGIN);
  computer.vel = 2;
  theBall = new Ball();
  background(0);
  thePlayer.draw();
  computer.draw();
  theBall.draw();
  drawText();
  noLoop();
}

void drawText(){
  fill(255);
  textSize(18);
  text("Comp lives: " + computerLives, 100, 15);
  text("Player lives: " + playerLives, 100, SCREENY - 10);
  text("Computer velocity: " + computer.vel, SCREENX - 100, 20);
  text("Ball velocity(x): " + Math.abs(theBall.dx), SCREENX - 100, 60);
  //text("Player velocity: " + thePlayer.vel, 10, SCREENY - 20);
}

void deathCheck(){
  if(theBall.y < BALLRADIUS){
    if(--computer.lives > 0){
      computerLives = Integer.toString(computer.lives);
      reset();
    }
    else {
      textSize(64);
      fill(0, 255, 0);
      background(0);
      text("YOU WIN", SCREENX/2, SCREENY/2);
    }
  } else if(theBall.y > SCREENY - BALLRADIUS){
    if(--thePlayer.lives > 0){
      playerLives = Integer.toString(thePlayer.lives);
      reset();
    }
    else {
      textSize(64);
      fill(255, 0, 0);
      background(0);
      text("GAME OVER", SCREENX/2, SCREENY/2);
    }
  }
}

void draw() {
  background(0);

  thePlayer.move(mouseX);
  theBall.move();
  computerMove();

  theBall.collidePlayer(thePlayer);
  theBall.collideEnemy(computer);
  theBall.collideWall();

  thePlayer.draw();
  computer.draw();
  theBall.draw();

  drawText();
  deathCheck();
}
