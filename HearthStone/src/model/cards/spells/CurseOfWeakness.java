package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {
	
	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);
		super.setCardImage("resources/images/Spells/CurseofWeakness.png");
		super.setAvatar("resources/images/Spells/avatar/CurseofWeakness.png");

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for (int i = 0; i < oppField.size(); i++) {
			oppField.get(i).setAttack(oppField.get(i).getAttack() - 2);
		}

	}

}
