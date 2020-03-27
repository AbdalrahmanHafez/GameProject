package model.cards.minions;

import model.cards.Rarity;
import model.cards.spells.DivineSpirit;

public class Icehowl extends Minion {
	public Icehowl(){
			 super("Icehowl",9,Rarity.LEGENDARY, 10,10,false,false,true);
		}
	
	
	@Override
	public Icehowl clone() throws CloneNotSupportedException {
			try {
				Icehowl cloned = new Icehowl();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}
	
	
	
}
