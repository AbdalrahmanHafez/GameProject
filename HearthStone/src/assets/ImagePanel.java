package assets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
    Image scaledImage;
    private String imagePath;
    int w,h;
    private boolean yetToDraw = true;

    public ImagePanel(String imgpath) {
    	this(imgpath, 0, 0);
    }
    
    public ImagePanel(String img, int w, int h)
    {
    	this.imagePath = img;
    	this.w = w;
    	this.h = h;        	
    }
    

    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
		if(yetToDraw) 
			scaledImage = new ImageIcon(imagePath).getImage().getScaledInstance(this.getWidth(),  this.getHeight(),Image.SCALE_DEFAULT);
		yetToDraw = false;
		
		
		g.drawImage(scaledImage, 0, 0, null);
        this.repaint();
		this.revalidate();


    	
    }
 


	
}




