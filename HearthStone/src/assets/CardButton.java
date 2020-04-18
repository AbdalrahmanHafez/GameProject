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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.xml.bind.Marshaller.Listener;

import Main.GameScreenListener;


public class CardButton extends JButton implements ActionListener {
	private String imagePath;
	private JLabel Label;
	private boolean hidden = false;
	
	private ActionListener listener;
	
	public CardButton(int width, int hight, boolean h)
	{
		this.setPreferredSize(new Dimension(width, hight));
		this.setLayout(new BorderLayout());
		
		Label=new JLabel("", JLabel.CENTER);
		Label.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Label.setForeground(Color.black);
		
		this.hidden	=	h;
		
		this.addActionListener(this);
		
		if(!hidden) {
			this.add(Label, BorderLayout.SOUTH);	
			
			this.addMouseListener(new MouseAdapter() {
				 public void mouseEntered(MouseEvent me) {
					 ((CardButton) me.getSource()).setImage("resources/images/uparrow.png");
				 }
		         public void mouseExited(MouseEvent me) {
					 ((CardButton) me.getSource()).setImage("resources/images/Cards/spell.png");
		         }
		      });
		}
		
	
		
		
		
	}
	
	
	
	public void setImage(String imgPath) {
		this.imagePath = imgPath;
	}
	public void setText(String txt) {
		this.Label.setText(txt);
	}
	public void setHidden(boolean h) {
		this.hidden = h; 
	}
	public boolean isHidden() {
		return this.hidden; 
	}
	
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		try {
			if(hidden) {g.drawImage(ImageIO.read(new File("resources/images/qmark.png")), 0, 0, null);return;}
			
			g.drawImage(ImageIO.read(new File(imagePath)), 0, 0, null);
		} catch (IOException e) {
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		this.setActionCommand("CardButton");
		listener.actionPerformed(e);
	}
	
	
	public void setListener(ActionListener listener) {
		this.listener = listener;
	}
	
	

	
	
}
