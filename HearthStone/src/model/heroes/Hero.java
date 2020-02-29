package model.heroes;

import java.util.ArrayList;

import model.cards.Card;
import model.cards.minions.Minion;

public abstract class Hero {
	String name;
	int currentHP;
	boolean heroPowerUsed;
	int totalManaCrystals;
	int currentManaCrystals;
	ArrayList<Card> deck;
	ArrayList<Minion> field;
	ArrayList<Card> hand;
	int fatigueDamage;
	
	public Hero(String name) {
		this.name = name;
		currentHP =	30;
		heroPowerUsed = false;
	}

	
	
	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

}
