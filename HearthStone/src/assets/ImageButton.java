package assets;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.xml.bind.Marshaller.Listener;

import Main.GameScreenListener;
import Main.ImageButtonListener;
import model.cards.Card;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.Spell;


public class ImageButton extends JButton implements ActionListener {
	protected boolean hidden = false;
	protected String imagePath;
	protected String imageDefualtPath;
	protected String imageHiddenPath = "resources/images/hiddencard.png";
	protected JLabel Label;
	protected boolean clickable = true;
	private boolean yetToDraw = true;

	protected ImageButtonListener listener;

	Image scaledImage;
    int w,h;
	
	public ImageButton(int w, int h, boolean clickable)	{
		this.w = w;
		this.h = h;        	
		
		this.setPreferredSize(new Dimension(w, h));
		this.setLayout(new BorderLayout());
		
		Label=new JLabel("", JLabel.CENTER);
		Label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Label.setForeground(Color.black);
		
		this.add(Label, BorderLayout.SOUTH);	
		
		setText("");
		setImage("");
    	
    
		this.clickable = clickable;
		
		this.addActionListener(this);
		
		}
	

	
	public void setImage(String imgPath) {
		this.imagePath = imgPath;
		this.yetToDraw = true;
	}
	
	public void setDefultImage(String imgPath) {
		this.imageDefualtPath = imgPath;
	}
	
	
	public void setText(String txt) {
		this.Label.setText(txt);
	}

	
	protected void paintComponent(Graphics g)	{
		super.paintComponent(g);
		
		if(yetToDraw) { 
			if(!hidden) {
				scaledImage = new ImageIcon(imagePath).getImage().getScaledInstance(this.getWidth(),  this.getHeight(),Image.SCALE_DEFAULT);
			}else {
				scaledImage = new ImageIcon(imageHiddenPath).getImage().getScaledInstance(this.getWidth(),  this.getHeight(),Image.SCALE_DEFAULT);
			}
			yetToDraw = false;
		}
		
		g.drawImage(scaledImage, 0, 0, null);
        this.repaint();
		this.revalidate();


	}



	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(clickable+"image button "+ e.getActionCommand());
		if(clickable)
			listener.actionPerformed(e);
	}
	
	
	public void setListener(ImageButtonListener listener) {
		this.listener = listener;
	}

	public void setHidden(boolean h) {
		this.hidden = h; 
	}
	public boolean isHidden() {
		return this.hidden; 
	}
	public boolean isClickable() {
		return clickable;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
	
}