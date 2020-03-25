package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Game implements ActionValidator {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	
	private GameListener listener;
		

	public Game(Hero p1, Hero p2)
	{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		
	}
	
	

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if (!user.equals(currentHero)) {
			throw new NotYourTurnException();
		}
	}



	@Override
	public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		// TODO GAME validateAttack
		if(!opponent.getField().contains(target)) {
			throw new  InvalidTargetException();
		}
		
		if(!currentHero.getField().contains(attacker)) {
			throw new  NotSummonedException();
		}
		
		
		// minons should not contain any taunt 
		
		// (FOR EACH) Minion in oponenetField
		for(Minion minion : opponent.getField()) {
			if(minion.isTaunt()) {
				throw new TauntBypassException();
			}
		}
				
		
		if(attacker.getAttack() == 0) {
			throw new  CannotAttackException();
		}
		
		
	}



	@Override
	public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		
		// TODO GAME  validateAttack

		
		if(!currentHero.getField().contains(attacker) ) {
			throw new  NotSummonedException();
		}
		
			}



	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		// TODO GAME  validateManaCost
		
		
	}



	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		// TODO GAME  validatePlayingMinion
		
	}



	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		// TODO GAME  validateUsingHeroPower
		
	}

	
	
	
	
	
	
	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	
	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}



	
	

}
