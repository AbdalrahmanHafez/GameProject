package model.heroes;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero {
	public Paladin() throws IOException, CloneNotSupportedException, FullHandException
	{
		super("Uther Lightbringer");
	}
	
	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
//		don;t duplicate a Minion, use clone()
		for(Minion m : neutrals) 
			if(getDeck().contains(m)) 
				getDeck().add((Card) m.clone());
			else 
				getDeck().add(m);
		
			
//		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new SealOfChampions());
			getDeck().add(new LevelUp());
		}
		Minion tirion=new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
	
		getDeck().add(tirion);
		Collections.shuffle(getDeck());
		
//		Hero listens to The minion screams
		for(Card c : this.getDeck()) 
			if(c instanceof Minion) {
				((Minion)c).setListener(this);
			}
//	
	}
	
	public static void main(String[] args) throws Exception{
		for(int i = 0 ; i < 100;i++){
			Paladin justahn = new Paladin();
			Set<Card> set = new HashSet<Card>(justahn.getDeck());
			if(set.size() < justahn.getDeck().size())
				System.out.println(set);
				System.out.println(justahn.getDeck());
				fail("When creating the Paladin's deck, a clone of the card should be added to the deck instead of the card itself.");
			}
	}	
	
	
	
	@Override
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		
		Minion m = new Minion("Silver Hand Recruit", 	1, Rarity.BASIC, 1, 1, false, false, false);

		if(this.getField().size() <= 6)
			this.getField().add(m);
		else
			throw new FullFieldException();
		
		
	}
	
}
