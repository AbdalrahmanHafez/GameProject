//when trying to do an action with a wrong target (Hint: think about Icehowl).
package exceptions;

public class InvalidTargetException extends HearthstoneException{

	public InvalidTargetException() {
	
	}

	public InvalidTargetException(String message) {
		super(message);
		
	}
	

}
