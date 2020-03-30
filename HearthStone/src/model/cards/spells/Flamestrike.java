package model.cards.spells;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Mage;

public class Flamestrike extends Spell implements AOESpell {

	
	public Flamestrike()
	{
		super("Flamestrike",7,Rarity.BASIC);
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		
		for(int i = 0; i <oppField.size() ; i++) {
			
			Minion m = oppField.get(i); 
			
			if(m.isDivine() ) {
				m.setDivine(false);
			}else{
				
				if (m.getCurrentHP() - 4 == 0) {		// if the minion would die 
					i = i-1;										// account for field size change
				}
				m.setCurrentHP(m.getCurrentHP() - 4);

			}
			
			
		}
		
	}



	


}
