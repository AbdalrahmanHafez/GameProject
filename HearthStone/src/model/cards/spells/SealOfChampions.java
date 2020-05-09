package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SealOfChampions extends Spell implements MinionTargetSpell {

	public SealOfChampions() {
		super("Seal of Champions", 3, Rarity.COMMON);
		super.setCardImage("resources/images/Spells/SealofChampions.png");
		super.setAvatar("resources/images/Spells/avatar/SealofChampions.png");

	}

	@Override
	public void performAction(Minion m) {
		m.setAttack(m.getAttack() + 3);
		m.setDivine(true);

	}

}
