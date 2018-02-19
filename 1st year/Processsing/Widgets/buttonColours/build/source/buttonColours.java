import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class buttonColours extends PApplet {

PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
final int EVENT_NULL=0;
Widget widget1, widget2, widget3;
ArrayList widgetList;

int squareColor;
int borderColor;

public void setup(){
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
  

  widgetList = new ArrayList();
  widgetList.add(widget1); widgetList.add(widget2); widgetList.add(widget3);

}

public void draw(){
  background(200, 200, 200);

  for(int i = 0; i<widgetList.size(); i++){
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

public void mousePressed(){
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
class Widget {
  int x, y, width, height;
  String label; int event;
  int widgetColor, labelColor;
  PFont widgetFont;

  Widget(int x,int y, int width, int height, String label,
  int widgetColor, PFont widgetFont, int event){
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.label=label; this.event=event;
    this.widgetColor=widgetColor; this.widgetFont=widgetFont;
    labelColor= color(0);
   }
  public void draw(){
    fill(widgetColor);
    rect(x,y,width,height);
    fill(labelColor);
    text(label, x+width/2, y+height/2);
  }
  public int getEvent(int mX, int mY){
     if(mX>x && mX < x+width && mY >y && mY <y+height){
        return event;
     }
     return EVENT_NULL;
  }
}
  public void settings() {  size(400, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "buttonColours" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
