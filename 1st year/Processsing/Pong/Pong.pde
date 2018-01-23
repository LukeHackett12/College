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
  frameRate(120);
  computer.vel = 1.1;
  time = millis();
}

void computerMove(){
  if(computer.xpos + PADDLEWIDTH/2 < theBall.x) computer.xpos += computer.vel;
  else if(computer.xpos + PADDLEWIDTH/2 > theBall.x) computer.xpos -= computer.vel;

  if(millis() > time + 200){
    if(computer.vel <= theBall.dx + 0.5) computer.vel *= 1.01;
    time = millis();
  }
}

void mousePressed(){
  loop();
}

void reset(){
  theBall.x = SCREENX/2;
  theBall.y = SCREENY/2;
  theBall.dx = random(1, 2);
  theBall.dy = random(1, 2);
  computer.xpos = SCREENX/2 - PADDLEWIDTH/2;
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
  text("Comp lives: " + computerLives, 10, 15);
  text("Player lives: " + playerLives, 10, SCREENY - 10);
  text("Computer velocity: " + computer.vel, SCREENX - 250, 20);
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
  theBall.move(thePlayer);
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
