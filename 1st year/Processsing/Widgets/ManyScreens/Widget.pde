class Widget {
  float x, y, width, height;
  String label; int event;
  color widgetColor, labelColor;
  PFont widgetFont;
  int type;

  Widget(){

  }

  Widget(int x,int y, int width, int height, String label,
  color widgetColor, PFont widgetFont, int event){
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.label=label; this.event=event;
    this.widgetColor=widgetColor; this.widgetFont=widgetFont;
    labelColor= color(0);
    type = 0;
   }

  void draw(){
    fill(widgetColor);
    rect(x,y,width,height);
    fill(labelColor);
    text(label, x+width/2, y+height);
  }

  int getEvent(int mX, int mY){
     if(mX>x && mX < x+width && mY >y && mY <y+height){
        return event;
     }
     return 0;
  }
}

class CheckBox extends Widget{
  boolean event;
  color selectedColor;

  CheckBox(float x,float y, int width, int height, color widgetColor, color selectedColor, boolean event){
    //super(x, y, width, height, label, widgetColor, widgetFont, event);
    super();
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.widgetColor=widgetColor;
    this.selectedColor = selectedColor;
    this.event = event;
    type = 1;
  }

  int getEvent(int mX, int mY){
     if(mX>x && mX < x+width && mY >y && mY <y+height){
        return 3;
     }
     return 0;
  }

  void draw(){
    stroke(255, 255, 255);
    strokeWeight(5);
    if(event == false){
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
  color backgroundColor;
  String[] choices;

  CheckBox[] checkboxes;

  RadioButton(float x,float y, int width, int height, color backgroundColor, String[] choices){
    super();
    this.x=x; this.y=y; this.width = width; this.height= height;
    this.backgroundColor=backgroundColor;
    this.choices = choices;

    checkboxes = new CheckBox[choices.length];
    boolean start = true;
    float tempY = y + height/(checkboxes.length*2);
    for(int i=0;i < checkboxes.length; i++){
      checkboxes[i] = new CheckBox(x, tempY, 20, 20, color(255, 255, 255), color(20, 20, 20), start);
      start = false;
      tempY += height/checkboxes.length;
    }
  }

  void draw(){
    fill(backgroundColor); //<>// //<>//
    noStroke();
    rect(x, y, width, height);
    for(int i=0;i < checkboxes.length; i++){
      checkboxes[i].draw();
    }
  }
}