package gra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class MyButton2 extends JButton {
	Color kolor;
    public MyButton2(String label,Color c) {
        super(label);
        kolor=c;
    }
 
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (getModel().isArmed()) {
   	 setBackground(kolor);
       } else {
       	 setBackground(Color.LIGHT_GRAY);
       }
    }
}