package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		// TODO performAction
		
	}

	@Override
	public HolyNova clone() throws CloneNotSupportedException {
			try {
				HolyNova cloned = new HolyNova();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	
	
	
}
