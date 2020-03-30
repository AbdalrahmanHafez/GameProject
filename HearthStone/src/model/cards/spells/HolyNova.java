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
		for(int i = 0; i <oppField.size() ; i++) {
			
			Minion m = oppField.get(i); 
			
			if(m.isDivine() ) {
				m.setDivine(false);
			}else{
				
				if (m.getCurrentHP() - 2 == 0) {		// if the minion would die
					i = i-1;										// account for field size change
				}
				m.setCurrentHP(m.getCurrentHP() - 2);

			}
			
			
		}
				
		
		for(Minion minion : curField) {
			minion.setCurrentHP(minion.getCurrentHP() + 2);
		}
		
	}


	
	
	
}
