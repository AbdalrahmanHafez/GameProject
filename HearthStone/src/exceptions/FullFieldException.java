package exceptions;

import model.cards.Card;

public class FullFieldException extends HearthstoneException{
	
	public FullFieldException() {
		
	}

	public FullFieldException(String s) {
		super(s);
	}

}
