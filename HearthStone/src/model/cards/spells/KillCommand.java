package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements MinionTargetSpell, HeroTargetSpell {

	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
		
	}

	@Override
	public void performAction(Hero h) {
		// TODO performAction
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		// TODO performAction
		
	}
}
