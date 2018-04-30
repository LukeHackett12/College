import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Drawing extends JFrame implements ActionListener, MouseMotionListener {
    //JFrame frame = new JFrame();
    JPanel panel;
    int xPosOval;
    int xPosImage;
    int yPosOval;
    int yPosImage;
    BufferedImage image;

    public Drawing() {
        super("Ben");
        super.setSize(800, 600);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new GPanel();
        super.addMouseMotionListener(this);

        JButton swapButton = new JButton("Swap");
        JButton centerButton = new JButton("Center");
        JButton benButton = new JButton("Ben");

        xPosOval = 200;
        yPosOval = 200;
        xPosImage = 400;
        yPosImage = 400;

        swapButton.setActionCommand("Swap");
        centerButton.setActionCommand("Center");
        benButton.setActionCommand("Ben");

        swapButton.addActionListener(this);
        centerButton.addActionListener(this);
        benButton.addActionListener(this);

        panel.add(swapButton);
        panel.add(centerButton);
        panel.add(benButton);

        try {
            image = ImageIO.read(new File("C:\\Users\\lukeh\\Downloads\\java-logo.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        super.add(panel);
    }

    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        drawing.setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
        //TODO implement if mouse is over the oval before moving
        xPosOval =e.getX();
        yPosOval=e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Swap")){
            int tempX = xPosOval;
            int tempY = yPosOval;

            xPosOval = xPosImage;
            yPosOval = yPosImage;
            xPosImage = tempX;
            yPosImage = tempY;

            repaint();
            System.out.println(command);
        }
        else if(command.equals("Center")){
            xPosOval = 400 - 60;
            yPosOval = 300 - 60;
            xPosImage = 400 - 60;
            yPosImage = 300 - 60;

            repaint();
            System.out.println(command);
        }
        //TODO if center move oval to center, if swap swap with pic, and if name then do trick
    }


    class GPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            //rectangle originates at 10,10 and ends at 240,240
            g.setColor(Color.DARK_GRAY);
            g.fillRect(50, 50, 700, 500);
            //filled Rectangle with rounded corners.
            g.setColor(Color.RED);
            g.fillOval(xPosOval, yPosOval, 120, 120);
            g.drawImage(image, xPosImage, yPosImage, this);
        }
    }
}
/*
public class Drawing{
    private JFrame mainFrame;
    private JPanel controlPanel;

    public Drawing(){
        prepareGUI();
    }

    public static void main(String[] args){
        Drawing drawing = new Drawing();
        drawing.setupGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Ben");
        mainFrame.setSize(800,600);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        //mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        //mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }
    private void setupGUI(){

        JButton swapButton = new JButton("Swap");
        JButton centerButton = new JButton("Center");
        JButton benButton = new JButton("Ben");

        swapButton.setActionCommand("OK");
        centerButton.setActionCommand("Submit");
        benButton.setActionCommand("Cancel");

        swapButton.addActionListener(new ButtonClickListener());
        centerButton.addActionListener(new ButtonClickListener());
        benButton.addActionListener(new ButtonClickListener());

        controlPanel.add(swapButton);
        controlPanel.add(centerButton);
        controlPanel.add(benButton);

        mainFrame.setVisible(true);
    }
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println(command);
        }
    }
}
*/