package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assets.CardButton;
import model.cards.Card;

public interface ImageButtonListener extends ActionListener{
	public void updatetxtCardInfo(Card card);
	public void updateCardOverlay(CardButton btn , boolean show);
	
	
}
