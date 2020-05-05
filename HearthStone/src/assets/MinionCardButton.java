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

		setDefultImage("resources/images/Cards/minion.png");
		setImage("resources/images/Cards/minion.png");
		this.setActionCommand("minionplay");
	

		
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
