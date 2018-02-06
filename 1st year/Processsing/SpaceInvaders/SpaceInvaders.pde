/* Declare an array of Aliens */
Alien[] aliens;
ArrayList<Bullet> bullets;
ArrayList<PowerUp> powerups;
Player player;
int offScreenIndex;
int collideIndex;
boolean[] haspowers;
PImage alienImageMain;

void settings(){
  size(SCREENX, SCREENY);
}

void setup(){
    aliens = new Alien[10];
    player = new Player(SCREENX - 30);
    bullets = new ArrayList<Bullet>();
    powerups = new ArrayList<PowerUp>();
    haspowers = new boolean[3];
    offScreenIndex = -1;
    collideIndex = -1;
    alienImageMain = loadImage("spacer.GIF");
    init_aliens(alienImageMain);
    frameRate(30);
}

void mousePressed(){
    bullets.add(new Bullet(player.xpos+player.player.width/2, player.ypos-player.player.height/2, 10));
}

void init_aliens(PImage alienImage){
    int xpos = 40;
    int ypos = 30;

    for(int i = 0; i < aliens.length; i++){
        aliens[i] = new Alien(xpos, ypos, alienImage);
        xpos += 40;
    }
}

void draw(){
    //Start alien draw
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

    int i = 0;
    for(Alien a : aliens){
        if(i%2 == 1) tint(150, 100, 100);
        else tint(100, 100, 150);
        a.move();
        a.draw();
        i++;
    }
    //End alien draw

    //Draw bullets
    for(Bullet bullet : bullets){
        if(!bullet.collide(aliens)){
            if(bullet.ypos <= 0-BULLETHEIGHT){
                offScreenIndex = bullets.indexOf(bullet);
            }
            if(!haspowers[0] && !haspowers[1] && !haspowers[2]){
                bullet.move();
                bullet.draw();
            }
            else{
                if (haspowers[0]){
                    bullet.moveRandom();
                    bullet.draw();
                }
                else if (haspowers[1]){
                    bullet.movePongy();
                    bullet.draw();
                }
                else if (haspowers[2]){
                    bullet.yvel = 20;
                    bullet.move();
                    bullet.draw();
                }
            }
        }
        else {
            powerups.add(new PowerUp(bullet.xpos, bullet.ypos, (int)random(3)));
        }
    }

    if(offScreenIndex != -1){
        bullets.remove(offScreenIndex);
        offScreenIndex = -1;
    }
    //End drawing bullets

    //Draw powerups
    for(PowerUp powerup : powerups){
        if(!powerup.collide(player)){
            powerup.move();
            powerup.draw();
        }
        else{
            collideIndex = powerups.indexOf(powerup);
            if(powerup.type == 1){
                haspowers[0] = true;
                haspowers[1] = false;
                haspowers[2] = false;
            }
            else if(powerup.type == 2) {
                haspowers[0] = false;
                haspowers[1] = true;
                haspowers[2] = false;
            }
            else if(powerup.type == 3) {
                haspowers[0] = false;
                haspowers[1] = false;
                haspowers[2] = true;
            }
        }
    }

    if(collideIndex != -1){
        powerups.remove(collideIndex);
        collideIndex = -1;
    }
    //End drawing powerups

    //Draw player
    player.move(mouseX);
    player.draw();
    //End drawing player
}
