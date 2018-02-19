class Barrier{
    float xpos; float ypos;
    int height; int width;

    Barrier(float xpos, float ypos){
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = BARRIERHEIGHT;
        this.width = BARRIERWIDTH;
    }

    boolean destruct(Bullet bullet){
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

    boolean destruct(Bomb bomb){
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

    void draw(){
        fill(0, 0, 255);
        rect(xpos, ypos, width, height);
    }
}
