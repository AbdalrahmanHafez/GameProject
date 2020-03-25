package model.heroes;

import exceptions.*;


//The Game should be able to listen to hero events.

public interface HeroListener {
	
	public void onHeroDeath();
	
	public void damageOpponent(int amount);
	
	public void endTurn() throws FullHandException, CloneNotSupportedException;
	
//	TODO endTurn() Method is triggered once a hero ends his turn. It is responsible for performing all actions needed
//	upon ending a turn. The following needs to be performed in order: The turn of the hero should be
//	switched. The current and total mana crystals of the current hero should be updated according
//	to the game rules. The hero power usage should be reset. All minions of the current hero should
//	have their attack usage reset and wake up (if asleep). Finally, the current hero should draw a
//	card from his deck(the functionality of drawing a card will be mentioned later in section 4.1).
//	PAGE 3
	
	
	
	
	
	
	
}
