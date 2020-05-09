package assets;

import java.awt.*;
import javax.swing.*;

import model.cards.Card;
import net.miginfocom.swing.MigLayout;

public class CardOverlayWindow extends JFrame{
    	ImagePanel img;
	 
    	public CardOverlayWindow() {
	        this.setTitle("CardOverlayWindow");
	        this.setUndecorated(true);
	        this.setBackground(new Color(0, 0, 0, 0));

//	        this.setAlwaysOnTop(true);
	        this.setType(Type.UTILITY);
	        
	        this.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
	        
	        
	        this.setLayout(new MigLayout("fill"));
	        
	        this.setBounds(500, 500, 0, 0);
	        this.pack();
	        this.setVisible(false);
	    }
	 
		public void setImage(Card card) {
			this.img = new ImagePanel(card.getCardImage());
	        img.setOpaque(false);
	        img.setBackground(new Color(0,0,0,0));

			this.getContentPane().removeAll();
			this.getContentPane().add(img , "grow, w 286, h 395");
			this.repaint();
			this.revalidate();
		}
	 
	 public void updateLocation(CardButton btn, boolean inverted) {
// 		this window location is based on the btn location on screen

		 java.awt.Point pt = btn.getLocationOnScreen();
		 if(!inverted)
			 this.setBounds(pt.x - 85, pt.y - 400   , 0, 0);
		 else
			 this.setBounds(pt.x - 85, pt.y + 150   , 0, 0);
		 
	     this.pack();
	     this.repaint();
	     this.revalidate();
	 }

	public void showoverlay(boolean b) {
		this.setVisible(b);
		this.pack();
    }


}
	 

