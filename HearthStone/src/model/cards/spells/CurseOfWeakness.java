package model.cards.spells;

import model.cards.Rarity;

public class CurseOfWeakness extends Spell implements AOESpell{

	public CurseOfWeakness(String name, int manaCost, Rarity rarity) {
		super("”Curse of Weakness", 2, Rarity.RARE);
	}

}
