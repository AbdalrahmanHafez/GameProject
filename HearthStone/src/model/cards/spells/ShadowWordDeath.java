package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
//	Destroys a minion that his attack is 5 or more even if it has a divine
		if(m.getAttack() >= 5) {
			m.setCurrentHP(0); 		// make him die
			
		}
		
	}
	
	@Override
	public ShadowWordDeath clone() throws CloneNotSupportedException {
			try {
				ShadowWordDeath cloned = new ShadowWordDeath();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	
}
