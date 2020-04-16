import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Controller implements ActionListener, WelcomeScreenListener{
	
	private WelcomeScreen welcomesc;
	private GameScreen gamesc;
	
	public Controller(){
		welcomesc  = new WelcomeScreen();
		gamesc = new GameScreen();
		welcomesc.setListener(this);
	}
	
	
	public static void main(String[] args) {
		
		new Controller();
	}

	public void actionPerformed(ActionEvent arg) {
		// TODO button Actions 

		switch (arg.getActionCommand()){
			
			case "startgame":
				welcomesc.setVisible(false);
				gamesc.setVisible(true); 			break;
		
		
		
		}
		
	}
	
	
}

