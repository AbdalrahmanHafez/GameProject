package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;


public class Warlock extends Hero{
	public Warlock() throws IOException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");

		deck.addAll(getNeutralMinions(minions, 13)); 
		
		deck.add(new CurseOfWeakness());
		deck.add(new CurseOfWeakness());

		
		deck.add(new SiphonSoul());
		deck.add(new SiphonSoul());
					
		
		deck.add(new TwistingNether());
		deck.add(new TwistingNether());
		
		deck.add(new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false));

		Collections.shuffle(deck);

	}
	

}
