final int BULLETWIDTH = 10;
final int BULLETHEIGHT = 50;

class Bullet {
    /* Insert the code for your Bullet class here.
    You need: variables to store the position aand appearance of the bullet.
    A constructor
    A method to move the bullet
    A method to draw the bullet
    A method to check for collisions
    */
    float xpos;
    float ypos;
    int yvel;
    int xvel;

    Bullet(float xpos, float ypos, int yvel){
        this.xpos = xpos;
        this.ypos = ypos;
        this.yvel = yvel;
        if((int)random(2) == 0)
            this.xvel = -10;
        else
            this.xvel = 10;
    }

    boolean collide(Alien[] aliens){
        for(Alien alien : aliens){
            if(alien.ypos + alien.alienImage.height >= ypos
                && alien.ypos <= ypos
                && alien.xpos <= xpos && alien.xpos + alien.alienImage.height >= xpos
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
        xpos += random(-10,10);
    }

    void movePongy(){
        ypos -= 10;
        if(xpos <= 0) xvel = 10;
        else if (xpos + BULLETWIDTH >= SCREENX) xvel = -10;

        xpos += xvel;
    }

    void draw(){
        fill(0, 210, 0);
        rect(xpos, ypos, BULLETWIDTH, BULLETHEIGHT);
    }
}
