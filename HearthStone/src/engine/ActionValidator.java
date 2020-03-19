package engine;

import exceptions.CannotAttackException;
import exceptions.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public interface ActionValidator {

	public void validateTurn(Hero user) throws NotYourTurnException;
	
	public void validateAttack(Minion attacker,Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException,InvalidTargetException;
	
	public void validateAttack(Minion attacker,Hero target) throws 	CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException;
	
	
	
}
