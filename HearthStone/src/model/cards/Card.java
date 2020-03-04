package model.cards;

public abstract class Card {

	
	private String name;
	private int manaCost;
	private Rarity rarity;
	
	public Card() {}
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
		//restict the manaCost to [0, 10]
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
