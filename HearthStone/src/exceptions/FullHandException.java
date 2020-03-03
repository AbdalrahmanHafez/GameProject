package exceptions;

import model.cards.Card;

public class FullHandException extends HearthstoneException{
	
	Card burned;  			//The card that will be burned (discarded) as the player’s hand is already full.
	
	public FullHandException() {
		
	}

	public FullHandException(String s) {
		super(s);
	}

}
