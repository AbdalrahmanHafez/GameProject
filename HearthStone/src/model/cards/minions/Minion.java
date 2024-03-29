package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;
import exceptions.InvalidTargetException;

public class Minion extends Card implements Cloneable {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	public boolean sleeping;
	private boolean attacked;
	private MinionListener listener;

	
	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine,
			boolean charge) {
		super(name, manaCost, rarity);
		this.attack = attack;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		if (!charge)
			this.sleeping = true;
	}

	public void attack(Minion target) {

		if (divine && target.divine)

		{
			if (target.attack > 0)
				divine = false;
			if (attack > 0)
				target.divine = false;
		} else if (divine) {
			target.setCurrentHP(target.currentHP - attack);
			if (target.getAttack() > 0)
				divine = false;

		} else if (target.divine) {
			setCurrentHP(currentHP - target.attack);
			if (attack > 0)
				target.divine = false;
		} else {
			target.setCurrentHP(target.currentHP - attack);
			setCurrentHP(currentHP - target.attack);

		}
		attacked = true;
	}

	public void attack(Hero target) throws InvalidTargetException {
		attacked = true;
		target.setCurrentHP(target.getCurrentHP() - attack);

	}
	
	@Override
	public String getCardImage() {
		switch (this.getName()) {
		case "Goldshire Footman":
			return "resources/images/Minions/GoldshireFootman.png";
		case "Stonetusk Boar":
			return "resources/images/Minions/StonetuskBoar.png";
		case "Bloodfen Raptor":
			return "resources/images/Minions/BloodfenRaptor.png";
		case "Frostwolf Grunt":
			return "resources/images/Minions/FrostwolfGrunt.png";
		case "Wolfrider":
			return "resources/images/Minions/Wolfrider.png";
		case "Chilwind Yeti":
			return "resources/images/Minions/ChillwindYeti.png";
		case "Boulderfist Ogre":
			return "resources/images/Minions/BoulderfistOgre.png";
		case "Core Hound":
			return "resources/images/Minions/CoreHound.png";
		case "Argent Commander":
			return "resources/images/Minions/ArgentCommander.png";
		case "Sunwalker":
			return "resources/images/Minions/Sunwalker.png";
		case "Chromaggus":
			return "resources/images/Minions/Chromaggus.png";
		case "The LichKing":
			return "resources/images/Minions/TheLichKing.png";
		case "Icehowl":
			return "resources/images/Minions/Icehowl.png";
		case "Colossus of the Moon":
			return "resources/images/Minions/ColossusoftheMoon.png";
		case "King Krush":
			return "resources/images/Minions/KingKrush.png";
		case "Kalycgos":
			return "resources/images/Minions/Kalycgos.png";
		case "Sheep":
			return "resources/images/Minions/Sheep.png";
		case "Tirion Fordring":
			return "resources/images/Minions/TirionFordring.png";
		case "Prophet Velen":
			return "resources/images/Minions/ProphetVelen.png";
		case "Silver Hand Recruit":
			return "resources/images/Minions/SilverHandRecruit.png";
		case "Wilfred Fizzlebang":
			return "resources/images/Minions/WilfredFizzlebang.png";
		}
		
		return "";
	}
	
	@Override
	public String getAvatar() {
		switch (this.getName()) {
		case "Goldshire Footman":
			return "resources/images/Minions/avatar/GoldshireFootman.png";
		case "Stonetusk Boar":
			return "resources/images/Minions/avatar/StonetuskBoar.jpg";
		case "Bloodfen Raptor":
			return "resources/images/Minions/avatar/BloodfenRaptor.jpg";
		case "Frostwolf Grunt":
			return "resources/images/Minions/avatar/FrostwolfGrunt.jpg";
		case "Wolfrider":
			return "resources/images/Minions/avatar/Wolfrider.jpg";
		case "Chilwind Yeti":
			return "resources/images/Minions/avatar/ChillwindYeti.jpg";
		case "Boulderfist Ogre":
			return "resources/images/Minions/avatar/BoulderfistOgre.jpg";
		case "Core Hound":
			return "resources/images/Minions/avatar/CoreHound.jpg";
		case "Argent Commander":
			return "resources/images/Minions/avatar/ArgentCommander.jpg";
		case "Sunwalker":
			return "resources/images/Minions/avatar/Sunwalker.jpg";
		case "Chromaggus":
			return "resources/images/Minions/avatar/Chromaggus.jpg";
		case "The LichKing":
			return "resources/images/Minions/avatar/TheLichKing.jpg";
		case "Icehowl":
			return "resources/images/Minions/avatar/Icehowl.png";
		case "Colossus of the Moon":
			return "resources/images/Minions/avatar/ColossusoftheMoon.jpg";
		case "King Krush":
			return "resources/images/Minions/avatar/KingKrush.jpg";
		case "Kalycgos":
			return "resources/images/Minions/avatar/Kalycgos.jpg";
		case "Sheep":
			return "resources/images/Minions/avatar/Sheep.jpg";
		case "Tirion Fordring":
			return "resources/images/Minions/avatar/TirionFordring.jpg";
		case "Prophet Velen":
			return "resources/images/Minions/avatar/ProphetVelen.jpg";
		case "Silver Hand Recruit":
			return "resources/images/Minions/avatar/SilverHandRecruit.png";
		case "Wilfred Fizzlebang":
			return "resources/images/Minions/avatar/WilfredFizzlebang.png";
		}
		return "";
	}

	
	
	
	

	public boolean isTaunt() {
		return taunt;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHp) {
		this.maxHP = maxHp;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (this.currentHP > maxHP)
			this.currentHP = maxHP;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onMinionDeath(this);
		}
	}

	public void setListener(MinionListener listener) {
		this.listener = listener;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
		if (this.attack <= 0)
			this.attack = 0;
	}

	public void setTaunt(boolean isTaunt) {
		this.taunt = isTaunt;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	public boolean isDivine() {
		return divine;
	}

	public Minion clone() throws CloneNotSupportedException {
		return (Minion) super.clone();
	}
}
