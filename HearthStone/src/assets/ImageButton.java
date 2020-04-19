package assets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.xml.bind.Marshaller.Listener;

import Main.GameScreenListener;
import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.Spell;


public class ImageButton extends JButton implements ActionListener {
	protected String imagePath;
	protected JLabel Label;
	protected boolean clickable = true;

	protected ActionListener listener;

	
	
	public ImageButton(int w, int h, boolean clickable)
	{
		this.setPreferredSize(new Dimension(w, h));
		this.setLayout(new BorderLayout());
		
		Label=new JLabel("", JLabel.CENTER);
		Label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label.setForeground(Color.black);
		
		this.add(Label, BorderLayout.SOUTH);	
		
//		By default
		setText("");
		setImage("resources/images/default.png");
		
		
		this.clickable = clickable;
		
		this.addActionListener(this);
		this.setActionCommand("CardButton");
		

		}
		
		
	

	
	public void setImage(String imgPath) {
		this.imagePath = imgPath;
	}
	public void setText(String txt) {
		this.Label.setText(txt);
	}

	
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(new File(imagePath)), 0, 0, null);
		} catch (IOException e) {
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(clickable)
			listener.actionPerformed(e);
	}
	
	
	public void setListener(ActionListener listener) {
		this.listener = listener;
	}



	public boolean isClickable() {
		return clickable;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
}
