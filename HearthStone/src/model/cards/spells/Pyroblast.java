package model.cards.spells;

import model.cards.Rarity;

public class Pyroblast extends Spell implements MinionTargetSpell, HeroTargetSpell{
	public Pyroblast() {
		
	}

	public Pyroblast(String name, int manaCost, Rarity rarity) {
		super("Pyroblast", 10, Rarity.EPIC);
	}

}
