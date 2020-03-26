package model.cards.spells;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class SiphonSoul extends Spell implements LeechingSpell {

	public SiphonSoul() {
		super("Siphon Soul", 6, Rarity.RARE);
		
	}

	@Override
	public int performAction(Minion m) {
		// TODO performAction
		return 0;
	}
	
	
	@Override
	public SiphonSoul clone() throws CloneNotSupportedException {
			try {
				SiphonSoul cloned = new SiphonSoul();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	
}
