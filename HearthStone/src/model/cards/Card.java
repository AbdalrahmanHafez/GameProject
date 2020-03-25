package model.cards;

public abstract class Card implements Cloneable{
	private String name;
	private int manaCost;
	private Rarity rarity;

	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		setManaCost(manaCost);
		this.rarity = rarity;
	}
	
	
	@Override
	//  TODO clone()
	//	You should override the clone() method to make it visible to all other classes and return a clone Card of the card that the method is invoked on (instead of the generic type Object).
	//		Abstract ??
	public Card clone() throws CloneNotSupportedException{
		
		
		
		return null;
		
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
