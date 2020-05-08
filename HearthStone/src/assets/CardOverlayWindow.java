package assets;

import java.awt.*;
import javax.swing.*;

import model.cards.Card;
import net.miginfocom.swing.MigLayout;

public class CardOverlayWindow extends JFrame{
    	ImagePanel img;
	 
    	public CardOverlayWindow() {
	        this.setTitle("Transparent Window");
	        this.setUndecorated(true);
	        this.setBackground(new Color(0, 0, 0, 0));

	        this.setAlwaysOnTop(true);
//			TODO hides the window from the taskbar
	        //	        this.setType(Type.UTILITY);
	        
	        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
	        
	        
	        this.setLayout(new MigLayout("fill"));
	     
	        img = new ImagePanel("resources/images/JainaProudmoore.gif");
	        img.setOpaque(false);
	        img.setBackground(new Color(0,0,0,0));

	        
	        this.getContentPane().add(img , "grow, w 286, h 395");
	        

	        
	        
	        
	        
	        this.setBounds(500, 500, 0, 0);
	        this.pack();
	        this.setVisible(false);
	    }
	 
		public void setImage(Card card) {
			this.img = new ImagePanel(card.getCardImage());
			this.getContentPane().removeAll();
			this.getContentPane().add(img , "grow, w 286, h 395");
		}
	 
	 public void updateLocation(CardButton btn) {
// 		this window location is based on the btn location on screen
		 
		 java.awt.Point pt = btn.getLocationOnScreen();
		 this.setBounds(pt.x - 100, pt.y - 420   , 0, 0);
				 
		 
	     this.pack();
	     this.repaint();
	     this.revalidate();
	 }

	public void showoverlay(boolean b) {
		this.setVisible(b);
		this.pack();
    }


}
	 

