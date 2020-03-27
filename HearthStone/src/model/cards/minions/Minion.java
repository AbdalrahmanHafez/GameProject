package model.cards.minions;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine; 
	private boolean sleeping;
	private boolean attacked;

	private MinionListener listener;
	
	

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,			boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}
	
	
	public void attack(Minion target) {
		if(target.isDivine()) {
			target.setDivine(false);
		}else {
			
			target.setCurrentHP(target.getCurrentHP() - this.getAttack());
			this.setCurrentHP(this.getCurrentHP() - target.getAttack());
			
//			notify the listener about minion death
			if (target.getCurrentHP() == 0) 
				listener.onMinionDeath(target);
			if (this.getCurrentHP() == 0) 
				listener.onMinionDeath(this);
		}
		
	}
	
	public void attack(Hero target) throws InvalidTargetException{
		if (this instanceof Icehowl) 
			throw new InvalidTargetException();
		else {
			target.setCurrentHP(target.getCurrentHP() - this.getAttack());
			
//			TODO where? onHeroDeath listener call 
		}
		
		
		
	}

			


	
	
	@Override
	public Minion clone() throws CloneNotSupportedException {	
	try {
			Minion cloned = new Minion(this.getName(), this.getManaCost(), this.getRarity(), this.getAttack(), this.getCurrentHP(), this.isTaunt(), this.isDivine(), false);
			return cloned;
			
		} catch (Exception e) {
			throw new CloneNotSupportedException();
		}	
	}
	
	
	
	
	public boolean isTaunt() {
		return taunt;
	}
	
		
	public void setListener(MinionListener listener) {
		this.listener = listener;
	}
	

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;

		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}

	

}
