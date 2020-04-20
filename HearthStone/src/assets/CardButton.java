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
import javax.swing.BorderFactory;
import javax.swing.JLabel;

import model.cards.Card;

public class CardButton extends ImageButton{
	private boolean hidden = false;
	private boolean showOverlay = true;
	
	private Card card;

	
	public CardButton(boolean h, boolean showOverlay, boolean clickable)
	{
		
		super(120, 150, clickable);
		
		this.addActionListener(this);
		this.setActionCommand("CardButton");
		
		this.hidden = h;
		
		this.addMouseListener(new MouseAdapter() {
		
			
			public void mouseEntered(MouseEvent me) {
					 border_activate();
					 if(showOverlay)
						 ((ImageButton) me.getSource()).setImage("resources/images/uparrow.png");
				 }

				public void mouseExited(MouseEvent me) {
		        	 border_deactivate();
					 ((ImageButton) me.getSource()).setImage("resources/images/Cards/spell.png");
		         }

		      });
		}
		
		
	
    private void border_activate() {
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.green));
		
	}
	private void border_deactivate() {
		this.setBorder(null);

	}

	public void setCard(Card card) {
//		TODO setCradButton
		this.card = card;
		this.setImage("resources/images/Cards/spell.png");
		if(hidden)
			this.setText("");	
		else
			this.setText(card.getName());
			
		
//		if(card instanceof Minion) {
//			if(card instanceof Icehowl) {this.setImage(imgPath); return;}
//			this.setImage(imgPath); return;
//				
//		}
//		
//		if(card instanceof Spell) {
//			
//		}
		
		
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
		if(clickable && !hidden)
			super.listener.actionPerformed(e);
	}
	
	



	
	
	
	public Card getCard() {
		return card;
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


	
}
