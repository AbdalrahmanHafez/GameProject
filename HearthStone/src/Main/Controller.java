package Main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import assets.ImageButton;
import assets.MinionCardButton;
import assets.SpellCardButton;
import assets.alertBox;
import assets.CardButton;
import engine.Game;
import engine.GameListener;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Random;
import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller implements ActionListener, WelcomeScreenListener, GameScreenListener, GameListener{

	private StartScreen Startsc;
	private WelcomeScreen welcomesc;
	private GameScreen gamesc;
	private Game game;
	private alertBox alert = new alertBox();
 	
	ControllerListener Listener;
	
	public Controller(){
		Startsc = new StartScreen();
		welcomesc  = new WelcomeScreen();
		gamesc = new GameScreen();
				
		Startsc.setListener(this);
		welcomesc.setListener(this);
		gamesc.setListener(this);
		
		
		this.setListener(gamesc);
		
		Controller.playSound("gameplay");
		
	}
	
	
	public static void main(String[] args) {
		new Controller();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("initialStart")) { 
			Startsc.setVisible(false);
			welcomesc.setVisible(true);
			return; //to avoid calling the update method
		}
		
		switch (e.getActionCommand()){
		
			case "start":
				welcomesc.setVisible(false);
				gamesc.setVisible(true);
				
//TODO remove
				game.getCurrentHero().setTotalManaCrystals(10);
				game.getCurrentHero().setCurrentManaCrystals(10);
				game.getOpponent().setTotalManaCrystals(10);
				game.getOpponent().setCurrentManaCrystals(10);
				
				;break;
				
			case "endturn":
				Controller.playSound("endTurn");
				try {
					game.endTurn();
				} catch (FullHandException | CloneNotSupportedException e2) {
					alert.info(e2.getMessage());
				}
				

			;break;
				
				
			case "minionplay":
				try {
					MinionCardButton btnminion =(MinionCardButton) e.getSource();
					Minion m = (btnminion).getCard();
					game.getCurrentHero().playMinion(m);
					Controller.playSound(new String[] {"cardPlace1","cardPlace2"});
				} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e2) {
					alert.error(e2.getMessage());
				}

			;break;
				
			case "minionattack":
				CardButton minionsource = (CardButton)e.getSource();
				Minion attackerMinion = ((MinionCardButton)minionsource.getAttackedBy()).getCard();
				Minion targetMinion = ((MinionCardButton)minionsource).getCard();
				
				try {
					game.getCurrentHero().attackWithMinion(attackerMinion, targetMinion);
					if(targetMinion.getCurrentHP() == 0) // if dead
						{Controller.playSound("damage-dead");}
					else
						{Controller.playSound("damage");}
					
				} catch (CannotAttackException | NotYourTurnException | TauntBypassException
						| InvalidTargetException | NotSummonedException e1) {
					alert.error(e1.getMessage());
				}
						
			;break;
			
			case "minionattackhero":
				try {
						game.getCurrentHero().attackWithMinion(((MinionCardButton)gamesc.attacker).getCard() , game.getOpponent());
						Controller.playSound("damage");
					} catch (CannotAttackException | NotYourTurnException | TauntBypassException
							| NotSummonedException | InvalidTargetException e1) {
						alert.error(e1.getMessage());
					}

			;break;

			case "spellcast":
				Spell s = ((SpellCardButton)gamesc.attacker).getCard();
				try {
				if(s instanceof FieldSpell)
					game.getCurrentHero().castSpell((FieldSpell)s);
				if(s instanceof AOESpell)
					game.getCurrentHero().castSpell((AOESpell)s, game.getOpponent().getField());
				if(s instanceof HeroTargetSpell)
					game.getCurrentHero().castSpell((HeroTargetSpell)s, game.getOpponent());

				Controller.playSound("spellCast");
				} catch (NotYourTurnException | NotEnoughManaException e1) {
					alert.error(e1.getMessage());}
				
				
			;break;
		
			case "spellcastontarget":
				CardButton esource = (CardButton) e.getSource();
				MinionCardButton em = ((MinionCardButton)esource);
				Minion spelltargetminion	=	em.getCard();
				Spell sp = ((SpellCardButton)em.getAttackedBy()).getCard();
				try {
					if(sp instanceof MinionTargetSpell)
						game.getCurrentHero().castSpell((MinionTargetSpell)sp, spelltargetminion);
					if(sp instanceof LeechingSpell)
						game.getCurrentHero().castSpell((LeechingSpell)sp, spelltargetminion);
				} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e2) {
					alert.error(e2.getMessage());
				}
			
				
			;break;
			
			case "spellattackhero":
				Spell spell = ((SpellCardButton)gamesc.attacker).getCard();
				
				if(spell instanceof HeroTargetSpell)
					try {
						game.getCurrentHero().castSpell((HeroTargetSpell)spell, game.getOpponent());
					} catch (NotYourTurnException | NotEnoughManaException e2) {
						alert.error(e2.getMessage());
					}
				
			;break;
			
			case "HeroPower":
				
				try {
					game.getCurrentHero().useHeroPower();
					Controller.playSound("damage-heropower");
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException e1) {
					alert.error(e1.getMessage());
				}
			;break;

				
				
		}
		
		
		gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		
		
		
	}


	@Override
	public void initializeGame(Hero p1, Hero p2) {
		try {
			game = new Game(p2, p1);
		} catch (FullHandException | CloneNotSupportedException e) {
			(new alertBox()).error("Error initilizeing the game engine");
			e.printStackTrace();
		}
		
		gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		
		Listener.initializeGameScreen(game.getCurrentHero(), game.getOpponent());
		game.setListener(this);


	}
	
	@Override
	public void onGameOver() {
		Controller.playSound("victory");
		alert.info("YOU won horray!");
		alert.info("Hope you had fun :)");
		System.exit(0);
	}
	
	
	public static AudioInputStream x = null;
	public static Clip clip = null;
	
	public static void playSound(String name)	{
		File soundFile = new File("resources/other/sounds/" +name+ ".wav");
	
		try {
				x = AudioSystem.getAudioInputStream(soundFile);
				clip = AudioSystem.getClip();
				clip.open(x);

				if(name.equals("gameplay"))
					clip.loop(1000);
				else
					clip.start();

			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				(new alertBox()).error(e.getMessage());
			}
	}
	
	public static void playSound(String[] names)	{
		 Random rand = new Random(); 
		 int randIndex = rand.nextInt(names.length); 
		 playSound(names[randIndex]);
	}


	
	
	
	

	
		
	public ControllerListener getListener() {
		return Listener;
	}


	public void setListener(ControllerListener listener) {
		Listener = listener;
	}



}

