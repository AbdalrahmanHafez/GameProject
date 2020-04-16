import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class WelcomeScreen extends JFrame implements ActionListener {
	
	WelcomeScreenListener listener;
	
	public WelcomeScreen() {
		this.setTitle("HearthStone version 0.01");
		this.setBounds(10,20, 	 1500, 900);
		this.setLocationRelativeTo(null); // will center the window on the screen
		this.setVisible( true );
//		this.setResizable( false );
		
				
//		this.setLayout(new GridLayout(6,1));
		
		Box theBox = Box.createVerticalBox();

		
		
		JLabel label0 = new JLabel("HearthStone");
		label0.setFont (label0.getFont ().deriveFont (64.0f));
		label0.setBorder(new CompoundBorder(label0.getBorder(), new EmptyBorder(10,50,10,10)));
		theBox.add(label0);
		
		
		JLabel label1 = new JLabel("Choose Player 1:");
		label1.setFont (label0.getFont ().deriveFont (24.0f)); 
//		label1.setBorder(new CompoundBorder(label1.getBorder(), new EmptyBorder(0,50,0,0)));

		label1.setAlignmentX(java.awt.Component.LEFT_ALIGNMENT);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel pan = new JPanel(new FlowLayout());
		pan.add(label1);
		pan.setPreferredSize(new Dimension(200,10));
		//TODO welcomescreen text alignment
		
		theBox.add(label1);
		
//		theBox.add(Box.createVerticalStrut(30));

		
		
		JPanel panel0 = new JPanel(new FlowLayout());
		panel0.setBackground(new Color(87, 138, 201));

		ArrayList<JButton> btns0 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
//			TODO button action commands
			btn.setActionCommand("");
			btns0.add(btn);
			btn.setPreferredSize(new Dimension(250,150));
			panel0.add(btn);
		}
		
		theBox.add(panel0);
		
		JLabel label2 = new JLabel("Choose Player 2:");
		label2.setFont (label2.getFont ().deriveFont (24.0f));
//		label2.setBorder(new CompoundBorder(label2.getBorder(), new EmptyBorder(0,50,0,0)));
		theBox.add(label2);
		
		
//		theBox.add(Box.createVerticalStrut(10));

				
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setBackground(new Color(87, 138, 201));

		ArrayList<JButton> btns1 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
			btns1.add(btn);
			btn.setPreferredSize(new Dimension(250,150));
			panel1.add(btn);
		}
		
		theBox.add(panel1);
		
		
		
		JPanel panel2 = new JPanel(new FlowLayout());
		
		JButton StartGamebtn = new JButton("START");
		StartGamebtn.setPreferredSize(new Dimension(1500,50));
		
		StartGamebtn.addActionListener(this);
		StartGamebtn.setActionCommand("startgame");
		
		
		panel2.add(StartGamebtn);
		
		theBox.add(panel2);
		
		

		this.add(theBox);
		
		
		
		this.repaint();
		this.revalidate();
		
		
	}


	public void actionPerformed(ActionEvent e) {
		listener.actionPerformed(e);
	}
	
	public void setListener(WelcomeScreenListener listener) {
		this.listener = listener;
	}
	
	
}
