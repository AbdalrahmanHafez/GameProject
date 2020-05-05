package assets;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class CardOverlayWindow extends JFrame{
	
	 public CardOverlayWindow() {
	        this.setTitle("Transparent Window");
	        this.setUndecorated(true);
	        this.setBackground(new Color(0, 0, 0, 0));
	        this.setSize(new Dimension(300,200));

	        this.setAlwaysOnTop(true);
//		hides the window from the taskbar
	        //	        this.setType(Type.UTILITY);
	        
	        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
	        
	        
	        
	        JPanel pn = new JPanel();
	        pn.setLayout(new MigLayout("fill"));
	        
	        ImagePanel img = new ImagePanel("resources/images/testcard.png");
	        
	        pn.add(img, "grow, w 286, h 395");

	        
	        this.getContentPane().add(pn);
	        
	        this.setBounds(500, 500, 0, 0);
	        this.pack();
	        this.setVisible(false);
	    }
	 
	 public void updateLocation(CardButton btn) {
// 		the window location is based on the btn location on screen
		 
		 java.awt.Point pt = btn.getLocationOnScreen();
		 this.setBounds(pt.x - 100, pt.y - 550   , 0, 0);
				 
		 
	     this.pack();
	     this.repaint();
	     this.revalidate();
	 }

	public void showoverlay(boolean b) {
		this.setVisible(b);
		this.pack();
    }
}
	 

