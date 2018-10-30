package main

import com.eleet.dragonconsole.CommandProcessor
import com.eleet.dragonconsole.DragonConsole
import com.eleet.dragonconsole.DragonConsoleFrame

import java.awt.Color
import java.awt.Font
import java.util.concurrent.LinkedBlockingQueue

class Terminal {
    DragonConsole console
    LinkedBlockingQueue<String> queue
    String textColor

    Terminal(String title) {
        console = new DragonConsole()
        textColor = "&gb"

        console.setBackground(Color.red)
        //console.clearConsole()
        console.font = new Font(Font.MONOSPACED, Font.PLAIN, 12)

        DragonConsoleFrame frame = new DragonConsoleFrame(title, console)
        frame.setResizable(true)
        frame.setVisible(true)

        queue = new LinkedBlockingQueue<>()
        CommandProcessor myCommandProcessor = new MyCommandProcessor()

        console.setCommandProcessor(myCommandProcessor)
    }

    String takeInput() {
        String input = queue.take()
        println("")
        return input
    }

    void print(String text) {
        console.append("$textColor$text")
    }

    void println(String text) {
        console.append("$textColor$text\n")
    }

    void setTextColor(char foreground, char background) {
        textColor = "&$foreground$background"
    }

    void setDefaultColors() {
        textColor = "&gb"
    }


    void setTextSize(int size){
        Font newFont = console.font
        newFont.size = size
        console.setConsoleFont(newFont)
    }

    void clear() {
        console.clearConsole()
    }

    class MyCommandProcessor extends CommandProcessor {
        MyCommandProcessor() {
            super()
            queue = new LinkedBlockingQueue<>()
        }

        @Override
        void processCommand(String input) {
            queue.add(input)
        }
    }
}
