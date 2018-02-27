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

public class ManyScreens extends PApplet {

Screen screenOne; Screen screenTwo;
Widget forwardButton; Widget backwardButton;
CheckBox checkBox;
RadioButton radioButton;
int screen;
PFont stdFont;

final int EVENT_FORWARD=1;
final int EVENT_BACKWARD=2;
final int TOGGLE=3;

public void setup(){
    
    stdFont = loadFont("ComicSansMS-Bold-48.vlw");
    textFont(stdFont);
    textAlign(CENTER, BOTTOM);

    screenOne = new Screen(color(230, 230, 100));
    screenTwo = new Screen(color(100, 230, 230));
    screen = 1;

    forwardButton=new Widget(250, 200, 300, 100, "forwards", color(100), stdFont, EVENT_FORWARD);
    backwardButton=new Widget(250, 500, 300, 100, "backwards", color(100), stdFont, EVENT_BACKWARD);
    checkBox = new CheckBox(10, 10, 50, 50, color(200), color(10), false);
    String[] array = {"Feck", "u", "Ellen", "my", "check", "box", "is", "real"};
    radioButton = new RadioButton(20,20, color (200,200,150), array);

    screenOne.addWidget(forwardButton);
    screenOne.addWidget(backwardButton);
    screenOne.addWidget(checkBox);

    screenTwo.addWidget(forwardButton);
    screenTwo.addWidget(backwardButton);
    screenTwo.addWidget(radioButton);
    //screenTwo.addWidget(radioButton);
}

public void mousePressed(){
    if(screen == 1){
        int event = screenOne.getEvent();
        switch(event) {
        case EVENT_FORWARD:
            screen = 2;
            break;
        case EVENT_BACKWARD:
            println("You can't go back");
            break;
        case TOGGLE:
            checkBox.eventBool = !checkBox.eventBool;
      }
    }
    else{
        int event = screenTwo.getEvent();
        if(event == EVENT_BACKWARD){
          screen = 1;
        }
        else if(event == EVENT_FORWARD){
          println("You can't go forwards");
        }
        else{
          for(int i = 0; i < radioButton.choices.length; i++){
            if(event-3 == i){
              radioButton.circleButtons[i].eventBool = true;
            }
            else if(event - 3 >= 0){
              radioButton.circleButtons[i].eventBool = false;
            }
          }
        }
    }
}

public void draw(){
    if(screen == 1){
        screenOne.draw();
    }
    else{
        screenTwo.draw();
    }
}
class Screen{
    int backgroundColor;
    ArrayList<Widget> widgetList;

    Screen(int backgroundColor){
        this.backgroundColor = backgroundColor;
        widgetList = new ArrayList<Widget>();
    }

    public int getEvent(){
        for(Widget widget : widgetList){
            int event = widget.getEvent(mouseX,mouseY);
            if(event != 0) return event;
        }
        return 0;
    }

    public void draw(){
        background(backgroundColor);
        if(!widgetList.isEmpty()){
            for(Widget widget : widgetList){
                int borderColor;
                if (mouseX > widget.x && mouseX < widget.x + widget.width && mouseY > widget.y && mouseY < widget.y + widget.height){
                    borderColor = color(255, 255, 255);
                }
                else{
                    borderColor = color(0, 0, 0);
                }
                stroke(borderColor);
                widget.draw();
            }
        }
    }

    public void addWidget(Widget widget){
        widgetList.add(widget);
    }
}
class Widget {
  float x, y, width, height;
  String label; int event;
  int widgetColor, labelColor;
  PFont widgetFont;
  int type;

  Widget(){

  }

  Widget(int x,int y, int width, int height, String label,
  int widgetColor, PFont widgetFont, int event){
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.label=label; this.event=event;
    this.widgetColor=widgetColor; this.widgetFont=widgetFont;
    labelColor= color(0);
    type = 0;
   }

  public void draw(){
    strokeWeight(1);
    fill(widgetColor);
    rect(x,y,width,height);
    fill(labelColor);
    textAlign(CENTER, BOTTOM);
    text(label, x+width/2, y+height);
  }

  public int getEvent(int mX, int mY){
     if(mX>x && mX < x+width && mY >y && mY <y+height){
        return event;
     }
     return 0;
  }
}


class CheckBox extends Widget{
  boolean eventBool;
  int selectedColor;

  CheckBox(float x,float y, int width, int height, int widgetColor, int selectedColor, boolean eventBool){
    //super(x, y, width, height, label, widgetColor, widgetFont, event);
    super();
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.widgetColor=widgetColor;
    this.selectedColor = selectedColor;
    this.eventBool = eventBool;
    type = 1;
  }

  public int getEvent(int mX, int mY){
     if(mX>x && mX < x+width && mY >y && mY <y+height){
        return 3;
     }
     return 0;
  }

  public void draw(){
    stroke(255, 255, 255);
    strokeWeight(10);
    if(eventBool == false){
      fill(widgetColor);
      rect(x,y,width,height);
    }
    else{
      fill(selectedColor);
      rect(x,y,width,height);
    }
  }
}

class RadioButton extends Widget{
  int backgroundColor;
  String[] choices;

  CheckBox[] circleButtons;

  RadioButton(float x,float y, int backgroundColor, String[] choices){
    super();
    this.x=x; this.y=y; height = choices.length*20;
    this.backgroundColor=backgroundColor;
    this.choices = choices;

    circleButtons = new CheckBox[choices.length];
    boolean start = true;
    float tempY = y + 10;
    for(int i=0;i < circleButtons.length; i++){
      circleButtons[i] = new CheckBox(x, tempY, 30, 30, color(255, 255, 255), color(20, 20, 20), start);
      start = false;
      tempY += 40;
    }
  }

  public int getEvent(int mX, int mY){
    for(int i = 0; i < circleButtons.length; i++){
       if(mX>circleButtons[i].x && mX < circleButtons[i].x+30 && mY > circleButtons[i].y && mY < circleButtons[i].y+30){
         int temp = 3+i;
         return temp;
       }
     }
     return 0;
  }

  public void draw(){ //<>//
    fill(backgroundColor); //<>//
    noStroke();
    //rect(x, y, width, height);
    float tempY = y + 7;
    for(int i=0;i < circleButtons.length; i++){
      circleButtons[i].draw();
      textAlign(CORNER, TOP);
      text(choices[i], x+40, tempY);
      tempY += 40;
    }
  }
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ManyScreens" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
