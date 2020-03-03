//occurs when a hero attempts to perform an action with insufficient mana, e.g. playing a card or using hero power.
package exceptions;

public class NotEnoughManaException extends HearthstoneException{

	public NotEnoughManaException() {
		
	}

	public NotEnoughManaException(String s) {
		super(s);
	}
	

}
