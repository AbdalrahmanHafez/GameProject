package model.heroes;

import java.util.ArrayList;

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
		
		ArrayList<Minion> m = new ArrayList<Minion> ();
		
		while ((currentLine = br.readLine()) != null) {
			Rarity R ;
			
			
			String[] z =  currentLine.split(",");
			switch(z[2]) {
			case "b" : R = Rarity.BASIC;break;
			case "c" : R = Rarity.COMMON;break;
			case "r" : R = Rarity.RARE;break;
			case "e" : R = Rarity.EPIC;break;
			case "l" : R = Rarity.LEGENDARY;break;
			
			}
			
			m.add(new Minion( z[0],Integer.parseInt(z[1]),R,Integer.parseInt(z[3]),Integer.parseInt(z[4]),Boolean.parseBoolean((z[5]).toLowerCase()),Boolean.parseBoolean((z[6]).toLowerCase())),Boolean.parseBoolean((z[7]).toLowerCase()));
		}
		
		String[] info = totalS.split(",");
		
		
		return 
		
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
