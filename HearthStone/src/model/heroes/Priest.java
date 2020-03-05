package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Priest extends Hero{
	

	public Priest() throws IOException {
		super("Anduin Wrynn");

	}

	@Override
	public void buildDeck() throws IOException {
		
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");

		
		deck.addAll(getNeutralMinions(minions, 13)); 
		
		deck.add(new DivineSpirit());
		deck.add(new DivineSpirit());
		
		deck.add(new HolyNova());
		deck.add(new HolyNova());
		
		deck.add(new ShadowWordDeath());
		deck.add(new ShadowWordDeath());
						
		deck.add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false));
		
		Collections.shuffle(deck);

	}

}
