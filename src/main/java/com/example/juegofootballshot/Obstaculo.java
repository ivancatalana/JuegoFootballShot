package com.example.juegofootballshot;

import javax.swing.*;
import java.awt.*;

public class Obstaculo extends JPanel {
    private int x = 900;
    private int y = 200;
    private int radius = 20;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Circle");
        Obstaculo movingCircle = new Obstaculo();
        frame.add(movingCircle);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        int angle = 0;
        int speed = 5;
        int radius = 5;
        while (true) {
            angle += speed;
            movingCircle.x = (int) (movingCircle.x - speed);
            movingCircle.y = (int) (movingCircle.y + radius * Math.sin(Math.toRadians(angle)));
            movingCircle.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
