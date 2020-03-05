package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero{
	public Mage() throws IOException {
		super("Jaina Proudmoore");

	}

	@Override
	public void buildDeck() throws IOException {

		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		

		deck.addAll(getNeutralMinions(minions, 13)); 
		
		deck.add(new Polymorph());
		deck.add(new Polymorph());
		
		deck.add(new Flamestrike());
		deck.add(new Flamestrike());
		
		deck.add(new Pyroblast());
		deck.add(new Pyroblast());
		
		
		deck.add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false , false ,false));
			
		Collections.shuffle(deck);

	}

}
