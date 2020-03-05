package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Paladin extends Hero {

	public Paladin() throws IOException {
		super("Uther Lightbringer");
		
	}

	@Override
	public void buildDeck() throws IOException {
		

		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		
		deck.addAll(getNeutralMinions(minions, 15)); 
		
		deck.add(new SealOfChampions());
		deck.add(new SealOfChampions());
		
		deck.add(new LevelUp());
		deck.add(new LevelUp());
		
		deck.add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false));
					
		Collections.shuffle(deck);

	}


}
