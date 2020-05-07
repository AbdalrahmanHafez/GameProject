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

public class GameScreen extends JFrame implements ActionListener, ImageButtonListener,ControllerListener{
	private alertBox alert = new alertBox();
	GameScreenListener listener;
	CardOverlayWindow cardoverlay = new CardOverlayWindow();
	

	ImageButton btnHero2Pic = new ImageButton(false);
		
	JLabel	lblHero2Mana = new JLabel("opponent mana", SwingConstants.CENTER);

	ImagePanel btnHero2Deck = new ImagePanel("resources/images/Cards/spell.png");
	ImagePanel btnHeroDeck = new ImagePanel("resources/images/Cards/spell.png");
	
	
	JPanel 	panHero2Field = new JPanel(new FlowLayout());
	JPanel 	panHeroField = new JPanel(new FlowLayout());
	JPanel	panHeroHand = new JPanel(new FlowLayout());
	JPanel 	panHero2Hand = new JPanel(new FlowLayout());
	
	ImageButton btnHeroPic = new ImageButton(false);
	ImageButton btnHeroPower = new ImageButton(true);
	ImageButton btnEndTurn = new ImageButton(true);
	
	
	ImagePanel imgPreview = new ImagePanel("resources/images/Cards/spell.png");
	
	JTextPane txtCardInfo= new JTextPane();


	JLabel	lblHeroMana = new JLabel("Hero mana", SwingConstants.CENTER);
	

	
	JTextPane txtInfo = new JTextPane();
	JPanel topPanle		 	= new JPanel(new MigLayout("fill"));
	JPanel leftPanle 		= new JPanel(new MigLayout("fill"));
	JPanel rightPanle 		= new JPanel(new MigLayout("fill"));
	JPanel bottomPanle 	= new JPanel(new MigLayout("fill"));
	JPanel centerPanle 	= new JPanel(new MigLayout("fill")) {
	    public void paintComponent(Graphics g) 
	    {
	        super.paintComponent(g);
	        ImageIcon img = new ImageIcon("resources/images/wsbackground.png");
	        g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	    }
	};
	
	

//														CONSTRUCTOR
	public GameScreen() {
		this.setTitle("HearthStone version 0.7");
		this.setBounds(10, 20, 1930, 1030);
//		TODO minimum window size
		this.setMinimumSize(new Dimension(1500, 800));
		this.setLocationRelativeTo(null); // will center the window on the screen
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen
//		this.setResizable( false );
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.updateCursor("");
		
		
		panHeroField.setOpaque(false);
		
		/* Buttons action assignments */

		
		btnEndTurn.setActionCommand("endturn"); btnEndTurn.setListener(this);		
		btnHeroPower.setActionCommand("HeroPower");	btnHeroPower.setListener(this);
		btnHero2Pic.setActionCommand("minionattack"); btnHero2Pic.setListener(this);
		
		 /* END Buttons action assignments */

		/* components Adjustments */
	

		leftPanle.setPreferredSize(new Dimension(250, 0));
	
		
		txtInfo.setEditable(false);
		txtInfo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		txtCardInfo.setEditable(false);
		txtCardInfo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		Font fontPrince = null;
			try {
				fontPrince = Font.createFont(Font.TRUETYPE_FONT, new File("resources\\other\\prince.ttf")).deriveFont(40f);
			} catch (FontFormatException | IOException e) {
				System.out.println("Error importing Prince font");
				e.printStackTrace();}
		lblHero2Mana.setFont(fontPrince);
		lblHeroMana.setFont(fontPrince);

		btnHeroPower.setText("use POWER");

//		TODO imagebutton should be giving in the constructor the image path, not like this
		btnEndTurn.setImage("resources/images/endTurn.png");

		
		panHeroField.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		panHero2Field.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.DARK_GRAY));
		panHeroHand.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		panHero2Hand.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.gray));
		
		/* End components Adj */

		
		topPanle.add(btnHero2Pic, "grow, w 170::170");
		topPanle.add(panHero2Hand, "grow, w 1000:100%:");
		topPanle.add(lblHero2Mana, "grow");
		  
		
		leftPanle.add(txtInfo, "growx, wrap");
		leftPanle.add(txtCardInfo, "grow");
		
	
		rightPanle.add(btnHero2Deck, "grow, wrap, w 150!");
		rightPanle.add(btnEndTurn	, "growx, wrap, w 20%, h 7%");
		rightPanle.add(btnHeroDeck	, "grow, wrap, w 150!");
		
		
		bottomPanle.add(btnHeroPic, "grow, w 170::170");
		bottomPanle.add(panHeroHand, "grow, w 1000:100%:");
		bottomPanle.add(btnHeroPower, "grow, w 150::");
		
		bottomPanle.add(lblHeroMana, "grow");
 
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
		panel.add(btn);
		
		this.repaint();
		this.revalidate();
	}

	
	
	public void updatetxtCardInfo(Card card) {
		String r = "====[CARD INFO]====";
		r += "\nName: " + card.getName();
		r += "\nManaCost: " + card.getManaCost();
		r += "\nRairty: " + card.getRarity().toString();
		if(card instanceof Minion) {
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
		
		System.out.println("gamescreen");
		
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
			
			if(e.getActionCommand() == "minionattack" || e.getActionCommand() == "spellcast") {
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
	public void updateCardOverlay(CardButton btn, boolean show) {
		if ( show ) {
			cardoverlay.showoverlay(true);
			cardoverlay.updateLocation(btn);
			
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
