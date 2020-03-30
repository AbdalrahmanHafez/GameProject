package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.Validator;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.Flamestrike;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.LevelUp;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;

public class Mage extends Hero {

	public Mage() throws IOException, CloneNotSupportedException, FullHandException {
		super("Jaina Proudmoore");
	}

	@Override
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"), 13);
//		don;t duplicate a Minion, use clone()
		for(Minion m : neutrals) 
			if(getDeck().contains(m)) 
				getDeck().add((Card) m.clone());
			else 
				getDeck().add(m);
		
		
		for (int i = 0; i < 2; i++) {
			getDeck().add(new Polymorph());
			getDeck().add(new Flamestrike());
			getDeck().add(new Pyroblast());
		}
		Minion kalycgos = (new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false));
		
		getDeck().add(kalycgos);
		Collections.shuffle(getDeck());
		
//		Hero listens to The minion screams
		for(Card c : this.getDeck()) 
			if(c instanceof Minion)
				((Minion)c).setListener(this);
		
//	
	}	
	
	
	private void checkForKalycgos(Spell s) {
		for(Minion m : super.getField())
			if(m.getName().equals("Kalycgos")) {
				Card spell = (Card) s;
				spell.setManaCost(spell.getManaCost() - 4);
			}	
	}

	@Override
	public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException {
		checkForKalycgos((Spell) s);
		super.castSpell(s);

	}
	
	@Override
	public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws NotYourTurnException, NotEnoughManaException {
		checkForKalycgos((Spell) s);
		super.castSpell(s, oppField);
	}
	@Override
	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException {
		checkForKalycgos((Spell) s);
		super.castSpell(s, h);
	}
	@Override
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException {
		checkForKalycgos((Spell) s);
		super.castSpell(s, m);
	}
	@Override
	public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException, InvalidTargetException {
		checkForKalycgos((Spell) s);
		super.castSpell(s, m);
	}
	

	
	
	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
//		Damages the hero
		target.setCurrentHP(target.getCurrentHP() - 1);
	
	}
	
	public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
//		Damages a minion
		if(target.isDivine())
			target.setDivine(false);
		else
			target.setCurrentHP(target.getCurrentHP() - 1);
	
	}
}
