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

    boolean collide(Alien[] aliens){
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

    void move(){
        ypos -= yvel;
    }

    void moveRandom(){
        ypos -= 10;
        xpos += random(-20, 20);
    }

    void movePongy(){
        ypos -= 10;
        if(xpos <= 0) xvel = 10;
        else if (xpos + BULLETWIDTH >= SCREENX) xvel = -10;

        xpos += xvel;
    }

    void draw(){
        fill(0, 220, 0);
        rect(xpos, ypos, BULLETWIDTH, BULLETHEIGHT);
    }
}
