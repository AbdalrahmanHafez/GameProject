package engine;

import model.heroes.Hero;

import java.io.IOException;
import java.util.Random;
public class Game {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;

	public Game(Hero p1, Hero p2) throws IOException {
		p1.buildDeck();
		p2.buildDeck();
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
	}
	
	
	public Hero getCurrentHero() {
		return currentHero;
	}
	public Hero getOpponent() {
		return opponent;
	}

}
