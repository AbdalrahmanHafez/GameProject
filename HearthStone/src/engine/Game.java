package engine;

import static org.junit.Assert.fail;

import java.io.IOException;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;
import model.heroes.Paladin;

public class Game implements ActionValidator, HeroListener {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	
	private GameListener listener;
		

	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException
	{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		
//		in order to listen to whatever hero screams
		currentHero.setListener(this);
		opponent.setListener(this);
//		to validate
		currentHero.setValidator(this);
		opponent.setValidator(this);
		
		currentHero.drawCard();
		currentHero.drawCard();
		currentHero.drawCard();
		
		opponent.drawCard();
		opponent.drawCard();
		opponent.drawCard();
		opponent.drawCard();
		
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		
		opponent.setCurrentManaCrystals(0);
		opponent.setTotalManaCrystals(0);
		
		
		
	}
	
	

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if (!user.equals(currentHero)) {
			throw new NotYourTurnException();
		}
	}



	@Override
	public void validateAttack(Minion attacker, Minion target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		
		if(currentHero.getField().contains(target)) {
			throw new InvalidTargetException();
		}
		
		if(!currentHero.getField().contains(attacker)) {
			throw new  NotSummonedException();
		}
		
		
		if(!opponent.getField().contains(target)) {
			throw new  NotSummonedException();
		}
		
	
		
		
		
		if(!target.isTaunt()) {
			for(Minion minion : opponent.getField()) {
				if(minion.isTaunt()) {
					throw new TauntBypassException();
				}
			}
		}
		
		if(attacker.isAttacked() == true || attacker.getAttack() == 0) {
			throw new  CannotAttackException();
		}
		
	}

	


	@Override
	public void validateAttack(Minion attacker, Hero target) throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		
		if (target == currentHero) {
			throw new  InvalidTargetException();
		}
		
//		icwhowl can't attack hero, unless he got polymorphed..
		if(attacker instanceof Icehowl) {
			if (attacker.getName() == "Sheep") {}
				else {
					throw new InvalidTargetException();}
		}
		
		if(attacker.isSleeping())
			throw new CannotAttackException();
		
		
		for(Minion minion : opponent.getField()) {
			if(minion.isTaunt()) {
				throw new TauntBypassException();
			}
		}
		
		if(attacker.isAttacked() == true || attacker.getAttack() == 0) {
			throw new  CannotAttackException();
		}
		
		if(!currentHero.getField().contains(attacker) ) {
			throw new  NotSummonedException();
		}
		
	}



	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		
		if(currentHero.getCurrentManaCrystals() < card.getManaCost()) {
			throw new NotEnoughManaException();
		}
		
		
		
	}


	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		if(currentHero.getField().size() >= 7) {
			throw new FullFieldException();
		}
		
		
		
		
	}


	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
	
		if(hero.isHeroPowerUsed()) {
			throw new HeroPowerAlreadyUsedException();
		}
		
		if(hero.getCurrentManaCrystals() < 2 || (hero instanceof Paladin && hero.getCurrentManaCrystals() < 1 )) {
			throw new NotEnoughManaException();
		}
		
		
		
		
	}

	
	@Override
	public void onHeroDeath() {
		listener.onGameOver();

			
	}



	@Override
	public void damageOpponent(int amount) {
		opponent.setCurrentHP(opponent.getCurrentHP() - amount);
		
	}
	


// endTurn() Method is triggered once a hero ends his turn. It is responsible for performing all actions needed
//		upon ending a turn. The following needs to be performed in order: The turn of the hero should be
//		switched. The current and total mana crystals of the current hero should be updated according
//		to the game rules. The hero power usage should be reset. All minions of the current hero should
//		have their attack usage reset and wake up (if asleep). Finally, the current hero should draw a
//		card from his deck(the functionality of drawing a card will be mentioned later in section 4.1).
//		PAGE 3
	
	@Override
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		Hero temp = currentHero;
		currentHero = opponent;
		opponent = temp;
		

		
		currentHero.setHeroPowerUsed(false);
		
		
		for(Minion minion: currentHero.getField()) {
			minion.setSleeping(false);
			minion.setAttacked(false);
		}
		
		
//		Clone not supported may arise here
		currentHero.drawCard();

		
		
		currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals() +1 );
		currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
		
		
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
