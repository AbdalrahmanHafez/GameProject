package engine;

import model.cards.minions.MinionListener;
import model.heroes.HeroListener;

public interface GameListener extends HeroListener, MinionListener {

	
	
//	 This interface will be used later in
//		Milestone 3
	
	
	public void onGameOver();
	
	
	
	
	
	
	
}
