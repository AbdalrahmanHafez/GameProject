package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
	public Pyroblast()
	{
		super("Pyroblast", 10, Rarity.EPIC);
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		// TODO performAction
		
	}

	@Override
	public void performAction(Hero h) {
		// TODO performAction
		
	}
	
}
