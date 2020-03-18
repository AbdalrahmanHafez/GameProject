//when trying to attack with a minion that is not yet summoned to the field (i.e. still in hand).
package exceptions;

public class NotSummonedException extends HearthstoneException{

	public NotSummonedException() {
	}

	public NotSummonedException(String message) {
		super(message);
		
	}

}
