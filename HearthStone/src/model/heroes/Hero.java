package model.heroes;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.math.*;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

	
			
			
	
	public static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		
		ArrayList<Minion> minions = new ArrayList<Minion> ();
		
		while ((currentLine = br.readLine()) != null) {
			String[] l =  currentLine.split(",");
			
			Rarity R = null ;
			switch(l[2]) { // Rarity Value
				case "b" : R = Rarity.BASIC;break;
				case "c" : R = Rarity.COMMON;break;
				case "r" : R = Rarity.RARE;break;
				case "e" : R = Rarity.EPIC;break;
				case "l" : R = Rarity.LEGENDARY;break;
			}
			

//			public Minion(String name, int manaCost, Rarity rarity, int attack,int maxHP,boolean
//					taunt,boolean divine,boolean charge) 
			
//			NAME, MANA COST,RARITY, ATTACK, MAX HP,
//			TAUNT, DIVINE, CHARGE.
			
			minions.add(new Minion(l[0],						//name
					Integer.parseInt(l[1]),						//mana
					R,											//Rarity
					Integer.parseInt(l[3]),						//Attack
					Integer.parseInt(l[4]),						//MaxHP
					Boolean.parseBoolean(l[5].toLowerCase()),	//taunt
					Boolean.parseBoolean(l[6].toLowerCase()),	//Divine
					Boolean.parseBoolean(l[7].toLowerCase())	//Charge
					));
	}
	
	
		return minions;
	}
	
	static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count){
		Collections.shuffle(minions);
		
		Random r = new Random();
		int rand = r.nextInt(1);
		//???
		
		return null;
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
