package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
//		 Destroys all minions of both heroes even if any of them has a divine shield.
		
		for(Minion minion : curField)
			minion.setCurrentHP(0); //make him die?
		for(Minion minion : oppField)
			minion.setCurrentHP(0);
		

		
	}
	
	@Override
	public TwistingNether clone() throws CloneNotSupportedException {
			try {
				TwistingNether cloned = new TwistingNether();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	

}
