package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
		super.setCardImage("resources/images/Spells/TwistingNether.png");
		super.setAvatar("resources/images/Spells/avatar/TwistingNether.png");
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		while (!oppField.isEmpty()) {

			oppField.get(0).setCurrentHP(0);

		}
		while (!curField.isEmpty()) {

			curField.get(0).setCurrentHP(0);

		}

	}

}
