package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
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

		//Accessing the HeroDeck
		ArrayList<Card> HeroDeck = super.getDeck();
				
		
		HeroDeck.addAll(getNeutralMinions(minions, 13)); 
		
		HeroDeck.add(new DivineSpirit());
		HeroDeck.add(new DivineSpirit());
		
		HeroDeck.add(new HolyNova());
		HeroDeck.add(new HolyNova());
		
		HeroDeck.add(new ShadowWordDeath());
		HeroDeck.add(new ShadowWordDeath());
						
		HeroDeck.add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false));
		
		Collections.shuffle(HeroDeck);

	}

}
