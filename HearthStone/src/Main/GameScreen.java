package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
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
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.Current;
import org.omg.CORBA.CurrentHelper;

import assets.ImageButton;
import assets.ImagePanel;
import assets.MinionCardButton;
import assets.SpellCardButton;
import assets.alertBox;
import assets.CardButton;
import assets.CardOverlayWindow;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import net.miginfocom.swing.MigLayout;

public class GameScreen extends JFrame implements ActionListener, ImageButtonListener,ControllerListener, ImagePanelListener{
	private alertBox alert = new alertBox();
	Font fontPrince = null;
	GameScreenListener listener;
	CardOverlayWindow cardoverlay = new CardOverlayWindow();
	

	ImageButton btnHero2Pic = new ImageButton(false);
	JLabel	lblHero2Mana = new JLabel("opponent mana", SwingConstants.CENTER);

	public class cardsLeft extends ImagePanel {
		public JLabel txt = new JLabel("#Value");
		public cardsLeft(String imgpath) {
			super(imgpath);
			txt.setForeground(Color.white);
			this.setLayout(null);
		    this.add(txt);
		}
		public void setText(String str) {
			txt.setText(str);
			txt.setFont(fontPrince.deriveFont(40.0f));
		}
		
		public void setTextLoc(int panw, int panh) {
			int xloc =(int) ( panw* 0.4);
			int yloc =(int) ( panh* 0.57);
			txt.setBounds(xloc, yloc, 100 , 100);
		}
		
		
	}
	
	cardsLeft imgHero2Deck = new cardsLeft("resources/images/cardleft.png"); 
	cardsLeft imgHeroDeck = new cardsLeft("resources/images/cardleft.png");

	
	JPanel 	panHero2Field = new JPanel(new FlowLayout());
	JPanel 	panHeroField = new JPanel(new FlowLayout());
	JPanel	panHeroHand = new JPanel(new FlowLayout());
	JPanel 	panHero2Hand = new JPanel(new FlowLayout());
	
	ImageButton btnHeroPic = new ImageButton(false);
	ImageButton btnHeroPower = new ImageButton(true);	
	ImageButton btnEndTurn = new ImageButton(true);
	
	
	JTextPane txtCardInfo= new JTextPane();
	JTextPane txtGeneralInfo = new JTextPane();
	
	
	JLabel	lblHeroMana = new JLabel("Hero mana", SwingConstants.CENTER);
	

	public class customMig extends JPanel{
		public customMig() {this.setLayout(new MigLayout("fill"));}
		public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        ImageIcon img = new ImageIcon("resources/images/back.png");
	        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	    }
	}
	public class topbot extends JPanel{
		public topbot() {this.setLayout(new MigLayout("fill"));}
		 public void paintComponent(Graphics g)	    {
		        super.paintComponent(g);
		        ImageIcon img = new ImageIcon("resources/images/back2.png");
		        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		    }
	}
	
	
	
	JPanel topPanle		 	= new topbot();
	JPanel leftPanle 		= new customMig();
	JPanel rightPanle 		= new customMig();
	JPanel bottomPanle 	= new topbot();
	JPanel centerPanle 	= new JPanel(new MigLayout("fill")) {
	    public void paintComponent(Graphics g)	    {
	        super.paintComponent(g);
	        ImageIcon img = new ImageIcon("resources/images/back.png");
	        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	    }
	};
	
	

//														CONSTRUCTOR
	public GameScreen() {
		this.setTitle("HearthStone version 1.0");
		this.setBounds(10, 20, 1930, 1030);
//		this.setMinimumSize(new Dimension(1500, 800)); //recommended 
		this.setMinimumSize(new Dimension(1470, 768)); // to match small screen laptops
		this.setLocationRelativeTo(null); // will center the window on the screen
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen
//		this.setResizable( false );
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.updateCursor("");

		try {
			fontPrince = Font.createFont(Font.TRUETYPE_FONT, new File("resources/other/prince.ttf")).deriveFont(40f);
		} catch (FontFormatException | IOException e) {
		}
		
		
		imgHero2Deck.setListener(this);
		imgHeroDeck.setListener(this);

		/* Buttons action assignments */

		
		btnEndTurn.setActionCommand("endturn"); btnEndTurn.setListener(this);		
		btnHeroPower.setActionCommand("HeroPower");	btnHeroPower.setListener(this);
		btnHero2Pic.setActionCommand("minionattack"); btnHero2Pic.setListener(this);
		
		 /* END Buttons action assignments */

		/* components Adjustments */
	

		leftPanle.setPreferredSize(new Dimension(250, 0));
	
		
		txtGeneralInfo.setEditable(false);
		txtGeneralInfo.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		txtCardInfo.setEditable(false);
		txtCardInfo.setFont(new Font("Tahoma", Font.BOLD, 17));
		
		
		
		lblHero2Mana.setFont(fontPrince);
		lblHeroMana.setFont(fontPrince);
		lblHero2Mana.setForeground(Color.WHITE);
		lblHeroMana.setForeground(Color.WHITE);

		btnHeroPower.setImage("resources/images/heropower.png");
		btnEndTurn.setImage("resources/images/endturn.png");

		
		
		panHeroField.setOpaque(false);
		panHero2Field.setOpaque(false);
		panHeroHand.setOpaque(false);
		panHero2Hand.setOpaque(false);
		
		btnHero2Pic.setOpaque(false);
		btnHero2Pic.setBackground(new Color(0,0,0,0));
		btnHero2Pic.setBorder(null);
		
		btnHeroPic.setOpaque(false);
		btnHeroPic.setBackground(new Color(0,0,0,0));
		btnHeroPic.setBorder(null);
		
		txtGeneralInfo.setOpaque(false);
		txtGeneralInfo.setBackground(new Color(0,0,0,0));
		txtGeneralInfo.setForeground(Color.WHITE);
		
		txtCardInfo.setOpaque(false);
		txtCardInfo.setBackground(new Color(0,0,0,0));
		txtCardInfo.setForeground(Color.WHITE);
		
		imgHero2Deck.setOpaque(false);
		imgHero2Deck.setBackground(new Color(0,0,0,0));
		
		imgHeroDeck.setOpaque(false);
		imgHeroDeck.setBackground(new Color(0,0,0,0));
		
		btnEndTurn.setOpaque(false);
		btnEndTurn.setBackground(new Color(0,0,0,0));
		btnEndTurn.setBorder(null);
		
		btnHeroPower.setOpaque(false);
		btnHeroPower.setBackground(new Color(0,0,0,0));
		btnHeroPower.setBorder(null);

		
		/* End components Adj */

		
		topPanle.add(btnHero2Pic, "grow, w 170::170");
		topPanle.add(panHero2Hand, "grow, w 1000:100%:");
		JPanel mana2Pan = new JPanel(new MigLayout("fill , insets 0"));
		mana2Pan.setOpaque(false); mana2Pan.setBackground(new Color(0,0,0,0));
		JLabel lblmana2 = new JLabel("Mana");
		lblmana2.setFont(fontPrince.deriveFont(35.0f));
		lblmana2.setForeground(Color.WHITE);
		mana2Pan.add(lblHero2Mana, "grow, wrap");
		mana2Pan.add(lblmana2, "center, gapbottom 10%");;
		topPanle.add(mana2Pan, "grow");
		  
		
		leftPanle.add(txtGeneralInfo, "growx, wrap");
		leftPanle.add(txtCardInfo, "grow");
		
	
		rightPanle.add(imgHero2Deck, "grow, wrap, w 200!");
		rightPanle.add(btnEndTurn	, "growx, wrap, w 20%, h 8%");
		rightPanle.add(imgHeroDeck	, "grow, wrap, w 200!");
		
		
		bottomPanle.add(btnHeroPic, "grow, w 170::170");
		bottomPanle.add(panHeroHand, "grow, w 1000:100%:");
		bottomPanle.add(btnHeroPower, "grow, w 150::, gapright 2%");

		JPanel manaPan = new JPanel(new MigLayout("fill , insets 0"));
		manaPan.setOpaque(false); manaPan.setBackground(new Color(0,0,0,0));
		JLabel lblmana = new JLabel("Mana");
		lblmana.setFont(fontPrince.deriveFont(35.0f));
		lblmana.setForeground(Color.WHITE);
		manaPan.add(lblHeroMana, "grow, wrap");
		manaPan.add(lblmana, "center, gapbottom 10%");
		bottomPanle.add(manaPan);
 
		
		centerPanle.add(panHero2Field,"grow, height 170:: , wrap");
		centerPanle.add(panHeroField,"grow, height 170::");
		
		
	
		
		this.add(topPanle			, BorderLayout.NORTH); 
		this.add(leftPanle			, BorderLayout.WEST);
		this.add(rightPanle		, BorderLayout.EAST);
		this.add(bottomPanle	, BorderLayout.SOUTH);
		this.add(centerPanle		, BorderLayout.CENTER);


				
		this.repaint();
		this.revalidate();

	}

	public void initializeGameScreen(Hero CurrentHero, Hero OpponentHero) { // will be called when the game window is shown
	
		btnHeroPic.setImage(CurrentHero.getAvatar());
		btnHero2Pic.setImage(OpponentHero.getAvatar());
		
		Controller.playSound("myGreetings");
		
	
	}
	
	public void updateInfo(Hero CurrentHero, Hero OpponentHero) {

		txtGeneralInfo.setText("====[GAME INFO]===="
				+ "\nAgainst : " 	     	+ OpponentHero.getName()
				+ "\nHP: " 					+ OpponentHero.getCurrentHP()
				+ "\nMana: " 				+ OpponentHero.getCurrentManaCrystals() + "/" + OpponentHero.getTotalManaCrystals()
				+ "\nHand left: "			+ OpponentHero.getHand().size()
				+ "\nDeck left: "			+ OpponentHero.getDeck().size()
				+ "\n"
				+ "\nPlaying : " 	     	+ CurrentHero.getName()
				+ "\nHP: " 					+ CurrentHero.getCurrentHP()
				+ "\nMana: " 				+ CurrentHero.getCurrentManaCrystals() + "/" + CurrentHero.getTotalManaCrystals()
				+ "\nHand left: "			+ CurrentHero.getHand().size()
				+ "\nDeck left: "			+ CurrentHero.getDeck().size()
				
				);
		lblHeroMana.setText(CurrentHero.getCurrentManaCrystals() + " / " + CurrentHero.getTotalManaCrystals());
		lblHero2Mana.setText(OpponentHero.getCurrentManaCrystals() + " / " + OpponentHero.getTotalManaCrystals());

		imgHero2Deck.setText(OpponentHero.getDeck().size()+"");
		imgHeroDeck.setText(CurrentHero.getDeck().size()+"");

		
		panHeroHand.removeAll();
		for(Card c : CurrentHero.getHand()) {
			addCardTo(c, panHeroHand, false, true, true, false);
		}
		
		panHeroField.removeAll();
		for(Card c : CurrentHero.getField()) {
			addCardTo(c, panHeroField, false, true, true, true);
		}
	
		
		panHero2Hand.removeAll();
		for(Card c : OpponentHero.getHand()) {
			addCardTo(c, panHero2Hand, true, false, false,false);
		}
		
		
		panHero2Field.removeAll();
		for(Card c : OpponentHero.getField()) {
			addCardTo(c, panHero2Field, false, false, false,true);
		}
	
		

		
		this.repaint();
		this.revalidate();
		
	}
	
	

	public void addCardTo(Card Card, JPanel panel, boolean hidden, boolean showOverlay, boolean clickable, boolean inField) {
		if (Card == null) {return;}
		
		CardButton btn = null;
		if(Card instanceof Minion) {
			btn = new MinionCardButton(hidden, showOverlay,clickable);
			((MinionCardButton) btn).setinField(inField);
			((MinionCardButton) btn).setTheCard((Minion)Card);
			
			
		}else
		if(Card instanceof Spell) {
			btn = new SpellCardButton(hidden, showOverlay,clickable);
			((SpellCardButton) btn).setTheCard((Spell)Card);
		}
		
			
		btn.setListener(this);
		panel.add(btn, BorderLayout.CENTER);
		
		this.repaint();
		this.revalidate();
	}

	
	
	public void updatetxtCardInfo(Card card) {
		String r = "\n====[CARD INFO]====";
		r += "\nName: " + card.getName();
		r += "\nManaCost: " + card.getManaCost();
		r += "\nRairty: " + card.getRarity().toString();
		if(card instanceof Minion) {
			r+= "\nAttack: " + ((Minion) card).getAttack();
			r += "\nHealth: " +((Minion) card).getCurrentHP() + "/" + ((Minion) card).getMaxHP();
			r += "\nis Taunt: " + ((Minion) card).isTaunt();
			r += "\nis Divine: " + ((Minion) card).isDivine();			
			r += "\nis Charged: " + !((Minion) card).isSleeping();
		}
		txtCardInfo.setText(r);
	}




	private boolean waitingfortarget = false;
	public CardButton attacker = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(waitingfortarget) {
			if(	!(e.getSource() instanceof CardButton) && e.getSource() != btnHero2Pic) {
				alert.error("Please select a valid target");
				return;
			}//otherwise
			waitingfortarget = false;
//			the opp field cards now not clickable 
			setFieldOppTo(false);
			attacker.setHighlightRemove();

			
			if(e.getSource() == btnHero2Pic) {
				if(attacker instanceof SpellCardButton) {
					e = new ActionEvent(e.getSource(), 0, "spellattackhero");
				}else
				if(attacker instanceof MinionCardButton) {
					e = new ActionEvent(e.getSource(), 0, "minionattackhero");
				}

				
			}else {
			
				if(attacker instanceof SpellCardButton) {
					CardButton source = (CardButton)  e.getSource(); 
					source.setAttackedBy(attacker);
					e = new ActionEvent(e.getSource(), 0, "spellcastontarget");
				}else
				if(attacker instanceof MinionCardButton) {
					CardButton source = (CardButton)  e.getSource(); 
					source.setAttackedBy(attacker);
					e = new ActionEvent(e.getSource(), 0, "minionattack");
				}

			}

			listener.actionPerformed(e); // sending the attack
			return;
		}
		
//		IF not Waiting for target, 
		
		if ( e.getSource() instanceof CardButton ) {
			CardButton source = (CardButton)  e.getSource(); 
		
			if(e.getActionCommand() == "spellcast") {
				if (! ((SpellCardButton) source).doesspellNeedaTarget() ) { // does not need a target
					attacker = source;
					listener.actionPerformed(e);
					return;
				}
			}
			
			if(e.getActionCommand() == "minionattack" || e.getActionCommand() == "spellcast") {
				if(e.getActionCommand() == "spellcast") {
					Controller.playSound("spellCast");
				}
				attacker = source;
				waitingfortarget = true;
//				enable the opp field cards to be clickable
				attacker.setHighlightMode();
				setFieldOppTo(true);
				return;
			}
			
		}
		
			listener.actionPerformed(e);
	}
	
	
	


	public void setListener(GameScreenListener listener) {
		this.listener = listener;
	}



	@Override
	public void showCardOverlay(CardButton btn, boolean show) {
		if ( show ) {
			cardoverlay.showoverlay(true);
			cardoverlay.setImage(btn.getCard());
			cardoverlay.updateLocation(btn, !btn.isClickable());
			
		}else {
			cardoverlay.showoverlay(false);
		}

	}
	
	private void setFieldOppTo(boolean flag) {
		for(int i = 0 ; i < panHero2Field.getComponentCount() ; i++) {
			CardButton btn = (CardButton)panHero2Field.getComponent(i);
			btn.setClickable(flag);
			
		}
		btnHero2Pic.setClickable(flag);
	}


	@Override
	public void updateDeckLabelLocation(int width, int height) {
		imgHero2Deck.setTextLoc(width, height);
		imgHeroDeck.setTextLoc(width, height);
	}
	


	@Override
	public void updateCursor(String Path) {
		Cursor cursor = null;
		if(Path == "") { // set the default mouse
			cursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon("resources/images/Cursors/cur.png")).getImage(),new Point(), "Normal Cursor");	
		}else {
			cursor = Toolkit.getDefaultToolkit().createCustomCursor((new ImageIcon(Path)).getImage(),new Point(), "Normal Cursor");
		}
		this.setCursor(cursor);
	}



	







	
	

}
