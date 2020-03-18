//when a hero attempts to perform an action outside their turn.
package exceptions;

public class NotYourTurnException extends HearthstoneException{

	public NotYourTurnException() {
		
	}

	public NotYourTurnException(String message) {
		super(message);
		
	}
	
   
}
