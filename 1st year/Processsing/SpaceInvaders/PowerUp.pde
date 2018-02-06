class PowerUp{
    float xpos; float ypos;
    int type;

    PowerUp(float xpos, float ypos, int type){
        this.xpos = xpos;
        this.ypos = ypos;
        this.type = type+1;
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
