package model.cards;

import model.cards.minions.Minion;

public abstract class Card {

	
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.setManaCost(manaCost); // restrictons on the size of manacost
		this.rarity = rarity;
	}
	
	
	
	//Getters - Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int manaCost) {
		if (manaCost <= 10 && manaCost >= 0)
			this.manaCost = manaCost;
		else
		if(manaCost < 0)
			this.manaCost = 0;
		else
		if(manaCost > 10)
			this.manaCost = 10;
			
	}
	
	
	
	public Rarity getRarity() {
		return rarity;
	}
	
	
}
