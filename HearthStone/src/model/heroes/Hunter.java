package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import engine.Game;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

public class Hunter extends Hero {

	public Hunter() throws IOException, CloneNotSupportedException, FullHandException {
		super("Rexxar");
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
		
		
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new KillCommand());
			getDeck().add(new MultiShot());
			
		}
		Minion krush=(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true));
		
		getDeck().add(krush);
		Collections.shuffle(getDeck());
		
//		Hero listens to The minion screams
		for(Card c : this.getDeck()) 
			if(c instanceof Minion)
				((Minion)c).setListener(this);
		
//	
	
	}
	
	
	@Override
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();

		
		super.damageOpponent(2);
		
	}
	
		
	
	
	
	
	
}
