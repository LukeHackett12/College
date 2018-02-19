class Bomb{
    float xpos; float ypos;

    Bomb(float xpos, float ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    boolean collide(Player player){
        if(player.ypos + player.player.height >= ypos
            && player.ypos <= ypos
            && player.xpos <= xpos && player.xpos + player.player.height >= xpos){
                return true;
            }
            return false;
    }

    void move(){
        ypos += 10;
    }

    void draw(){
        fill(255, 0, 0);
        ellipse(xpos, ypos, 50, 50);
    }
}
