package model.cards.spells;

import java.util.ArrayList;
import java.util.Random;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{

	public MultiShot() {
		super("Multi-Shot", 4,Rarity.BASIC);
		
	}

	@Override
	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
//		opponent ﬁeld is empty then nothing happens.
		if(oppField.isEmpty()) 
			return;
		
		if(oppField.size() == 1){
				Minion m = oppField.get(0);
				m.setCurrentHP(m.getCurrentHP() - 3);
		}else {
//			Deals 3 damage to two random enemy minions
	        Random rand = new Random(); 
	        
	        int randint = rand.nextInt(oppField.size() );
	        int randint2 = 0;
	        do {
	        	randint2 = rand.nextInt(oppField.size());
			} while (randint2 == randint);
	        
	        
	        Minion m1 = oppField.get(  randint 	 );
			Minion m2 = oppField.get(  randint2  );
			
			m1.setCurrentHP(m1.getCurrentHP() - 3);
			m2.setCurrentHP(m2.getCurrentHP() - 3);

			
		}
		
			
		
		
		
		
		
		
	}
	
	@Override
	public MultiShot clone() throws CloneNotSupportedException {
			try {
				MultiShot cloned = new MultiShot();
				return cloned;
				
			} catch (Exception e) {
				throw new CloneNotSupportedException();
			}	
	}	

}
