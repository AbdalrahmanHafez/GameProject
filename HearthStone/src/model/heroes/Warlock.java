package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
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

		//Accessing the HeroDeck
		ArrayList<Card> HeroDeck = super.getDeck();
				
		
		HeroDeck.addAll(getNeutralMinions(minions, 13)); 
		
		HeroDeck.add(new CurseOfWeakness());
		HeroDeck.add(new CurseOfWeakness());

		
		HeroDeck.add(new SiphonSoul());
		HeroDeck.add(new SiphonSoul());
					
		
		HeroDeck.add(new TwistingNether());
		HeroDeck.add(new TwistingNether());
		
		HeroDeck.add(new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false));

		Collections.shuffle(HeroDeck);

	}
	

}
