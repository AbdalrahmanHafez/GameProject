package assets;

import java.awt.BorderLayout;
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
import model.cards.spells.Spell;

public class CardButton extends ImageButton{
	protected boolean hidden = false;
	private boolean showOverlay = true;
	protected boolean isinField; //meaning in the current hero field only

	CardButton target =null;
	


	
	public CardButton(boolean h, boolean showOverlay, boolean clickable)
	{
		
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
					 ((ImageButton) me.getSource()).setImage("resources/images/default.png");
		         }

		      });
		}
		
		
	
    private void border_activate() {
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
		
	}
	private void border_deactivate() {
		this.setBorder(null);

	}

	
	
	public void setText(String txt) {
		this.Label.setText(txt);
	}

	
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try {
			if(hidden) {g.drawImage(ImageIO.read(new File("resources/images/qmark.png")), 0, 0, null);return;}
			
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

	public CardButton getTarget() {
		return target;
	}
	public void setTarget(CardButton target) {
		this.target = target;
	}
	public boolean isinField() {
		return isinField;
	}

	
}
