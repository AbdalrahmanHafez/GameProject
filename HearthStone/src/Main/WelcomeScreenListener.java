package Main;
import java.awt.event.ActionEvent;

import model.heroes.Hero;

public interface WelcomeScreenListener {
	public void actionPerformed(ActionEvent arg);
	public void initializeGame(Hero p1, Hero p2);
	
	
	
}
