//when a minion is trying to attack a target while the opponent has a taunt minion(s) in their field.
package exceptions;

public class TauntBypassException extends HearthstoneException{

	public TauntBypassException() {
		// TODO Auto-generated constructor stub
	}
	public TauntBypassException(String s) {
		super(s);
	}
}
