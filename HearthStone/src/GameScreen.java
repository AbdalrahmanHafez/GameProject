import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
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

		
//		  migpanel.add(btnHero2Pic, "grow"); migpanel.add(btnHero2Hand,
//		  "grow, span 2 1"); migpanel.add(btnHero2Mana, "grow, wrap");
//		  
//		  migpanel.add(btnHero2Info, "grow"); migpanel.add(btnHero2Field,
//		  "grow, span 2 1");
//		  
//		  JPanel migp2= new JPanel(new MigLayout("fill")); migp2.add(btnEndTurn,
//		  "grow, cell 0 0"); migp2.add(btnHero2Deck, "grow, cell 0 1");
//		  
//		  migpanel.add(migp2, "grow, wrap");
//		 
//		  migpanel.add(btnHeroInfo, "grow"); migpanel.add(btnHeroField,
//		  "grow, span 2 1"); migpanel.add(btnHeroDeck, "grow, span 2 1, wrap");
//		  
//		  migpanel.add(btnHeroPic, "grow"); migpanel.add(btnHeroHand,
//		  "grow, span 2 1");
//		  
//		  JPanel migp3= new JPanel(new MigLayout("fill")); migp3.add(btnHeroPower,
//		  "grow, cell 0 0"); migp3.add(btnHeroMana, "grow, cell 1 0");
//		  
//		  migpanel.add(migp3, "grow");
//	

		JPanel topPanle = new JPanel(new FlowLayout());
		JPanel leftPanle = new JPanel(new FlowLayout());
		JPanel rightPanle = new JPanel(new GridLayout(3, 1));
		JPanel bottomPanle = new JPanel(new FlowLayout());
		JPanel centerPanle = new JPanel(new GridLayout(2, 1));

		/* Buttons Adjustments */

		btnHero2Hand.setPreferredSize(new Dimension(1350, 120));
		btnHero2Pic.setPreferredSize(new Dimension(250, 120));
		btnHero2Mana.setPreferredSize(new Dimension(250, 120));

		btnHeroHand.setPreferredSize(new Dimension(1200,120));
		btnHeroPic.setPreferredSize(new Dimension(250, 120));
		btnHeroPower.setPreferredSize(new Dimension(150,120));
		btnHeroMana.setPreferredSize(new Dimension(250, 120));
		
		btnHeroInfo.setPreferredSize(new Dimension(70,800));
		
		btnEndTurn.setPreferredSize(new Dimension(10, 10 ));
		
		/* End Button Adj */

		
		  topPanle.add(btnHero2Pic);
		  topPanle.add(btnHero2Hand);
		  topPanle.add(btnHero2Mana);
		  
		  leftPanle.add(btnHeroInfo);
		  
		  rightPanle.add(btnHero2Deck);
		  
//		  TODO maybe do a verical box layout
		  JPanel endPanel = new JPanel();
		  btnEndTurn.setPreferredSize(new Dimension(100,150));
		  endPanel.add(btnEndTurn);
		  rightPanle.add(endPanel);
		  
		  rightPanle.add(btnHeroDeck);
		  
		  bottomPanle.add(btnHeroPic ); 
		  bottomPanle.add(btnHeroHand);
		  bottomPanle.add(btnHeroPower);
		  bottomPanle.add(btnHeroMana);
		  
		  centerPanle.add(btnHero2Field); centerPanle.add(btnHeroField);
		  
		  
		  topPanle.setPreferredSize(new Dimension(100,120));
		  
		  this.add(topPanle, BorderLayout.NORTH); 
		  this.add(leftPanle,BorderLayout.WEST);
		  this.add(rightPanle, BorderLayout.EAST);
		  this.add(bottomPanle, BorderLayout.SOUTH);
		  this.add(centerPanle,BorderLayout.CENTER);


//		this.add(migpanel);
		  
		this.repaint();
		this.revalidate();

	}

}
