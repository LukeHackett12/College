Screen screenOne; Screen screenTwo;
Widget forwardButton; Widget backwardButton;
CheckBox checkBox;
RadioButton radioButton;
int screen;
PFont stdFont;

final int EVENT_FORWARD=1;
final int EVENT_BACKWARD=2;
final int TOGGLE=3;

void setup(){
    size(800, 800);
    stdFont = loadFont("ComicSansMS-Bold-48.vlw");
    textFont(stdFont);
    textAlign(CENTER, BOTTOM);

    screenOne = new Screen(color(230, 230, 100));
    screenTwo = new Screen(color(100, 230, 230));
    screen = 1;

    forwardButton=new Widget(250, 200, 300, 100, "forwards", color(100), stdFont, EVENT_FORWARD);
    backwardButton=new Widget(250, 500, 300, 100, "backwards", color(100), stdFont, EVENT_BACKWARD);
    checkBox = new CheckBox(10, 10, 50, 50, color(200), color(10), false);
    String[] array = {""};
    radioButton = new RadioButton(20,20, color (200,200,150), array);

    screenOne.addWidget(forwardButton);
    screenOne.addWidget(backwardButton);
    screenOne.addWidget(checkBox);

    screenTwo.addWidget(forwardButton);
    screenTwo.addWidget(backwardButton);
    screenTwo.addWidget(radioButton);
    //screenTwo.addWidget(radioButton);
}

void mousePressed(){
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

void draw(){
    if(screen == 1){
        screenOne.draw();
    }
    else{
        screenTwo.draw();
    }
}
