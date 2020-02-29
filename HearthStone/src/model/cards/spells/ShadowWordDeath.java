package model.cards.spells;

import model.cards.Rarity;

public class ShadowWordDeath extends Spell implements MinionTargetSpell{

	public ShadowWordDeath(String name, int manaCost, Rarity rarity) {
		super("Shadow Word: Death", 3, Rarity.BASIC);
	}

}
