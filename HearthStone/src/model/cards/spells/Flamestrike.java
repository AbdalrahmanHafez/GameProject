package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
		super.setCardImage("resources/images/Spells/Flamestrike.png");
		super.setAvatar("resources/images/Spells/avatar/Flamestrike.png");

	}
	

	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {

		for (int i = 0; i < oppField.size(); i++) {
			Minion m = oppField.get(i);
			if (m.isDivine())
				m.setDivine(false);
			else {
				m.setCurrentHP(oppField.get(i).getCurrentHP() - 4);
				if (m.getCurrentHP() == 0)
					i--;
			}
		}

	}

}
