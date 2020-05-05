package assets;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;

public class CardButton extends ImageButton{
	private boolean showOverlay = true;
	protected boolean isinField; //meaning in the current hero field only
	private Card card;
	public boolean hightlighted = false;
	private CardButton attackedBy = null;
	

	
	public CardButton(boolean h, boolean showOverlay, boolean clickable)	{
		
		super(120, 150, clickable);
	

		this.hidden = h;
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
					 CardButton btn =  ((CardButton) e.getSource());
					 if(!btn.isHidden()) {
						 try{
								
								btn.listener.updatetxtCardInfo(btn.getCard());					 }catch(Exception ee) {;}
						 		btn.listener.updateCardOverlay(btn, true);
					 }
					
					 border_activate();
					 if(showOverlay)
						 	btn.setImage("resources/images/uparrow.png");

					 
				 }
				public void mouseExited(MouseEvent e) {
					 CardButton btn =  ((CardButton) e.getSource());
					 btn.listener.updateCardOverlay(btn, false);
					 border_deactivate();
					
					(btn).setImage(btn.imageDefualtPath);
		         }
		      });
		}
	
	
		
		
    private void border_activate() {
    	if(!hightlighted) {
    		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
    	}else {
    		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
    	}
    }
    private void border_deactivate() {
    	if(!hightlighted) {
    		this.setBorder(null);
    	}else {
    		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
    	}
    }

	public void setHighlightMode() {
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		this.hightlighted = true;
	}
	public void setHighlightRemove() {
		this.hightlighted = false;
	}

	
	
	public void setText(String txt) {
		this.Label.setText(txt);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("card butt trig");
		if( clickable && !hidden)
			super.listener.actionPerformed(e);
	}
	
	



	
	public Card getCard() {
		return card;
	}
	public void setinField(boolean inField) {
		this.isinField = inField;
	}
	
	public boolean isShowOverlay() {
		return showOverlay;
	}
	public void setShowOverlay(boolean showOverlay) {
		this.showOverlay = showOverlay;
	}
	

	public boolean isinField() {
		return isinField;
	}
	public CardButton getAttackedBy() {
		return attackedBy;
	}
	public void setAttackedBy(CardButton attackedBy) {
		this.attackedBy = attackedBy;
	}
	
}
