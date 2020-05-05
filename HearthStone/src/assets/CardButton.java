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
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;

public class CardButton extends ImageButton{
	protected boolean hidden = false;
	private boolean showOverlay = true;
	protected boolean isinField; //meaning in the current hero field only
	private Card card;
	public boolean hightlighted = false;
	private CardButton attackedBy = null;
	

	public CardButton(boolean h, boolean showOverlay, boolean clickable)	{
		
		super(120, 150, clickable);
	

		this.hidden = h;
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent me) {
					 border_activate();
					 if(showOverlay)
						 ((ImageButton) me.getSource()).setImage("resources/images/uparrow.png");
				 }
				public void mouseExited(MouseEvent me) {
					border_deactivate();
					((ImageButton) me.getSource()).setImage(((CardButton)me.getSource()).imageDefultPath);
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

	
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			if(hidden) {g.drawImage(ImageIO.read(new File("resources/images/hiddencard.png")), 0, 0, null);return;}
			
			g.drawImage(ImageIO.read(new File(super.imagePath)), 0, 0, null);
		} catch (IOException e) {
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("card butt trig");
		if( clickable && !hidden)
			super.listener.actionPerformed(e);
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
	
	public void setHidden(boolean h) {
		this.hidden = h; 
	}
	public boolean isHidden() {
		return this.hidden; 
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
