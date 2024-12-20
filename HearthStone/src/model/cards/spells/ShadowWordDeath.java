package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		super.setCardImage("resources/images/Spells/ShadowWordDeath.png");
		super.setAvatar("resources/images/Spells/avatar/ShadowWordDeath.png");
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		if (m.getAttack() < 5)
			throw new InvalidTargetException("Choose a minion with 5 or more attack");
		m.setCurrentHP(0);

	}

}
