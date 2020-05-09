package model.cards;

public abstract class Card implements Cloneable {
	private String name;
	private int manaCost;
	private Rarity rarity;

	private String avatar;
	private String cardImage;

	

	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.manaCost = manaCost;
		this.rarity = rarity;
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
		if (this.manaCost < 0)
			this.manaCost = 0;
	}

	public Card clone() throws CloneNotSupportedException {
		return (Card) super.clone();
	}

	
	public String getAvatar() {
		return avatar;
	}

	public String getCardImage() {
		return cardImage;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setCardImage(String cardImage) {
		this.cardImage = cardImage;
	}
	
}
