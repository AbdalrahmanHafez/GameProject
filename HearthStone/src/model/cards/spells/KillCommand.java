package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements MinionTargetSpell, HeroTargetSpell {

	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
		super.setCardImage("resources/images/Spells/KillCommand.png");
		super.setAvatar("resources/images/Spells/avatar/KillCommand.png");
	}

	@Override
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP() - 3);

	}

	@Override
	public void performAction(Minion m) {
		if (m.isDivine())
			m.setDivine(false);
		else
			m.setCurrentHP(m.getCurrentHP() - 5);

	}

}
