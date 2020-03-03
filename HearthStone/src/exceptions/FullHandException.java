package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException{
	
	private Card burned;  			//The card that will be burned (discarded) as the player’s hand is already full.
	
	public FullHandException(Card b) {
		
	}

	public FullHandException(String s,Card b) {
		super(s);
	}

}
