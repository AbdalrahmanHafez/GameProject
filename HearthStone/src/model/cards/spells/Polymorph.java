package model.cards.spells;

import java.io.IOException;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Paladin;

public class Polymorph extends Spell implements MinionTargetSpell {

	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		m.setName("Sheep");
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setAttack(1);
		m.setManaCost(1);
		m.setTaunt(false);
		m.setDivine(false);
		m.setSleeping(true);
		
		
	}
	

	
	@Override
	public Polymorph clone() throws CloneNotSupportedException {
			try {
				Polymorph cloned = new Polymorph();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	
	
}
