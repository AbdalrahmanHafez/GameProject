package Main;
import javax.swing.*;

import assets.ImageButton;
import assets.MinionCardButton;
import assets.SpellCardButton;
import assets.alertBox;
import assets.CardButton;
import engine.Game;
import exceptions.AlreadyDrawnException;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
public class Controller implements ActionListener, WelcomeScreenListener, GameScreenListener{

	private StartScreen Startsc;
	private WelcomeScreen welcomesc;
	private GameScreen gamesc;
	private Game game;
	private alertBox alert = new alertBox();
	
	
//testdel
	
	public Controller(){
		Startsc = new StartScreen();
		welcomesc  = new WelcomeScreen();
		gamesc = new GameScreen();
		
		Startsc.setListener(this);
		welcomesc.setListener(this);
		gamesc.setListener(this);
		
	}
	
	
	public static void main(String[] args) {
		new Controller();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO button Actions 
		
		boolean _updatethisround = true; // used to keep the current action commands, and not replace them by the default AC
	
		if(e.getActionCommand().equals("initialStart")) { 
			Startsc.setVisible(false);
			welcomesc.setVisible(true);
			return; //to avoid call the update method
		}
		
		switch (e.getActionCommand()){
		
			case "start":
				welcomesc.setVisible(false);
				gamesc.setVisible(true);
				
				game.getCurrentHero().setTotalManaCrystals(10);
				game.getCurrentHero().setCurrentManaCrystals(10);
				game.getOpponent().setTotalManaCrystals(10);
				game.getOpponent().setCurrentManaCrystals(10);
				
				;break;
				
			case "endturn":
				try { //TODO 	endturn could this give an error
					game.endTurn();
				} catch (FullHandException | CloneNotSupportedException e2) {
					alert.info(e2.getMessage());
				}
			;break;
				
				
			case "minionplay":
//					TODO cardButton action
				System.out.println("controller minionplay");
						
				try {
					MinionCardButton btnminion =(MinionCardButton) e.getSource();
					Minion m = (btnminion).getCard();
					game.getCurrentHero().playMinion(m);
				} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e2) {
					alert.error(e2.getMessage());
				}
						
			
			;break;
				
			case "minionattack":
				System.out.println("controller attack method");
				CardButton minionsource = (CardButton)e.getSource();
				Minion attackerMinion = ((MinionCardButton)minionsource.getAttackedBy()).getCard();
				Minion targetMinion = ((MinionCardButton)minionsource).getCard();
				
				try {
					game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
					System.out.println("attacked a Minion" + "attacker" + attackerMinion.getName() + " target"
							+ targetMinion.getName());
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException
						| InvalidTargetException | NotSummonedException e1) {
					alert.error(e1.getMessage());
				}
						
			;break;
			
			case "minionattackhero":
				System.out.println("controller minion attack hero method");
				try {
						game.getCurrentHero().attackWithMinion(((MinionCardButton)gamesc.attacker).getCard() , game.getOpponent());
						System.out.println("attacked the hero");
					} catch (CannotAttackException | NotYourTurnException | TauntBypassException
							| NotSummonedException | InvalidTargetException e1) {
						alert.error(e1.getMessage());
					}
			;break;

			case "spellcast":
				System.out.println("controller spellcast method");
				Spell s = ((SpellCardButton)gamesc.attacker).getCard();
				
				if(s instanceof FieldSpell)
				try {
					game.getCurrentHero().castSpell((FieldSpell)s);
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					alert.error(e1.getMessage());}
				
				if(s instanceof AOESpell)
				try {
					game.getCurrentHero().castSpell((AOESpell)s, game.getOpponent().getField());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					alert.error(e1.getMessage());}

				if(s instanceof HeroTargetSpell)
				try {
					game.getCurrentHero().castSpell((HeroTargetSpell)s, game.getOpponent());
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					alert.error(e1.getMessage());}
				
				
			;break;
		
			case "spellcastontarget":
				System.out.println("controller spellcast method");
				CardButton esource = (CardButton) e.getSource();
				MinionCardButton em = ((MinionCardButton)esource);
				Minion spelltargetminion	=	em.getCard();
				Spell sp = ((SpellCardButton)em.getAttackedBy()).getCard();
				
				if(sp instanceof MinionTargetSpell)
					try {
						game.getCurrentHero().castSpell((MinionTargetSpell)sp, spelltargetminion);
					} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e2) {
						alert.error(e2.getMessage());
					}
			
				if(sp instanceof LeechingSpell)
					try {
						game.getCurrentHero().castSpell((LeechingSpell)sp, spelltargetminion);
					} catch (NotYourTurnException | NotEnoughManaException e2) {
						alert.error(e2.getMessage());
					}
			
				
			;break;
			
			case "spellattackhero":
				System.out.println("controller spellcast method");

				Spell spell = ((SpellCardButton)gamesc.attacker).getCard();
				
				if(spell instanceof HeroTargetSpell)
					try {
						game.getCurrentHero().castSpell((HeroTargetSpell)spell, game.getCurrentHero());
					} catch (NotYourTurnException | NotEnoughManaException e2) {
						alert.error(e2.getMessage());
					}
				
			;break;
			
			case "HeroPower":
				
				try {
					game.getCurrentHero().useHeroPower();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					alert.error(e1.getMessage());
				}
				
				;break;

				
				
		}
		
		if(_updatethisround) {
			gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		}
		
		
	}


	@Override
	public void initializeGame(Hero p1, Hero p2) {
		try {
			game = new Game(p1, p2);
		} catch (FullHandException | CloneNotSupportedException e) {
			(new alertBox()).error("Error initilizeing the game engine");
			e.printStackTrace();
		}
		
		
		gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		
	}

	
	
	
	
	
	
}

