package model.cards;

public abstract class Card {

	
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public Card() {}
	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.manaCost = manaCost;
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
		if(manaCost >=0 && manaCost<=10)
		     this.manaCost = manaCost;
		else if(manaCost>10)
			 this.manaCost =10;
		else
			 this.manaCost =0;
	}
	public Rarity getRarity() {
		return rarity;
	}
	
	
}
