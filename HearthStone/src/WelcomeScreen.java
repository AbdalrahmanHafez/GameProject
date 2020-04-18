import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.*;    


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import assets.*;
import model.heroes.Hero;
import model.heroes.*;
import net.miginfocom.swing.MigLayout;


public class WelcomeScreen extends JFrame implements ActionListener {
	
	WelcomeScreenListener listener;
	
	Hero p1;
	Hero p2;
	
	private Color colorDarkBlue = new 		Color(59, 89, 182);
	private Color colorGrayBlue = new 		Color(161, 225, 255);
	private Color colorWhiteBlue = new		Color(62, 157, 201);
	private Color colorLightBlue = new		Color(161, 225, 255);

	 ArrayList<String> Heronames = new ArrayList<String>( 
	            Arrays.asList(	"<html><center> [Mage] <br> Jaina Proudmore		</html>", //1
	            					"<html><center>	[Hunter] <br> Rexxar					</html>", 	//2
	            					"<html><center>	[Paladin] <br> Uther Lightbringer </html>",	//3
	            					"<html><center>	[Priest] <br> Anduin Wrynn			</html>",	//4
	            					"<html><center>	[Warlock] <br> Gul'dan				</html>")); 	//5
	 
	 alertBox alert = new alertBox();
	 
	int posX=0,posY=0;
			
	public WelcomeScreen() {
		
		this.setTitle("HearthStone version 0.01");
		this.setBounds(10,20, 	 1500, 700);
		this.setLocationRelativeTo(null); // will center the window on the screen
		this.setUndecorated( true );
		this.setResizable( true );
		this.setVisible( true );

		
		
		
		JPanel migpanel = new JPanel(new MigLayout("fill"));
		migpanel.setBackground(colorWhiteBlue);
		

		
		JButton btnMin = new JButton("-");
		btnMin.setBackground(colorDarkBlue);
		btnMin.setForeground(Color.WHITE);
		btnMin.setFocusPainted(false);
		btnMin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMin.setActionCommand("min");
		btnMin.addActionListener (this);

		
		JButton btnMax = new JButton("🗖");
		btnMax.setBackground(colorDarkBlue);
		btnMax.setForeground(Color.WHITE);
		btnMax.setFocusPainted(false);
		btnMax.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMax.setActionCommand("max");
		btnMax.addActionListener (this);

				
		JButton btnclose = new JButton("x");
        btnclose.setBackground(colorDarkBlue);
        btnclose.setForeground(Color.WHITE);
        btnclose.setFocusPainted(false);
        btnclose.setFont(new Font("Tahoma", Font.BOLD, 12));
        
		btnclose.addActionListener (new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				  System.exit(0);
				 }
		});
		
		JPanel controlBtnsPanel = new JPanel(new MigLayout("align right"));
		controlBtnsPanel.setBackground(colorDarkBlue);
		controlBtnsPanel.add(btnMin);
		controlBtnsPanel.add(btnMax);
		controlBtnsPanel.add(btnclose);
		migpanel.add(controlBtnsPanel,"dock north");
		
		
		
		migpanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, colorLightBlue));
		

		
		JLabel txtheadTitle = new JLabel("HearthStone");
		txtheadTitle.setForeground(Color.white);
		txtheadTitle.setFont (txtheadTitle.getFont ().deriveFont (64.0f));
		txtheadTitle.setBorder(new CompoundBorder( 
				BorderFactory.createMatteBorder(0, 0, 3, 0,  Color.white ),
				new EmptyBorder(10,50,10,10))
			);
		
		migpanel.add(txtheadTitle, "grow, wrap");
		
		
		JLabel txtchoose1 = new JLabel("Choose Player 1:");
		txtchoose1.setForeground(Color.white);
		txtchoose1.setFont (txtheadTitle.getFont ().deriveFont (24.0f)); 
		txtchoose1.setBorder(new CompoundBorder( 
				BorderFactory.createMatteBorder(0, 4, 0, 0,  Color.green),
				new EmptyBorder(0,10,0,0))
			);
		
		migpanel.add(txtchoose1, "grow, wrap, gaptop 50");


		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setBackground(new Color(87, 138, 201));
		panel1.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, colorGrayBlue));


		ArrayList<JButton> btnsChoose1 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
			btn.setIcon(new ImageIcon("resources/Card.png"));
			btn.addActionListener(this);
			btn.setActionCommand("p1"+i) ;
			btn.setText(Heronames.get(i));
			btnsChoose1.add(btn);
			btn.setPreferredSize(new Dimension(250,150));
			btn.setBackground(colorDarkBlue);
	        btn.setForeground(Color.WHITE);
	        btn.setFocusPainted(false);
	        btn.setFont(new Font("Tahoma", Font.BOLD, 20));
	        	        
			panel1.add(btn);
		}
		migpanel.add(panel1, "grow, wrap");
		
		
		
		
		
		JLabel txtchoose2 = new JLabel("Choose Player 2:");
		txtchoose2.setForeground(Color.white);
		txtchoose2.setFont (txtchoose2.getFont ().deriveFont (24.0f));
		txtchoose2.setBorder(new CompoundBorder( 
				BorderFactory.createMatteBorder(0, 4, 0, 0,  Color.green),
				new EmptyBorder(0,10,0,0))
			);


		migpanel.add(txtchoose2, "wrap, gaptop 20");

		
		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setBackground(new Color(87, 138, 201));
		panel2.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, colorGrayBlue));
		
		ArrayList<JButton> btnsChoose2 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
			btnsChoose2.add(btn);
			btn.addActionListener(this);
			btn.setActionCommand("p2"+i) ;
			btn.setText(Heronames.get(i));
			btn.setPreferredSize(new Dimension(250,150));
			btn.setBackground(colorDarkBlue);
	        btn.setForeground(Color.WHITE);
	        btn.setFocusPainted(false);
	        btn.setFont(new Font("Tahoma", Font.BOLD, 20));
	        
			panel2.add(btn);
		}
		
		migpanel.add(panel2, "grow, wrap");

			
		
		JButton StartGamebtn = new JButton("Lets GO !");
		StartGamebtn.setPreferredSize(new Dimension(1500,50));
		StartGamebtn.setBackground(colorDarkBlue);
		StartGamebtn.setForeground(Color.WHITE);
		StartGamebtn.setFocusPainted(false);
		StartGamebtn.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		migpanel.add(StartGamebtn, "grow, gaptop 20");

		
		StartGamebtn.addActionListener(this);
		StartGamebtn.setActionCommand("start");
		
		

		
		this.add(migpanel);
		
		
		this.repaint();
		this.revalidate();
		
		
		

		
		
		// Drag and -Resize undecorated JFrame
		this.addMouseListener(new MouseAdapter()
		{
		   public void mousePressed(MouseEvent e)
		   {
		      posX=e.getX();
		      posY=e.getY();
		   }
		   
		});
		
		
		this.addMouseMotionListener(new MouseAdapter()
		{
		     public void mouseDragged(MouseEvent evt)
		     {
				//sets frame position when mouse dragged			
				setLocation (evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
							
		     }
		});
		
		
				
		
	}


	public void actionPerformed(ActionEvent e) {
		JButton btnpressed = (JButton)e.getSource();

		if(e.getActionCommand().equals("max"))
			if(this.getExtendedState() == JFrame.MAXIMIZED_BOTH)
				this.setExtendedState(0); 
			else
				this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		
		if(e.getActionCommand().equals("min")) {
			this.setExtendedState(JFrame.ICONIFIED); 	
		}
		
		try {
		switch (e.getActionCommand()){
			case "p11":
							p1 = new Mage() 			;break;
			case "p12":
							p1 = new Hunter() 		;break;
			case "p13":
							p1 = new Paladin() 		;break;
			case "p14":
							p1 = new Priest() 			;break;
			case "p15":
							p1 = new Warlock() 		;break;
			case "p21":
							p2 = new Mage() 			;break;
			case "p22":
							p2 = new Hunter() 		;break;
			case "p23":
							p2 = new Paladin() 		;break;
			case "p24":
							p2 = new Priest() 			;break;
			case "p25":
							p2 = new Warlock() 		;break;
		}
		}catch(Exception err) 
			{err.getMessage();}
		
		
		if(e.getActionCommand().equals("start")){
//			TODO assuming players 
			try {
			p1 = new Priest();
			p2 = new Mage();}catch(Exception ee) {;}
				if (	p1 == null || p2 == null 	) {
					alert.error("Please choose a hero for each player");
					return;
				}

				listener.initializeGame(p1, p2);
				listener.actionPerformed(e);
		}
		
		
	}



	
	
	public void setListener(WelcomeScreenListener listener) {
		this.listener = listener;
	}
	
	
}
