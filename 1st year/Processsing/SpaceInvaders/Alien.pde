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

    void explode(){
        exploded = true;    
    }
    
    void move(){
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
            sin += 0.1;
        }
        else if(dir == A_BACKWARD){
             xpos -= vel;
             ypos += sin(sin) * 4;
             sin += 0.1;
        }
    }

    void draw(){
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