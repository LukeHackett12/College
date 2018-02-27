PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
final int EVENT_NULL=0;
Widget widget1, widget2, widget3;
ArrayList widgetList;

color squareColor;
color borderColor;

void setup(){
  squareColor = color(0, 0, 0);
  borderColor = color(0, 0, 0);
  stdFont = loadFont("ComicSansMS-Bold-48.vlw");
  textFont(stdFont);
  textAlign(CENTER, BOTTOM);

  widget1=new Widget(100, 100, 180, 70,
  "red", color(100),
  stdFont, EVENT_BUTTON1);
  widget2=new Widget(100, 200, 180, 70,
  "green", color(100),
  stdFont, EVENT_BUTTON2);
  widget3=new Widget(100, 300, 180, 70,
  "blue", color(100),
  stdFont, EVENT_BUTTON3);
  size(400, 500);

  widgetList = new ArrayList();
  widgetList.add(widget1); widgetList.add(widget2); widgetList.add(widget3);

}

void draw(){
  background(200, 200, 200);

  for(int i= 0; i<widgetList.size(); i++){
	Widget aWidget = (Widget)widgetList.get(i);
    if (mouseX > aWidget.x && mouseX < aWidget.x + aWidget.width && mouseY > aWidget.y && mouseY < aWidget.y + aWidget.height){
        borderColor = color(255, 255, 255);
    }
    else{
        borderColor = color(0, 0, 0);
    }
    stroke(borderColor);
	aWidget.draw();
  }

  fill(squareColor);
  noStroke();
  rect(10, 10, 80, 80);
}

void mousePressed(){
  int event;

  for(int i = 0; i<widgetList.size(); i++){
	Widget aWidget = (Widget) widgetList.get(i);
       event = aWidget.getEvent(mouseX,mouseY);
       switch(event) {
         case EVENT_BUTTON1:
           println("button 1!");
           squareColor = color(255, 0, 0);
           break;
         case EVENT_BUTTON2:
           println("button 2!");
           squareColor = color(0, 255, 0);
           break;
         case EVENT_BUTTON3:
           println("button 3!");
           squareColor = color(0, 0, 255);
           break;
       }
  }
}
