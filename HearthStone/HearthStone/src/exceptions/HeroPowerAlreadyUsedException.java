//when a hero attempts to use their hero power more than once per turn.
package exceptions;

public class HeroPowerAlreadyUsedException extends HearthstoneException{

	public HeroPowerAlreadyUsedException() {
		
	}

	public HeroPowerAlreadyUsedException(String s) {
		super(s);
	}

}
