int i = 0;
int height = 100;
int width = 100;

void setup(){
  size(600, 600);
  noStroke(); background(0);
}

void draw(){
  background(0);
  fill(255, 0, 0);
  rect(i++, 100, width, height);
  
  if(i == 600) i = 0;
  if(i + width > 600){
    rect(0, 100, (i+width)-600, height);
  }
}