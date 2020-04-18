package assets;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;


public class CardButton extends JButton {
	
	public CardButton(int sizex, int sizey) {
		
		this.setPreferredSize(new Dimension(sizex, sizey));

	
	}

	
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try {
			g.drawImage(ImageIO.read(new File("resources/images/Card.png")), 0, 0, null);
		} catch (IOException e) {
			System.out.println("errow while importing");
		}
	}
	
	
	
}
