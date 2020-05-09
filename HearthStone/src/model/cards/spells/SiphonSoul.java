package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SiphonSoul extends Spell implements LeechingSpell {

	public SiphonSoul() {
		super("Siphon Soul", 6, Rarity.RARE);
		super.setCardImage("resources/images/Spells/SiphonSoul.png");
		super.setAvatar("resources/images/Spells/avatar/SiphonSoul.png");
	}

	@Override
	public int performAction(Minion m) {
		m.setCurrentHP(0);
		return 3;
	}

}
