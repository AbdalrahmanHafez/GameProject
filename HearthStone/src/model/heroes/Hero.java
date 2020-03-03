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
	String name;
	
	int currentHP;
	int totalManaCrystals;
	int currentManaCrystals;
	int fatigueDamage;
	
	ArrayList<Card> deck;
	ArrayList<Minion> field;
	ArrayList<Card> hand;
	
	boolean heroPowerUsed;
	
	
	public Hero(String name) {
		this.name = name;
		currentHP =	30;
		heroPowerUsed = false;
	}

	
			
			
	
	public static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		
		Icehowl ce;
		
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
	
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count){
		Collections.shuffle(minions); // Randomizes Minions Array
		
		ArrayList<Minion> smallerArrayOfMinions = new ArrayList<Minion> ();
		for(int i = 0; i< count; i++) { 
			smallerArrayOfMinions.add(minions.get(i)) ;
		}	
			
		return smallerArrayOfMinions;
	}
	
	

	void buildDeck() throws IOException{

		//Reading the cvs data file
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		
		
		switch(this.name) { // HeroName
			case "Hunter" :
				
				deck.addAll(getNeutralMinions(minions, 15)); // gets 15 nurtal minions
								
				deck.add(new KillCommand("Kill Command", 3, Rarity.BASIC));
				deck.add(new KillCommand("Kill Command", 3, Rarity.BASIC));
				
				deck.add(new MultiShot("Multi-Shot", 4, Rarity.BASIC));
				deck.add(new MultiShot("Multi-Shot", 4, Rarity.BASIC));
												
				deck.add(new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false , false ,true));
								
				;break;
				
				
			case "Mage" :
				
				deck.addAll(getNeutralMinions(minions, 13)); 
				
				deck.add(new Polymorph("Polymorph", 4, Rarity.BASIC));
				deck.add(new Polymorph("Polymorph", 4, Rarity.BASIC));
				
				deck.add(new Flamestrike("Flamestrike", 7, Rarity.BASIC));
				deck.add(new Flamestrike("Flamestrike", 7, Rarity.BASIC));
				
				deck.add(new Pyroblast("Pyroblast", 10, Rarity.EPIC));
				deck.add(new Pyroblast("Pyroblast", 10, Rarity.EPIC));
				
				
				deck.add(new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false , false ,false));
								
				;break;
				
				
			case "Paladin" :
				
				deck.addAll(getNeutralMinions(minions, 15)); 
				
				deck.add(new SealOfChampions("Seal of Champions", 3, Rarity.COMMON));
				deck.add(new SealOfChampions("Seal of Champions", 3, Rarity.COMMON));
				
				deck.add(new LevelUp("Level Up!", 6, Rarity.EPIC));
				deck.add(new LevelUp("Level Up!", 6, Rarity.EPIC));
				
				deck.add(new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false));
								
				;break;
				
				
			case "Priest" :
				
				deck.addAll(getNeutralMinions(minions, 13)); 
				
				deck.add(new DivineSpirit("Divine Spirit", 2, Rarity.BASIC));
				deck.add(new DivineSpirit("Divine Spirit", 2, Rarity.BASIC));
				
				deck.add(new HolyNova("Holy Nova", 5, Rarity.BASIC));
				deck.add(new HolyNova("Holy Nova", 5, Rarity.BASIC));
				
				deck.add(new ShadowWordDeath("Shadow Word :Death", 3, Rarity.BASIC));
				deck.add(new ShadowWordDeath("Shadow Word :Death", 3, Rarity.BASIC));
								
				deck.add(new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false));
				
				;break;
				
				
			case "Warlock" : 
				
				deck.addAll(getNeutralMinions(minions, 13)); 

				deck.add(new CurseOfWeakness("Curse of Weakness", 2, Rarity.RARE));
				deck.add(new CurseOfWeakness("Curse of Weakness", 2, Rarity.RARE));
				
				
				deck.add(new SiphonSoul("Siphon Soul", 6, Rarity.RARE));
				deck.add(new SiphonSoul("Siphon Soul", 6, Rarity.RARE));
				
				
				deck.add(new TwistingNether("Twisting Nether", 8, Rarity.EPIC));
				deck.add(new TwistingNether("Twisting Nether", 8, Rarity.EPIC));
				
				deck.add(new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false));

				
				;break;	
			}
		
		
		Collections.shuffle(deck);
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
