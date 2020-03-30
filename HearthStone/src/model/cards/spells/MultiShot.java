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
		
        Random rand = new Random(); 

		//		opponent ﬁeld is empty then nothing happens.
		if(oppField.isEmpty()) 
			return; //exit

		if(oppField.size() == 1){
				Minion m = oppField.get(rand.nextInt(oppField.size()));
				
				if(m.isDivine())
					m.setDivine(false);
				else
					m.setCurrentHP(m.getCurrentHP() - 3);
				
				
		}	else {
//			Deals 3 damage to two random enemy minions
			Minion m1;
			Minion m2;
			
		    int randint = rand.nextInt(oppField.size() );
		    int randint2;
	        
	        do { 
	        	randint2 = rand.nextInt(oppField.size());
			} while (randint2 == randint);		 			//make sure rand numbers are Not the same

	        m1 = oppField.get(  randint 	 );
			m2 = oppField.get(  randint2  );

			
			//MultiShot should not damage opponent minions with divine shield
			
			if(m1.isDivine()) 
				m1.setDivine(false);
			else 
				m1.setCurrentHP(m1.getCurrentHP() - 3);

			if(m2.isDivine()) 
				m2.setDivine(false);
			else 
				m2.setCurrentHP(m2.getCurrentHP() - 3);
			
		}
		
	}

	
	
	

}
