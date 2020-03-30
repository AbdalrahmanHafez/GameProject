package model.cards;

import java.io.IOException;

import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.Pyroblast;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Priest;

public abstract class Card implements Cloneable{
	private String name;
	private int manaCost;
	private Rarity rarity;

	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		setManaCost(manaCost);
		this.rarity = rarity;
	}

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	
	public static void main(String[] args) throws CloneNotSupportedException{
		Minion m = new Minion("cece", 10, Rarity.LEGENDARY, 4, 5, false, false, false);
		Minion m2 = (Minion) m.clone();
		System.out.println(m + " 55 " + m2);
		
		Icehowl h = new Icehowl();
		Icehowl h2 = (Icehowl) h.clone();
		System.out.println(h + " 55 " + h2);
		
	}
	

	
	

	public int getManaCost() {
		return manaCost;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
		if (this.manaCost > 10)
			this.manaCost = 10;
		else if (this.manaCost < 0)
			this.manaCost = 0;
	}

}
