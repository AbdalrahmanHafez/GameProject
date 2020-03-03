package model.cards.spells;

import model.cards.Card;
import model.cards.Rarity;

public abstract class Spell extends Card{
	public Spell() {
		
	}
	public Spell(String name, int manaCost, Rarity rarity) {
		super(name, manaCost, rarity);
	}
	
	
	
}
