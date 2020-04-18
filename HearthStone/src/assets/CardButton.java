package assets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CardButton extends JButton {
	private String imagePath;
	private JLabel Label;
	

	
	public CardButton(int w, int h)
	{
		this.setPreferredSize(new Dimension(150, 100));
		Label=new JLabel("", JLabel.CENTER);
		Label.setForeground(Color.white);
		this.add(Label);	}
	
	
	
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
//			g.drawImage(ImageIO.read(new File("resources/images/Cards/spell.png")), 0, 0, null);
			g.drawImage(ImageIO.read(new File(imagePath)), 0, 0, null);
		} catch (IOException e) {
		}
	}


//	public static void main(String[] args) {
//		System.out.println("sss");
//		JFrame f = new JFrame();
//		f.setLayout(new FlowLayout());
//		CardButton btn = new CardButton(120, 150);
//		f.setBounds(0, 0, 500, 700);
//		f.add(btn);
//		
//		f.setVisible(true);
//		f.repaint();
//		f.revalidate();
//		
//	}
	
	
}
