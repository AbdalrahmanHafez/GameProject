package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class SealOfChampions extends Spell implements MinionTargetSpell {

	public SealOfChampions() {
		super("Seal of Champions", 3, Rarity.COMMON);
		
	}

	@Override
	public void performAction(Minion m) throws InvalidTargetException {
		m.setAttack(m.getAttack() + 3);
		m.setDivine( true );
		
	}

	
	@Override
	public SealOfChampions clone() throws CloneNotSupportedException {
			try {
				SealOfChampions cloned = new SealOfChampions();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	
	
}
