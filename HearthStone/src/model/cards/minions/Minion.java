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
		this.setAttacked(true);

		if(this.getAttack() != 0)
			if(target.isDivine()) 
				target.setDivine(false);
			else 
				target.setCurrentHP(target.getCurrentHP() - this.getAttack());
			
		if(target.getAttack() != 0)
			if(this.isDivine())
				this.setDivine(false);
			else
				this.setCurrentHP(this.getCurrentHP() - target.getAttack());

	}
	
	public void attack(Hero target) throws InvalidTargetException{
//		The attacker minion should lose divine
		this.setDivine(false);
		
		this.setAttacked(true);
		
		if (this instanceof Icehowl) 
			throw new InvalidTargetException();
		else {
			target.setCurrentHP(target.getCurrentHP() - this.getAttack());
			
		}
	
		
		
	}

			


	
	
	
	
	
	
	public boolean isTaunt() {
		return taunt;
	}
	
		
	public void setListener(MinionListener listener) {
		if(listener != null)
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
//		Meaning the minion Died
			listener.onMinionDeath(this);
			
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
