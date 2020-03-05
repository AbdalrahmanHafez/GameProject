package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;


public class Hunter extends  Hero{

	public Hunter() throws IOException {
		super("Rexxar");
	}

	@Override
	public void buildDeck() throws IOException {
		
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		
		
		deck.addAll((getNeutralMinions(minions, 15))); // gets 15 nurtal minions
		
		deck.add(new KillCommand());
		deck.add(new KillCommand());
		
		deck.add(new MultiShot());
		deck.add(new MultiShot());
		
		deck.add(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false , false ,true));
					
		Collections.shuffle(deck);

		
	}


}
