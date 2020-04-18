import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import model.heroes.Hero;
import net.miginfocom.swing.MigLayout;

public class GameScreen extends JFrame implements ActionListener{
	
	GameScreenListener listener;
	
	


	JPanel migpanel = new JPanel(new MigLayout("fill"));

	JButton btnHero2Pic = new JButton("Opponent Picture");
	JButton btnHero2Hand = new JButton("How many in opponent hand");
	JLabel	lblHero2Mana = new JLabel("opponent mana", SwingConstants.CENTER);

	JButton btnHero2Deck = new JButton("Oponent Deck");

	JPanel 	panHero2Field = new JPanel(new FlowLayout());
	JPanel 	panHeroField = new JPanel(new FlowLayout());
	
	JButton btnHeroDeck = new JButton("Hero Deck, Draw a new Card");

	JButton btnHeroPic = new JButton("Hero Picture");
	JButton btnHeroHand = new JButton("How many in Hero hand");
	JButton btnHeroPower = new JButton("Hero POWER");
	JLabel	lblHeroMana = new JLabel("Hero mana", SwingConstants.CENTER);
	
	JButton btnHero2Info = new JButton("opponent info");
	JButton btnEndTurn = new JButton("EndTurn");


	JTextPane txtInfo = new JTextPane();
	JPanel topPanle		 	= new JPanel(new MigLayout("fill"));
	JPanel leftPanle 		= new JPanel(new MigLayout("fill"));
	JPanel rightPanle 		= new JPanel(new MigLayout("fill"));
	JPanel bottomPanle 	= new JPanel(new MigLayout("fill"));
	JPanel centerPanle 	= new JPanel(new MigLayout("fill"));
	
	
	
	public GameScreen() {
		this.setTitle("HearthStone version 0.01");
		this.setBounds(10, 20, 1930, 1030);
		this.setLocationRelativeTo(null); // will center the window on the screen
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen
//		this.setResizable( false );
//		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/* Buttons action assignments */
		
		
		btnHeroDeck.setActionCommand("draw"); btnHeroDeck.addActionListener(this);
		btnEndTurn.setActionCommand("endturn"); btnEndTurn.addActionListener(this);		
		
		 /* END Buttons action assignments */

		/* components Adjustments */
	
		topPanle.setPreferredSize(new Dimension(100,120));
		bottomPanle.setPreferredSize(new Dimension(100,120));
		leftPanle.setPreferredSize(new Dimension(250, 0));
		txtInfo.setEditable(false);
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		Font fontPrince = null;
			try {
				fontPrince = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\other\\prince.ttf")).deriveFont(40f);
			} catch (FontFormatException | IOException e) {
				System.out.println("Error importing Prince font");
				e.printStackTrace();}
		lblHero2Mana.setFont(fontPrince);
		lblHeroMana.setFont(fontPrince);

		panHeroField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		panHero2Field.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));

		
		/* End components Adj */

		
		topPanle.add(btnHero2Pic, "grow");
		topPanle.add(btnHero2Hand, "grow, w 1500");
		topPanle.add(lblHero2Mana, "grow");
		  
		
		leftPanle.add(txtInfo, "grow");
			  
	
		rightPanle.add(btnHero2Deck, "grow, wrap");
		rightPanle.add(btnEndTurn	, "grow x, wrap");
		rightPanle.add(btnHeroDeck, "grow, wrap");
  
		
		bottomPanle.add(btnHeroPic, "grow");
		bottomPanle.add(btnHeroHand, "grow,w 1100");
		bottomPanle.add(btnHeroPower, "grow");
		bottomPanle.add(lblHeroMana, "grow");
 
		  
		centerPanle.add(panHero2Field,"grow, wrap");
		centerPanle.add(panHeroField,"grow");
		  
		  
  
		this.add(topPanle			, BorderLayout.NORTH); 
		this.add(leftPanle			,BorderLayout.WEST);
		this.add(rightPanle		, BorderLayout.EAST);
		this.add(bottomPanle	, BorderLayout.SOUTH);
		this.add(centerPanle		,BorderLayout.CENTER);

		  
		this.repaint();
		this.revalidate();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		listener.actionPerformed(e);
	}

		
	
	public void updateInfo(Hero CurrentHero, Hero OpponentHero) {
		
		txtInfo.setText("=====[GAME INFO]======"
				+ "\nPlaying : " 	     	+ CurrentHero.getName()
				+ "\nHP: " 					+ CurrentHero.getCurrentHP()
				+ "\nMana: " 				+ CurrentHero.getCurrentManaCrystals() + "/" + CurrentHero.getTotalManaCrystals()
				+ "\nHand left: "			+ CurrentHero.getHand().size()
				+ "\nDeck left: "			+ CurrentHero.getDeck().size()
				
				+"\n "
				
				+ "\nAgainst : " 	     	+ OpponentHero.getName()
				+ "\nHP: " 					+ OpponentHero.getCurrentHP()
				+ "\nMana: " 				+ OpponentHero.getCurrentManaCrystals() + "/" + OpponentHero.getTotalManaCrystals()
				+ "\nHand left: "			+ OpponentHero.getHand().size()
				+ "\nDeck left: "			+ OpponentHero.getDeck().size()
				);
		lblHeroMana.setText(CurrentHero.getCurrentManaCrystals() + " / " + CurrentHero.getTotalManaCrystals());
		lblHero2Mana.setText(OpponentHero.getCurrentManaCrystals() + " / " + OpponentHero.getTotalManaCrystals());

		
		
	}

	
	public void setListener(GameScreenListener listener) {
		this.listener = listener;
	}

}
