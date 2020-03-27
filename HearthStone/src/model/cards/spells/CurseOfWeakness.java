package model.cards.spells;

import java.util.ArrayList;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {

	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);
		
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		for(Minion minion : oppField)
			minion.setAttack( minion.getAttack() - 2);
		
	}

	@Override
	public CurseOfWeakness clone() throws CloneNotSupportedException {
			try {
				CurseOfWeakness cloned = new CurseOfWeakness();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}

	
}
