package Main;
import javax.swing.*;

import assets.ImageButton;
import assets.MinionCardButton;
import assets.SpellCardButton;
import assets.alertBox;
import assets.CardButton;
import engine.Game;
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
	
	private Minion minionattacker = null;
	private Minion minionattacktarget = null;
	
	private Spell spellattacker = null;

	
	private boolean waitingforatarget = false;
	private boolean waitingforatargetspell = false;

	
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
		Component[] panhero2fieldContainer = gamesc.panHero2Field.getComponents();
		Component[] panherofieldContainer = gamesc.panHeroField.getComponents();
		Component[] panhero2handContainer = gamesc.panHero2Hand.getComponents();
		Component[] panherohandContainer = gamesc.panHeroHand.getComponents();
		
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
				boolean _attacked = false;
				
				if(waitingforatarget == true) { //this is our target
					waitingforatarget = false;
					
					if(e.getSource() == gamesc.btnHero2Pic) { // the target is a hero
							try {
								game.getCurrentHero().attackWithMinion(minionattacker, game.getOpponent());
								gamesc.btnHero2Pic.setClickable(false);
								_attacked = true;
								System.out.println("attacked the hero");
							} catch (CannotAttackException | NotYourTurnException | TauntBypassException
									| NotSummonedException | InvalidTargetException e1) {
								alert.error(e1.getMessage());
							}
					}else {
						minionattacktarget = ((MinionCardButton)e.getSource()).getCard();
							try {
								game.getCurrentHero().attackWithMinion(minionattacker, minionattacktarget);
								_attacked = true;
								System.out.println("attacked a minion");
							} catch (CannotAttackException | NotYourTurnException | TauntBypassException
									| InvalidTargetException | NotSummonedException e1) {
								alert.error(e1.getMessage());
							}
					}
				
				}

					if( !_attacked) {
					System.out.println("wating for another minion to attack him");
					minionattacker = ((MinionCardButton)e.getSource()).getCard();
					waitingforatarget = true;
					gamesc.btnHero2Pic.setClickable(true);
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

			case "spellcast":
				System.out.println("controller castspell method");
				
				if(waitingforatargetspell == true) { //then this is our target
					Minion m = ((MinionCardButton) e.getSource()).getCard();
					
					try {
						game.getCurrentHero().castSpell((MinionTargetSpell) spellattacker, m);
					} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
						alert.error(e1.getMessage());
					} catch (ClassCastException ee) {;}
					
					try {
						game.getCurrentHero().castSpell((LeechingSpell) spellattacker, m);
					} catch (NotYourTurnException | NotEnoughManaException e1) {
						alert.error(e1.getMessage());
					} catch (ClassCastException ee) {;}
					
					
					
					waitingforatargetspell = false;
					spellattacker = null;
				}else{
				SpellCardButton btn = (SpellCardButton) e.getSource();
				Spell s = btn.getCard();
											
				if(btn.doesspellNeedaTarget()) { // Either minion target spell, leeching spell
					System.out.println("spell need a target");
					this.spellattacker = s;
					
					MinionCardButton[] panhero2fieldbuttons = Arrays.copyOf(panhero2fieldContainer, panhero2fieldContainer.length, MinionCardButton[].class);
					MinionCardButton[] panherofieldbuttons = Arrays.copyOf(panherofieldContainer, panherofieldContainer.length, MinionCardButton[].class);
					MinionCardButton[] panhero2handbuttons = Arrays.copyOf(panhero2handContainer, panhero2handContainer.length, MinionCardButton[].class);
					MinionCardButton[] panherohandbuttons = Arrays.copyOf(panherohandContainer, panherohandContainer.length, MinionCardButton[].class);
					
					for(MinionCardButton sbtn : panhero2fieldbuttons) {
						sbtn.setActionCommand("spellcast");
						sbtn.setClickable(true);
						_updatethisround = false;
					}
					for(MinionCardButton sbtn : panherofieldbuttons) {
						sbtn.setActionCommand("spellcast");
						sbtn.setClickable(true);
						_updatethisround = false;
					}
					for(MinionCardButton sbtn : panhero2handbuttons) {
						sbtn.setActionCommand("spellcast");
						sbtn.setClickable(false);
						_updatethisround = false;
					}
					for(MinionCardButton sbtn : panherohandbuttons) {
						sbtn.setActionCommand("spellcast");
						sbtn.setClickable(false);
						_updatethisround = false;
					}
					
					
					waitingforatargetspell = true;
					
				}else {
					System.out.println("spell does not need a target");
					try {
						game.getCurrentHero().castSpell((FieldSpell)s);
					} catch (NotYourTurnException | NotEnoughManaException e1) {
						e1.printStackTrace();
					} catch (ClassCastException ee) {;} // if can't cast it => dont use this method 
					
					try {
						game.getCurrentHero().castSpell((AOESpell)s, game.getOpponent().getField());
					} catch (NotYourTurnException | NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassCastException ee) {;}
					
					try {
						game.getCurrentHero().castSpell((HeroTargetSpell)s, game.getOpponent());
					} catch (NotYourTurnException | NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassCastException ee) {;}	
					
					waitingforatargetspell = false;
				}
				
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

