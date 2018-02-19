import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SpaceInvaders extends PApplet {



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

public void settings(){
  size(SCREENX, SCREENY);
}

public void setup(){
    homer = new SoundFile(this, "homer2.mp3");
    homer.amp(2);
    apu = new SoundFile(this, "apu.mp3");
    apu.amp(5);
    music = new SoundFile(this, "music.mp3");
    music.amp(0.5f);
    shot = new SoundFile(this, "shot.mp3");
    shot.amp(0.5f);
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

public void mousePressed(){
    loop();
    homer.amp(2);
    apu.amp(5);
    music.amp(0.5f);
    shot.play();
    bullets.add(new Bullet(player.xpos+player.player.width/2, player.ypos-player.player.height/2, 10));
}

public void reset(){
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

public void init_aliens(PImage alienImage){
    int xpos = 40;
    int ypos = 30;

    for(int i = 0; i < aliens.length; i++){
        aliens[i] = new Alien(xpos, ypos, alienImage);
        xpos += 60;
    }
}

public void init_barriers(){
    int xpos = 50;
    int ypos = SCREENY - 200;

    for(int i = 0; i < barriers.length; i++){
        barriers[i] = new Barrier(xpos, ypos);
        xpos += 500;
    }
}

public void draw(){
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
class Alien {
    
    float xpos;
    float ypos;
    float ysaved;
    int vel;
    int dir;
    float sin;
    boolean exploded;
    int explodeRender;
    boolean hasBomb;
    Bomb bombObject;
    PImage alienImage;

    Alien(float xpos, float ypos, PImage alienImage){
        this.xpos = xpos;
        this.ypos = ypos;
        this.alienImage = alienImage;
        dir = A_FORWARD;
        exploded = false;
        sin = 0;
        vel = 2;
        explodeRender = 0;
        hasBomb = false;
        imageMode(CORNER);
    }

    public void explode(){
        exploded = true;    
    }
    
    public void move(){
        if(dir == A_DOWN){
            if(ypos - ysaved < alienImage.height) ypos += vel;
            else if(xpos == 0){
                dir = A_FORWARD;
            }
            else if(xpos == SCREENX - alienImage.width){
                 dir = A_BACKWARD;
             }
        }

        else if(dir == A_FORWARD){
            xpos += vel;
            ypos += sin(sin) * 4;
            sin += 0.1f;
        }
        else if(dir == A_BACKWARD){
             xpos -= vel;
             ypos += sin(sin) * 4;
             sin += 0.1f;
        }
    }

    public void draw(){
        if(!exploded){
            image(alienImage, xpos, ypos, 50, 50);
            if(!hasBomb){
                if((int)random(500) == 1){
                    bombObject = new Bomb(xpos, ypos);
                    hasBomb = true;
                }
            }
        }
        else {
            if(explodeRender++ <= 15)image(loadImage("exploding.GIF"), xpos, ypos, 50, 50);
        }
    }
}
class Barrier{
    float xpos; float ypos;
    int height; int width;

    Barrier(float xpos, float ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = BARRIERHEIGHT;
        this.width = BARRIERWIDTH;
    }

    public boolean destruct(Bullet bullet){
        if(bullet.ypos <= ypos + height
            && bullet.ypos + height >= ypos
            && bullet.xpos >= xpos && bullet.xpos + BULLETWIDTH <= xpos+BARRIERWIDTH){
                if(height > 0){
                    height -= 10;
                    return true;
                }
            }
            return false;
    }

    public boolean destruct(Bomb bomb){
        if(bomb.ypos >= ypos
            && bomb.ypos <= ypos + height
            && bomb.xpos >= xpos && bomb.xpos <= xpos + BARRIERWIDTH){
                if(height > 0){
                    homer.play();
                    height -= 10;
                    ypos += 10;
                    return true;
                }
            }
            return false;
    }

    public void draw(){
        fill(0, 0, 255);
        rect(xpos, ypos, width, height);
    }
}
class Bomb{
    float xpos; float ypos;

    Bomb(float xpos, float ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public boolean collide(Player player){
        if(player.ypos + player.player.height >= ypos
            && player.ypos <= ypos
            && player.xpos <= xpos && player.xpos + player.player.height >= xpos){
                return true;
            }
            return false;
    }

    public void move(){
        ypos += 10;
    }

    public void draw(){
        fill(255, 0, 0);
        ellipse(xpos, ypos, 50, 50);
    }
}
final int BULLETWIDTH = 10;
final int BULLETHEIGHT = 50;

class Bullet {
    float xpos;
    float ypos;
    int yvel;
    int xvel;

    Bullet(float xpos, float ypos, int yvel){
        this.xpos = xpos;
        this.ypos = ypos;
        this.yvel = yvel;
        this.xvel = (int)random(-10, 10);
    }

    public boolean collide(Alien[] aliens){
        for(Alien alien : aliens){
            if(alien.ypos + 50 >= ypos
                && alien.ypos <= ypos
                && alien.xpos <= xpos && alien.xpos + 50 >= xpos
                && !alien.exploded){
                    alien.explode();
                    return true;
                }
        }
        return false;
    }

    public void move(){
        ypos -= yvel;
    }

    public void moveRandom(){
        ypos -= 10;
        xpos += random(-20, 20);
    }

    public void movePongy(){
        ypos -= 10;
        if(xpos <= 0) xvel = 10;
        else if (xpos + BULLETWIDTH >= SCREENX) xvel = -10;

        xpos += xvel;
    }

    public void draw(){
        fill(0, 220, 0);
        rect(xpos, ypos, BULLETWIDTH, BULLETHEIGHT);
    }
}
final int A_FORWARD = 0;
final int A_BACKWARD = 1;
final int A_DOWN = 2;
final int SCREENX = 1500;
final int SCREENY = 1500;
final int PLAYERWIDTH = 200;
final int PLAYERHEIGHT = 20;
final int BARRIERHEIGHT = 60;
final int BARRIERWIDTH = 100;
class Player {

    /* Insert your code from week 2 here to begin with, again you need
    to remember the position and appearance of the Player, a constructor,
    methods to move the player, and to draw the player */

    float xpos; float ypos;
    int playerColor = color(255);
    PImage player;

    Player(float screen_y){
        xpos=SCREENX/2;
        ypos=screen_y;
        player = loadImage("player.GIF");
    }

    public void move(float x){
        if(x>SCREENX-PLAYERWIDTH) xpos = SCREENX-PLAYERWIDTH;
        else xpos=x;
    }

    public void draw(){
        tint(255, 255, 255);
        image(player, xpos, ypos);
    }
}
class PowerUp{
    float xpos; float ypos;
    int type;

    PowerUp(float xpos, float ypos, int type){
        this.xpos = xpos;
        this.ypos = ypos;
        this.type = type+1;
    }

    public boolean collide(Player player){
        if(player.ypos + player.player.height >= ypos
            && player.ypos <= ypos
            && player.xpos <= xpos && player.xpos + player.player.height >= xpos){
                return true;
            }
            return false;
    }

    public void move(){
        ypos += 10;
    }

    public void draw(){
        switch(type){
            case 1:
                fill(255, 0, 0);
                rect(xpos, ypos, 20, 20);
                break;
            case 2:
                fill(0, 255, 0);
                rect(xpos, ypos, 20, 20);
                break;
            case 3:
                fill(0, 0, 255);
                rect(xpos, ypos, 20, 20);
                break;
        }
    }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SpaceInvaders" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
