package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
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
		
		//Accessing the HeroDeck
		ArrayList<Card> HeroDeck = super.getDeck();
			
		HeroDeck.addAll(getNeutralMinions(minions, 15)); 
		
		HeroDeck.add(new SealOfChampions());
		HeroDeck.add(new SealOfChampions());
		
		HeroDeck.add(new LevelUp());
		HeroDeck.add(new LevelUp());
		
		HeroDeck.add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false));
					
		Collections.shuffle(HeroDeck);

	}


}
