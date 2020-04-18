import javax.swing.*;

import assets.alertBox;
import engine.Game;
import exceptions.FullHandException;
import model.heroes.Hero;

import java.awt.*;
import java.awt.event.*;
public class Controller implements ActionListener, WelcomeScreenListener, GameScreenListener{
	
	private WelcomeScreen welcomesc;
	private GameScreen gamesc;
	private Game game;
	
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
				gamesc.setVisible(true)				;break;
				
			case "draw":
				try {
					game.getCurrentHero().drawCard();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
					
			case "endturn":
				(new alertBox()).info("Thanks For Playing : )");
			
		}
		
		
		
		
	}


	@Override
	public void initializeGame(Hero p1, Hero p2) {
		try {
			game = new Game(p1, p2);
		} catch (FullHandException | CloneNotSupportedException e) {
			(new alertBox()).error("Error inisilizeing the game engine");
			e.printStackTrace();
		}
		
		gamesc.updateInfo(game.getCurrentHero(), game.getOpponent());
		
	}

	
	
	
	
	
	
}

