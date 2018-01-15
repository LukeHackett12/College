int i = 0;
int j = 600;
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
  fill(0, 255, 0);
  rect(j--, 400, width, height);
  
  if(i == 600) i = 0;
  else if(i + width > 600){
    fill(255, 0, 0);
    rect(0, 100, (i+width)-600, height);
  }
  
  if(j == 0 - width) j = 600 - width;
  else if(j < 0){
    fill(0, 255, 0);
    rect(0, 400, (j+width), height);
    rect(j+600, 400, width, height);
  }
}