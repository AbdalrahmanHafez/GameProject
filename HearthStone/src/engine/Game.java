package engine;

import model.heroes.Hero;

import java.util.Random;
public class Game {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	public Game() {
		// TODO Auto-generated constructor stub
	}
	
	public Game(Hero p1, Hero p2) {
		
		Double x = Math.random();
		if(x >= 0.5) {
			firstHero  = p1;
			secondHero = p2;
		}
		else {
			firstHero  = p2;
			secondHero = p1;
		}
	}
	public Hero getCurrentHero() {
		return currentHero;
	}
	public Hero getOpponent() {
		return opponent;
	}

}
