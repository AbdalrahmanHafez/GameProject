package model.cards.spells;

import model.cards.Rarity;

public class TwistingNether extends Spell implements AOESpell {
	
	public TwistingNether() {
		super();
		
	}

	public TwistingNether(String name, int manaCost, Rarity rarity) {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

}
