package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class DivineSpirit extends Spell implements MinionTargetSpell {

	public DivineSpirit() {
		super("Divine Spirit", 3, Rarity.BASIC);
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		
		m.setCurrentHP(2 * m.getCurrentHP());
		m.setMaxHP(2 * m.getMaxHP());
	
	}

	@Override
	public DivineSpirit clone() throws CloneNotSupportedException {
			try {
				DivineSpirit cloned = new DivineSpirit();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}

	

}
