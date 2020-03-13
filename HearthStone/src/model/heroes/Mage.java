package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
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
		
		//Accessing the HeroDeck
		ArrayList<Card> HeroDeck = super.getDeck();
				

		HeroDeck.addAll(getNeutralMinions(minions, 13)); 
		
		HeroDeck.add(new Polymorph());
		HeroDeck.add(new Polymorph());
		
		HeroDeck.add(new Flamestrike());
		HeroDeck.add(new Flamestrike());
		
		HeroDeck.add(new Pyroblast());
		HeroDeck.add(new Pyroblast());
		
		
		HeroDeck.add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false , false ,false));
			
		Collections.shuffle(HeroDeck);

	}

}
