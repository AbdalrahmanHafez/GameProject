package Main;
import javax.swing.*;

import assets.ImageButton;
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
	
	private WelcomeScreen welcomesc;
	private GameScreen gamesc;
	private Game game;
	private alertBox alert = new alertBox();

	
	public Controller(){
		welcomesc  = new WelcomeScreen();
		gamesc = new GameScreen();
		welcomesc.setListener(this);
		gamesc.setListener(this);
		
	}
	
	
	public static void main(String[] args) {
		new Controller();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO button Actions 
		
		switch (e.getActionCommand()){
			case "start":
				welcomesc.setVisible(false);
				gamesc.setVisible(true);
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
				
			case "CardButton":
//					TODO cardButton action
				CardButton cbtn = ((CardButton)e.getSource());
			
				try {
					Minion m = (Minion)(cbtn.getCard());
					game.getCurrentHero().playMinion(m);
					} catch (NotYourTurnException e1) {
						alert.error("NOT your turn");
					} catch (NotEnoughManaException e1) {
						alert.info("You don't have enough Mana - Crystals !");
					} catch (FullFieldException e1) {
						alert.info("Your field is FULL");
					}
				
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

