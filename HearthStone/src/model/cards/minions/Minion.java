package model.cards.minions;

import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine; 
	private boolean sleeping;
	private boolean attacked;

	private MinionListener listener;
	
	
	//	TODO Minion Attack
	//	TODO icelHowl validate attack against hero
	public void attack(Minion target) {
		
		
	}
	
	public void attack(Hero target) throws InvalidTargetException{
		
		
	}

			

	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,
			boolean charge) {
		super(name, manaCost, rarity);
		setAttack(attack);
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
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
	
	
	
	
	public static void main(String[] Args) throws CloneNotSupportedException {
		Minion m = new Minion("ce", 2, Rarity.BASIC, 2, 5, false, false, false);
		Minion m2 = m.clone();
		
		System.out.println(m.getCurrentHP());
		System.out.println(m2.getCurrentHP());
		
		m.setCurrentHP(1);
		System.out.println(m.getCurrentHP());
		System.out.println(m2.getCurrentHP());
		
		
		m2.setCurrentHP(3);
		System.out.println(m.getCurrentHP());
		System.out.println(m2.getCurrentHP());
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
