int i = 0;
int j = 600;
float sin = 0;
int height = 100;
int width = 100;

void setup(){
  size(600, 600);
  noStroke(); background(0);
}

void draw(){
  background(0);
  fill((sin(sin)*255)%255, i^255, j%255);
  rect(i++, (sin(sin)*100)+100, width, height);
  fill((sin(sin)*255)%255, i^255, j%255);
  rect(j--, (sin(sin)*100)+250, width, height);
 
  if(i == 600) i = 0;
  else if(i + width > 600){
    fill((sin(sin)*255)%255, i^255, j%255);
    rect(0, (sin(sin)*100)+100, (i+width)-600, height);
  }
  
  if(j == 0 - width) j = 600 - width;
  else if(j < 0){
    fill((sin(sin)*255)%255, i^255, j%255);
    rect(0, (sin(sin)*100)+250, (j+width), height);
    rect(j+600, (sin(sin)*100)+250, width, height);
  }
  sin += 0.1;
}