package model.heroes;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.spells.*;

import java.util.ArrayList;
import java.util.Collections;

import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Hero {
	private String name;
	
	private int currentHP;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private int fatigueDamage;
	
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	protected ArrayList<Card> deck; //protected, to be read by subclasses
	
	private boolean heroPowerUsed;
	
	public Hero() {
		
	}
	
	public Hero(String name) throws IOException {
		this.name = name;
		currentHP =	30;
		heroPowerUsed = false;

		deck = new ArrayList<Card>(1);
		buildDeck();	
	}

	
			
			
	
	public final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		
		
		ArrayList<Minion> minions = new ArrayList<Minion> ();
		
		while ((currentLine = br.readLine()) != null) {
			String[] l =  currentLine.split(",");
			
			// Icehowl instantiation
			if (l[0].equals("Icehowl")) {
				minions.add(new Icehowl());
				continue;
			}
			
				
			
			
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
	
	


	
	
	
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,	int count){
		
			
		
			Collections.shuffle(minions); // Randomizes Minions Array
			
			ArrayList<Minion> smallerArrayOfMinions = new ArrayList<Minion> ();
			while(smallerArrayOfMinions.size() < count) {
					int i = (int)(14.0 * Math.random());
			
					int occur = Collections.frequency(smallerArrayOfMinions, minions.get(i));	
					
					
					
					if (occur >= 2 || (occur >= 1 &&  minions.get(i).getRarity() == Rarity.LEGENDARY)) {
						continue;

					}
							
					smallerArrayOfMinions.add(minions.get(i)) ;
					
					
			}	
			
			return smallerArrayOfMinions;	
		}
		

	
	
	
	

	public abstract void buildDeck() throws IOException;
	//Each hero has his own implementation of the deck

	
	
	
	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		//restict the currentHP to [0, 30]
		if (currentHP <= 30 && currentHP >= 0)
			this.currentHP = currentHP;
		else
		if(currentHP < 0)
			this.currentHP = 0;
		else
		if(currentHP > 30)
			this.currentHP = 30;
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
		//restict the totalManaCrystals to [0, 10]
		if (totalManaCrystals <= 10 && totalManaCrystals >= 0)
				this.totalManaCrystals = totalManaCrystals;
			else
		if(totalManaCrystals < 0)
				this.totalManaCrystals = 0;
			else
		if(totalManaCrystals > 10)
				this.totalManaCrystals = 10;	
		
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		//restict the currentManaCrystals to [0, 10]
				if (currentManaCrystals <= 10 && currentManaCrystals >= 0)
						this.currentManaCrystals = currentManaCrystals;
					else
				if(currentManaCrystals < 0)
						this.currentManaCrystals = 0;
					else
				if(currentManaCrystals > 10)
						this.currentManaCrystals = 10;	
		
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
