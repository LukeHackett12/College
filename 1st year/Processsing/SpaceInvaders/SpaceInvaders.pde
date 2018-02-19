import processing.sound.*;

Alien[] aliens;
Barrier[] barriers;
ArrayList<Bullet> bullets;
ArrayList<PowerUp> powerups;
Player player;
int offScreenIndex;
int collideIndex;
boolean[] haspowers;
boolean[][] destroyed;
PImage alienImageMain;
SoundFile homer;

SoundFile apu;
SoundFile music;
SoundFile shot;
SoundFile death;

void settings(){
  size(SCREENX, SCREENY);
}

void setup(){
    homer = new SoundFile(this, "homer2.mp3");
    homer.amp(2);
    apu = new SoundFile(this, "apu.mp3");
    apu.amp(5);
    music = new SoundFile(this, "music.mp3");
    music.amp(0.5);
    shot = new SoundFile(this, "shot.mp3");
    shot.amp(0.5);
    death = new SoundFile(this, "death.mp3");
    music.play();
    aliens = new Alien[10];
    barriers = new Barrier[3];
    player = new Player(SCREENX - 30);
    bullets = new ArrayList<Bullet>();
    powerups = new ArrayList<PowerUp>();
    haspowers = new boolean[3];
    offScreenIndex = -1;
    collideIndex = -1;
    alienImageMain = loadImage("spacer.GIF");
    init_aliens(alienImageMain);
    init_barriers();

    destroyed = new boolean[10][2];
    for(int i = 0; i < 10; i++){
        destroyed[i][0] = false;
        destroyed[i][1] = false;
    }

    frameRate(30);
}

void mousePressed(){
    loop();
    homer.amp(2);
    apu.amp(5);
    music.amp(0.5);
    shot.play();
    bullets.add(new Bullet(player.xpos+player.player.width/2, player.ypos-player.player.height/2, 10));
}

void reset(){
    background(0);

    textSize(64);
    fill(255, 0, 0);
    background(0);
    text("YOU LOOOSE", SCREENX/2, SCREENY/2);

    aliens = new Alien[10];
    player = new Player(SCREENX - 30);
    bullets = new ArrayList<Bullet>();
    powerups = new ArrayList<PowerUp>();
    haspowers = new boolean[3];
    homer.amp(0);
    apu.amp(0);
    music.amp(0);
    death.amp(30);
    death.play();
    init_aliens(alienImageMain);

    noLoop();
}

void init_aliens(PImage alienImage){
    int xpos = 40;
    int ypos = 30;

    for(int i = 0; i < aliens.length; i++){
        aliens[i] = new Alien(xpos, ypos, alienImage);
        xpos += 60;
    }
}

void init_barriers(){
    int xpos = 50;
    int ypos = SCREENY - 200;

    for(int i = 0; i < barriers.length; i++){
        barriers[i] = new Barrier(xpos, ypos);
        xpos += 500;
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
        if(a.exploded && !destroyed[i][0]){
          destroyed[i][0] = true;
          apu.play();
        }
        a.draw();
        if(a.hasBomb && a.bombObject != null){
            for(Barrier b : barriers){
                if(b.destruct(a.bombObject)){
                    b.draw();
                    homer.play();
                    destroyed[i][1] = true;
                }
            }

            if(!a.bombObject.collide(player) && !destroyed[i][1]){
                a.bombObject.move();
                a.bombObject.draw();

                if(a.bombObject.ypos > SCREENY){
                    a.hasBomb = false;
                    a.bombObject = null;
                }
            }
            else if(!destroyed[i][1]){
                reset();
                return;
            }
        }
        i++;
    }
    //End alien draw

    //Draw bariers
    for(Barrier b : barriers){
        b.draw();
    }
    //End draw barriers

    //Draw bullets
    for(Bullet bullet : bullets){
        for(Barrier b : barriers){
            if(b.destruct(bullet)){
                homer.play();
                offScreenIndex = bullets.indexOf(bullet);
            }
            b.draw();
        }
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
                    bullet.yvel = 40;
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
