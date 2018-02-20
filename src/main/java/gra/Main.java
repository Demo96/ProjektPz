package gra;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Main {
	public static void main(String[] args) {
		try {
			 
			 UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
			//MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
			// UIManager.setLookAndFeel(new MetalLookAndFeel()); 
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  JFrame.setDefaultLookAndFeelDecorated(true);
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ActionFrame();
			}
		});
	}
}