package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class DivineSpirit extends Spell implements MinionTargetSpell {

	public DivineSpirit() {
		super("Divine Spirit", 3, Rarity.BASIC);
		super.setCardImage("resources/images/Spells/DivineSpirit.png");
		
	}

	@Override
	public void performAction(Minion m) {
		m.setMaxHP(m.getMaxHP() * 2);
		m.setCurrentHP(m.getCurrentHP() * 2);

	}

}
