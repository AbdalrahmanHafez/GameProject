package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import assets.ImageButton;
import assets.ImagePanel;
import assets.CardButton;
import model.cards.Card;
import model.heroes.Hero;
import net.miginfocom.swing.MigLayout;

public class GameScreen extends JFrame implements ActionListener{
	
	GameScreenListener listener;
	
	
	JPanel migpanel = new JPanel(new MigLayout("fill"));

	JButton btnHero2Pic = new JButton("Opponent Picture");
	JLabel	lblHero2Mana = new JLabel("opponent mana", SwingConstants.CENTER);

	JButton btnHero2Deck = new JButton("Oponent Deck");

	JPanel 	panHero2Field = new JPanel(new FlowLayout());
	JPanel 	panHeroField = new JPanel(new FlowLayout());
	JPanel	panHeroHand = new JPanel(new FlowLayout());
	JPanel 	panHero2Hand = new JPanel(new FlowLayout());
	
	JButton btnHeroDeck = new JButton("Hero Deck, Draw a new Card");

	JButton btnHeroPic = new JButton("Hero Picture");
	ImageButton btnHeroPower = new ImageButton(50,50 ,true);

	JLabel	lblHeroMana = new JLabel("Hero mana", SwingConstants.CENTER);
	
	JButton btnHero2Info = new JButton("opponent info");
	JButton btnEndTurn = new JButton("EndTurn");
	ImagePanel imgPreview = new ImagePanel("resources/images/Cards/spell.png");

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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/* Buttons action assignments */
		
		
		btnHeroDeck.setActionCommand("draw"); btnHeroDeck.addActionListener(this);
		btnEndTurn.setActionCommand("endturn"); btnEndTurn.addActionListener(this);		
		
		 /* END Buttons action assignments */

		/* components Adjustments */
	
		topPanle.setPreferredSize(new Dimension(100,180));
		bottomPanle.setPreferredSize(new Dimension(100,180));
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

		btnHeroPower.setText("use POWER");
		
		panHeroField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		panHero2Field.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		panHeroHand.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		panHero2Hand.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		
		/* End components Adj */

		
		topPanle.add(btnHero2Pic, "grow");
		topPanle.add(panHero2Hand, "grow, w 1500");
		topPanle.add(lblHero2Mana, "grow");
		  
		
		leftPanle.add(txtInfo, "grow, wrap");
		leftPanle.add(imgPreview, "grow");
	
		rightPanle.add(btnHero2Deck, "grow, wrap, w 100!");
		rightPanle.add(btnEndTurn	, "growx, wrap, w 100!");
		rightPanle.add(btnHeroDeck	, "grow, wrap, w 100!");
  
		
		bottomPanle.add(btnHeroPic, "grow");
		bottomPanle.add(panHeroHand, "grow,w 1100");
//		bottomPanle.add(btnHeroPower, "grow");
		
		
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
	


		
	
	public void updateInfo(Hero CurrentHero, Hero OpponentHero) {
		
		txtInfo.setText("====[GAME INFO]===="
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

		panHeroHand.removeAll();
		for(Card c : CurrentHero.getHand()) {
			addCardTo(c, panHeroHand, false, true, true);
		}
		
		panHeroField.removeAll();
		for(Card c : CurrentHero.getField()) {
			addCardTo(c, panHeroField, false, true, true);
		}
	
		
		panHero2Hand.removeAll();
		for(Card c : OpponentHero.getHand()) {
			addCardTo(c, panHero2Hand, true, false, false);
		}
		
		
		panHero2Field.removeAll();
		for(Card c : OpponentHero.getField()) {
			addCardTo(c, panHero2Field, false, false, false);
		}
	
		this.repaint();
		this.revalidate();
		
	}
	
	

	public void addCardTo(Card Card, JPanel panel, boolean hidden, boolean showOverlay, boolean clickable) {
		if (Card == null) {return;}
		
		CardButton btn = new CardButton(hidden, showOverlay,clickable);
		btn.setCard(Card);
		

		btn.setListener(this);
		
		panel.add(btn);
		
		this.repaint();
		this.revalidate();
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		Listens to CardButtons
		listener.actionPerformed(e);
	}
	
	
	public void setListener(GameScreenListener listener) {
		this.listener = listener;
	}


}
