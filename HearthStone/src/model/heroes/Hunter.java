package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

public class Hunter extends  Hero{

	public Hunter() throws IOException {
		super("Rexxar");
	}

	@Override
	public void buildDeck() throws IOException {
		
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		
		//Accessing the HeroDeck
		ArrayList<Card> HeroDeck = super.getDeck();
		
		HeroDeck.addAll((getNeutralMinions(minions, 15))); // gets 15 nurtal minions
		
		HeroDeck.add(new KillCommand());
		HeroDeck.add(new KillCommand());
		
		HeroDeck.add(new MultiShot());
		HeroDeck.add(new MultiShot());
		
		HeroDeck.add(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false , false ,true));
					
		Collections.shuffle(HeroDeck);

		
	}


}
