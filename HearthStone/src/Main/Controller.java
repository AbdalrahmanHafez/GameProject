package Main;
import javax.swing.*;

import assets.ImageButton;
import assets.MinionCardButton;
import assets.alertBox;
import assets.CardButton;
import engine.Game;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import model.heroes.Hero;

import java.awt.*;
import java.awt.event.*;
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
		
		if(e.getActionCommand().equals("initialStart")) {
			Startsc.setVisible(false);
			welcomesc.setVisible(true);
			return;
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
				
			case "draw":
				Card drawnCard = null;

				try {
					drawnCard = game.getCurrentHero().drawCard();
				} catch (FullHandException | CloneNotSupportedException e1) {	
					alert.info("Your Hand is FULL");}
				

				;break;

			case "endturn":
			try { //TODO endturn could this give an error
				game.endTurn();
			} catch (FullHandException | CloneNotSupportedException e2) {
				e2.printStackTrace();
			}

				;break;
				
			case "minionplay":
//					TODO cardButton action
				System.out.println("minion play");
						
					try {
						MinionCardButton btnminion =(MinionCardButton) e.getSource();
						Minion m = (btnminion).getCard();
						game.getCurrentHero().playMinion(m);
						} catch (NotYourTurnException e1) {
							alert.error("NOT your turn");
						} catch (NotEnoughManaException e1) {
							alert.info("You don't have enough Mana - Crystals !");
						} catch (FullFieldException e1) {
							alert.info("Your field is FULL");
						}catch (Exception e1) {System.out.println("error i think");;}
			
			;break;
				
			case "minionattack":
				System.out.println("attacking");
			;break;
			
				
			case "HeroPower":
				try {
					game.getCurrentHero().useHeroPower();
				} catch (NotEnoughManaException e1) {
					alert.info("You don't have enoggggggggggggggugh Mana - Crystals !");
				} catch (HeroPowerAlreadyUsedException e1) {
					alert.error("You've used your power this turn");
				} catch (NotYourTurnException e1) {
					alert.error("NOT your turn");
				} catch (FullHandException e1) {
					alert.info("Your Hand is FULL");
				} catch (FullFieldException e1) {
					alert.info("Your field is FULL");
				} catch (CloneNotSupportedException e1) {
				}
				;break;

				
		

				
				
		}
		
		gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		
		
		
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

