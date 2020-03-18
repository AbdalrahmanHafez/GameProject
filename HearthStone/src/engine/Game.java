package engine;

import model.heroes.Hero;

import java.io.IOException;
import java.util.Random;
public class Game {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;

	public Game(Hero p1, Hero p2)
	{
		firstHero  = p1;
		secondHero = p2;
		
		Double x = Math.random();
		if(x >= 0.5) {
			currentHero = firstHero;
			opponent = secondHero;
		}
		else {
			currentHero = secondHero;
			opponent = firstHero;
		}
		
		firstHero.setTotalManaCrystals(1);
		firstHero.setCurrentManaCrystals(1);

	}
	
	
	public Hero getCurrentHero() {
		return currentHero;
	}
	public Hero getOpponent() {
		return opponent;
	}

}
