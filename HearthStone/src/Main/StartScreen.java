package Main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener{
	
	
	private ActionListener listener;

	
   public StartScreen() {
		 this.setSize(new Dimension(740,416));
		 
	     this.setContentPane(new JLabel( new ImageIcon("resources/images/hearthstone.jpg")));
	     
		 this.setLayout(new FlowLayout(FlowLayout.LEFT,270,340));
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 JButton startGame = new JButton("Start Game");
		 startGame.setForeground(Color.BLACK);
		 startGame.setFont(new Font("Arial", Font.PLAIN, 30));
	    
		 this.setTitle("HearthStone version 1.0");
		this.add(startGame);
		this.setVisible(true);
              
        this.setLocationRelativeTo(null);
        
        pack();
        this.setResizable(false);
		
        startGame.addActionListener(this);
		startGame.setActionCommand("initialStart");
		
    }
   
   
 public void setListener(ActionListener listener) {
		this.listener = listener;
	}

@Override
public void actionPerformed(ActionEvent e) {
	
	listener.actionPerformed(e);
	
	}
}


