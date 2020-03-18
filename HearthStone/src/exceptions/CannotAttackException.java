//current minion cannot be used to attack:if the minion is sleeping or has already been used to attack this turn
package exceptions;

public class CannotAttackException extends HearthstoneException{

	public CannotAttackException() {
	super();
	}

	public CannotAttackException(String message) {
		super(message);
		
	}

}
