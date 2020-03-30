package model.heroes;

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
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {

	public Priest() throws IOException, CloneNotSupportedException, FullHandException {
		super("Anduin Wrynn");
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
			getDeck().add(new DivineSpirit());
			getDeck().add(new HolyNova());
			getDeck().add(new ShadowWordDeath());
		}
		Minion velen=new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
		
		getDeck().add(velen);
		Collections.shuffle(getDeck());

		
//		Hero listens to The minion screams
		for(Card c : this.getDeck()) 
			if(c instanceof Minion)
				((Minion)c).setListener(this);
		
//	
	}
	
	
	
	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		
//		If a priest hero has Prophet Velen on his ﬁeld, his hero power restores 8 health instead of 2
		for(Minion m : super.getField()) {
			if(m.getName().equals("Prophet Velen")) {
				target.setCurrentHP(target.getCurrentHP() + 8);
				return;//exit
			}
		}
		//		restores 2 HP
		target.setCurrentHP(target.getCurrentHP() + 2);
	
	}
	
	public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		
//		If a priest hero has Prophet Velen on his ﬁeld, his hero power restores 8 health instead of 2
		for(Minion m : super.getField()) {
			if(m.getName().equals("Prophet Velen")) {
				target.setCurrentHP(target.getCurrentHP() + 8);
				return;//exit
			}
		}
		//		restores 2 HP
		target.setCurrentHP(target.getCurrentHP() + 2);
	
	
	}
	

}
