package model.cards.spells;

import model.cards.Rarity;

public class SealOfChampions extends Spell implements MinionTargetSpell{

	public SealOfChampions(String name, int manaCost, Rarity rarity) {
		super("Seal Of Champions", 3, Rarity.COMMON);
	}

}
