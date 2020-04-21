package assets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;

import model.cards.Card;
import model.cards.minions.Minion;



public class MinionCardButton extends CardButton implements ActionListener{
	
	private Minion card;
	

	public MinionCardButton(boolean h, boolean showOverlay, boolean clickable) {
		super(h, showOverlay, clickable);

		setImage("resources/images/Cards/minion.png");
		this.setActionCommand("minionplay");
	
		
		this.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent me) {
				 ((ImageButton) me.getSource()).setImage("resources/images/Cards/minion.png");
	         }
		      });
		
	}


	
	public void setTheCard(Minion card) {
//		TODO setCradButton
		this.card = card;
		this.setImage(super.imagePath);
		if(super.hidden) {
			this.setText("");
		}else {
			this.setText(card.getName());
			
		}
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
				System.out.println("minion card trig AC: " + e.getActionCommand());
				listener.actionPerformed(e);
	}
	
	@Override
	public void setinField(boolean inField) {
		if(inField) {
			this.setActionCommand("minionattack");
		}
		super.isinField = inField;
	}
	
	
	
	public Minion getCard() {
		return card;
	}

	
	

}
