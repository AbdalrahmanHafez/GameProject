package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {

	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
		super.setCardImage("resources/images/Spells/Polymorph.png");
		super.setAvatar("resources/images/Spells/avatar/Polymorph.png");
		}

	@Override
	public void performAction(Minion m) {
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setAttack(1);
		m.setName("Sheep");
		m.setSleeping(true);
		m.setTaunt(false);
		m.setDivine(false);
		m.setManaCost(1);

	}
}
