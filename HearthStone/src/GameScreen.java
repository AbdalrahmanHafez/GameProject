import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class GameScreen extends JFrame {

	public GameScreen() {
		this.setTitle("HearthStone version 0.01");
		this.setBounds(10, 20, 1930, 1030);
		this.setLocationRelativeTo(null); // will center the window on the screen
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen
//		this.setResizable( false );
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel migpanel = new JPanel(new MigLayout("fill"));

		this.setLayout(new BorderLayout());

		JButton btnHero2Pic = new JButton("Opponent Picture");
		JButton btnHero2Hand = new JButton("How many in opponent hand");
		JButton btnHero2Mana = new JButton("opponent mana");

		JButton btnHero2Field = new JButton("Oponent Field");
		JButton btnHero2Deck = new JButton("Oponent Deck");

		JButton btnHeroField = new JButton("Hero Field");
		JButton btnHeroDeck = new JButton("Hero Deck");

		JButton btnHeroPic = new JButton("Hero Picture");
		JButton btnHeroHand = new JButton("How many in Hero hand");
		JButton btnHeroPower = new JButton("Hero POWER");
		JButton btnHeroMana = new JButton("Hero mana");

		JButton btnHero2Info = new JButton("opponent info");
		JButton btnHeroInfo = new JButton("info");
		JButton btnEndTurn = new JButton("EndTurn");


		
		JPanel topPanle		 	= new JPanel(new MigLayout("fill"));
		JPanel leftPanle 		= new JPanel(new MigLayout("fill"));
		JPanel rightPanle 		= new JPanel(new MigLayout("fill"));
		JPanel bottomPanle 	= new JPanel(new MigLayout("fill"));
		JPanel centerPanle 	= new JPanel(new MigLayout("fill"));

		/* components Adjustments */
	
		topPanle.setPreferredSize(new Dimension(100,120));
		bottomPanle.setPreferredSize(new Dimension(100,120));

		
		
		/* End components Adj */

		
		topPanle.add(btnHero2Pic, "grow");
		topPanle.add(btnHero2Hand, "grow, w 1200");
		topPanle.add(btnHero2Mana, "grow");
		  
		
		leftPanle.add(btnHeroInfo, "grow");
			  
	
		rightPanle.add(btnHero2Deck, "grow, wrap");
		rightPanle.add(btnEndTurn	, "grow x, wrap");
		rightPanle.add(btnHeroDeck, "grow, wrap");
  
		
		bottomPanle.add(btnHeroPic, "grow");
		bottomPanle.add(btnHeroHand, "grow,w 1100");
		bottomPanle.add(btnHeroPower, "grow");
		bottomPanle.add(btnHeroMana, "grow");
 
		  
		centerPanle.add(btnHero2Field,"grow, wrap");
		centerPanle.add(btnHeroField,"grow");
		  
		  
  
		this.add(topPanle			, BorderLayout.NORTH); 
		this.add(leftPanle			,BorderLayout.WEST);
		this.add(rightPanle		, BorderLayout.EAST);
		this.add(bottomPanle	, BorderLayout.SOUTH);
		this.add(centerPanle		,BorderLayout.CENTER);

		  
		this.repaint();
		this.revalidate();

	}

}
