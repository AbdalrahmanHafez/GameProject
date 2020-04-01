package model.heroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import engine.ActionValidator;
import engine.Game;
import exceptions.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.*;

// The hero listens to minion Events
public abstract class Hero implements HeroListener, MinionListener{
	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	@SuppressWarnings("unused")
	private int fatigueDamage;
	
	private HeroListener listener;
	private ActionValidator validator;
//	The variable responsible for validating all actions the hero takes.
	
	public Hero(String name) throws IOException, CloneNotSupportedException, FullHandException {
		this.name = name;
		currentHP = 30;
		deck = new ArrayList<Card>();
		field = new ArrayList<Minion>();
		hand = new ArrayList<Card>();
		buildDeck();
	}

	public abstract void buildDeck() throws IOException, CloneNotSupportedException;

	
	public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		String current = br.readLine();
		while (current != null) {
			String[] line = current.split(",");
			Minion minion = null;
			String n = line[0];
			int m = Integer.parseInt(line[1]);
			Rarity r = null;
			switch (
				(line[2])
			) {
			case "b":
				r = Rarity.BASIC;
				break;
			case "c":
				r = Rarity.COMMON;
				break;
			case "r":
				r = Rarity.RARE;
				break;
			case "e":
				r = Rarity.EPIC;
				break;
			case "l":
				r = Rarity.LEGENDARY;
				break;
			}
			int a = Integer.parseInt(line[3]);
			int p = Integer.parseInt(line[4]);
			boolean t = line[5].equals("TRUE") ? true : false;
			boolean d = line[6].equals("TRUE") ? true : false;
			boolean c = line[7].equals("TRUE") ? true : false;
			if (!n.equals("Icehowl"))
				minion = new Minion(n, m, r, a, p, t, d, c);
			else
				minion = new Icehowl();
			minions.add(minion);
			current = br.readLine();
		}
		br.close();
		return minions;
	}

	public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) {
		ArrayList<Minion> res = new ArrayList<Minion>();
		int i = 0;
		while (i < count) {
			
			int index = (int) (Math.random() * minions.size());
			Minion minion = minions.get(index);
			int occ = 0;
			for (int j = 0; j < res.size(); j++) {
				if (res.get(j).getName().equals(minion.getName()))
					occ++;
			}
			if (occ == 0)
			{
				res.add(minion);
				i++;
			}
			else if(occ==1 && minion.getRarity()!=Rarity.LEGENDARY)
			{
				res.add(minion);
				i++;
			}
		}
		return res;
	}
	
	
	public void onMinionDeath(Minion m) {
		field.remove(m);


	}
	
	public void damageOpponent(int amount) {
		//		notify the game to damage
		listener.damageOpponent(amount);
	}
	
	
	
	
	
	public void onHeroDeath() {
		listener.onHeroDeath();
	}
	
	
	public void useHeroPower() throws NotEnoughManaException,HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException{
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		
//		using the hero's power cost 2 mana
		setCurrentManaCrystals(this.getCurrentManaCrystals() - 2);
		
		setHeroPowerUsed(true);
		
	}

	//	This method handles playing a minion card.this means removing a minion from the hero hand and adding it to his ﬁeld.
	 public void playMinion(Minion m) throws NotYourTurnException, NotEnoughManaException, FullFieldException{
		validator.validateTurn(this);
		validator.validateManaCost(m);
		validator.validatePlayingMinion(m);
		
		 getHand().remove(m);
		 getField().add(m);
		 
		 
	 }
	
	
	 public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, NotYourTurnException, TauntBypassException,	 InvalidTargetException, NotSummonedException{
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);
		
	 }
	 
	 
	 public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, NotYourTurnException, TauntBypassException, NotSummonedException, InvalidTargetException{
			validator.validateTurn(this);
			validator.validateAttack(attacker, target);
			attacker.attack(target);
	 }
	
	 
	 public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException {
		validator.validateTurn(this);
		validator.validateManaCost((Card)s);
		s.performAction(getField());
		getHand().remove(s);
		
		setCurrentManaCrystals(this.getCurrentManaCrystals() - ((Card)s).getManaCost());

	 }

	 
	
	 public void castSpell(AOESpell s, ArrayList<Minion >oppField) throws NotYourTurnException, NotEnoughManaException{
			validator.validateTurn(this);
			validator.validateManaCost((Card)s);
			s.performAction(oppField, getField());
			getHand().remove(s);
			
			setCurrentManaCrystals(this.getCurrentManaCrystals() - ((Card)s).getManaCost());

	 }
	 
	 public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException, InvalidTargetException{
			validator.validateTurn(this);
			validator.validateManaCost((Card)s);

	
			s.performAction(m);
			getHand().remove(s);

			setCurrentManaCrystals(this.getCurrentManaCrystals() - ((Card)s).getManaCost());

	 }
	 
	 public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException{
			validator.validateTurn(this);
			validator.validateManaCost((Card)s);
			s.performAction(h);
			getHand().remove(s);
			
			setCurrentManaCrystals(this.getCurrentManaCrystals() - ((Card)s).getManaCost());

	 }
	
	 
	 public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException{
			validator.validateTurn(this);
			validator.validateManaCost((Card)s);
			int returnedHelth = s.performAction(m);
			getHand().remove(s);
			
			this.setCurrentHP(this.getCurrentHP() + returnedHelth);

			setCurrentManaCrystals(this.getCurrentManaCrystals() - ((Card)s).getManaCost());

	 }
	 
		

	public void endTurn() throws FullHandException, CloneNotSupportedException {
		listener.endTurn();
	}
	
	
	 public Card drawCard() throws FullHandException, CloneNotSupportedException{
		 
		
		 
		 // drawing from an Empty deck
		 if(this.getDeck().isEmpty()) {
			setCurrentHP(currentHP - fatigueDamage); 
			this.fatigueDamage = this.fatigueDamage + 1;
		 	return null;
		 }

		 Card drawnCard = this.getDeck().remove(0);
	
		 if(this.getDeck().isEmpty()) {
			 this.fatigueDamage = 1;
		 }
		 
		 setCurrentManaCrystals(this.getCurrentManaCrystals() - (drawnCard).getManaCost());

		 
		 
		 if (this.getHand().size() >= 10) 
			 throw new FullHandException(drawnCard);
		 
		 
		 getHand().add(drawnCard);
		 
//		 Legendary Special
//		 If field already has chromaggus than add another clone of whatever the hero is drawing.
		 
		 for(Minion m : this.getField())
				if(m.getName().equals("Wilfred Fizzlebang") && drawnCard instanceof Minion) {
					drawnCard.setManaCost(0);
					break; // exit the loop
				}
			
		 
		 for(Card card : this.getField()) 
			if(  card.getName().equals("Chromaggus")  )
				if(this.getHand().size() <= 9) { // ensure a space left
					getHand().add(  (Card) drawnCard.clone()  );
					break; // exit the loop
				}
		
		 

		 
		
		 return drawnCard;
	 }
	 
	
	 
	 
	
	 
	 
	
	public HeroListener getListener() {
		return listener;
	}

	public void setListener(HeroListener listener) {
		if(listener != null)
			this.listener = listener;
	}

	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}
	

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
		if (this.currentHP > 30)
			this.currentHP = 30;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
//			Meaning the Hero Died
				listener.onHeroDeath();
		
		}
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
		if (this.totalManaCrystals > 10)
			this.totalManaCrystals = 10;
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
		if (this.currentManaCrystals > 10)
			this.currentManaCrystals = 10;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	

	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setHeroPowerUsed(boolean powerUsed) {
		this.heroPowerUsed = powerUsed;
	}

	public String getName() {
		return name;
	}

}
