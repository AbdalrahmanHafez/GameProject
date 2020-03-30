package model.heroes;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	public Warlock() throws IOException, CloneNotSupportedException, FullHandException {
		super("Gul'dan");
	}

	
	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
//		don;t duplicate a Minion, use clone()
		for(Minion m : neutrals) 
			if(getDeck().contains(m)) 
				getDeck().add((Card) m.clone());
			else 
				getDeck().add(m);
		
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred=new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
		getDeck().add(wilfred);
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
		

//		 Whenever a hero draws a card while Chromaggus is on his ﬁeld, a copy from the
//		 drawn card will be added to his hand (if there is an empty slot for the copy).
		
//		 If the warlock hero draws a minion card with his hero power while Wilfred
//		Fizzlebang is on his ﬁeld, the mana cost of the drawn minion is reduced to 0.
//		Note: In case the warlock hero has both Chromaggus and Wilfred Fizzlebang on his
//		ﬁeld, the eﬀect of Wilfred Fizzlebang should trigger ﬁrst before Chromaggus copies
//		the drawn minion.
		
//		 Draw an extra card and inﬂict two damage points to the hero.
	
		 this.drawCard();


		
		this.setCurrentHP(this.getCurrentHP() - 2);
		
	}
	
	
	
	
	
	
	
}
