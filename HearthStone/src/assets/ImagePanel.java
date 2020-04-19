package assets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
    Image image, scaledImage;
    int w,h;
    
    public ImagePanel(String imgpath) {
    	this(imgpath, 0, 0);
    }
    
    public ImagePanel(String imgpath, int w, int h)
    {
    	
		Image image = new ImageIcon(imgpath).getImage();
        this.image = image;

        this.w = w;
        this.h = h;        	
        
        if( w==0 || h==0 ) {
        	Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
     	    setPreferredSize(size);
     	    setMinimumSize(size);
     	    setMaximumSize(size);
     	    setSize(size);
     	    setLayout(null);
        	        	
        	this.w = this.getWidth();
            this.h = this.getHeight();    
        	scaledImage = image;
        	return;
        }
    	
        scaledImage = image.getScaledInstance(w,h,
                                              Image.SCALE_DEFAULT);
    }
  
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.drawImage(scaledImage, 0,0 , this);
    }
  
    public Dimension getPreferredSize()
    {
        return new Dimension(this.w, this.h);
    }
}




