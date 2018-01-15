void setup(){
  size(640, 640);
  noStroke(); background(0);
}

void draw(){
  fill(255, 0, 0);
  rect(10, 10, 100, 100);  
  fill(0, 255, 0);
  rect(50, 50, 100, 100);
  fill(0, 0, 255);
  rect(100, 100, 100, 100);
}