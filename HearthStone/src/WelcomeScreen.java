import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class WelcomeScreen extends JFrame{
	
	public WelcomeScreen() {
		this.setTitle("HearthStone version 0.01");
		this.setBounds(10,20, 	 1500, 900);
		this.setLocationRelativeTo(null); // will center the window on the screen
		this.setVisible( true );
//		this.setResizable( false );
		
		
		
		this.setLayout(new GridLayout(6,1));
		
		JLabel label0 = new JLabel("HearthStone");
		label0.setFont (label0.getFont ().deriveFont (64.0f));
		label0.setBorder(new CompoundBorder(label0.getBorder(), new EmptyBorder(10,50,10,10)));
		this.add(label0);
		
		
		JLabel label1 = new JLabel("Choose Player 1:");
		label1.setFont (label0.getFont ().deriveFont (24.0f)); 
		label1.setBorder(new CompoundBorder(label1.getBorder(), new EmptyBorder(0,50,0,0)));
		this.add(label1);
		
		JPanel panel0 = new JPanel(new FlowLayout());
		panel0.setBackground(new Color(87, 138, 201));

		ArrayList<JButton> btns0 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
			btns0.add(btn);
			btn.setPreferredSize(new Dimension(250,150));
			panel0.add(btn);
		}
		
		this.add(panel0);
		
		JLabel label2 = new JLabel("Choose Player 2:");
		label2.setFont (label2.getFont ().deriveFont (24.0f));
		label2.setBorder(new CompoundBorder(label2.getBorder(), new EmptyBorder(0,50,0,0)));
		this.add(label2);
		
				
		
		JPanel panel1 = new JPanel(new FlowLayout());
		panel1.setBackground(new Color(87, 138, 201));

		ArrayList<JButton> btns1 = new ArrayList<JButton> (5);
		for(int i = 0 ; i<5 ; i++) {
			JButton btn = new JButton("Button " + (i+1));
			btns1.add(btn);
			btn.setPreferredSize(new Dimension(250,150));
			panel1.add(btn);
		}
		
		

		this.add(panel1);
		
		
		
		
		JButton StartGamebtn = new JButton("START");
		this.add(StartGamebtn);
		
		this.repaint();
		this.revalidate();
		
		
	}
	
	
	
}
