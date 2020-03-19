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
	
	

	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	
	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}



	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if (!user.equals(currentHero)) {
			throw new NotYourTurnException();
		}
	}



	@Override
	public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		if(!opponent.getField().contains(target)) {
			throw new  InvalidTargetException();
		}
		
		if(!currentHero.getField().contains(attacker)) {
			throw new  CannotAttackException();
		}
		
		
		// minons should not contain any taunt 
		for(Minion minion:opponent.getField()) {
			if(minion.isTaunt()) {
				throw new  TauntBypassException();
			}
		}
		
		
		
		if(!currentHero.getField().contains(attacker) || !currentHero.getField().contains(target) ) {
			throw new  NotSummonedException();
		}
			
		
	}



	@Override
	public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		
		
		
		if(!currentHero.getField().contains(attacker) ) {
			throw new  NotSummonedException();
		}
		
		
		
		
		
	}



	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
