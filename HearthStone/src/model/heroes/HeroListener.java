package model.heroes;

import exceptions.*;
import model.cards.minions.MinionListener;


//The Game should be able to listen to hero events.

public interface HeroListener {
	
	public void onHeroDeath();
	
	public void damageOpponent(int amount);


	
	public void endTurn() throws FullHandException, CloneNotSupportedException;
	

	
	
	
	
	
	
	
}
