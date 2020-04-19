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
    private boolean fullimageflag = false;
    
    public ImagePanel(String imgpath) {
    	this(imgpath, 0, 0);
    }
    
    public ImagePanel(String imgpath, int w, int h)
    {
    	
		Image image = new ImageIcon(imgpath).getImage();
        this.image = image;

        this.w = w;
        this.h = h;        	
                   	
        scaledImage = image.getScaledInstance(w,h,
                                              Image.SCALE_DEFAULT);
    }
    
    public void setImageToFull() {
    	fullimageflag = true;
	}
  
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	if(this.getWidth() != 0 && this.getHeight() !=0 && fullimageflag) {
			scaledImage = image.getScaledInstance(this.getWidth(),  this.getHeight(),
	                Image.SCALE_DEFAULT);		
			fullimageflag = false;
		}
        g.drawImage(scaledImage, 0,0 , this);
    }
  
    public Dimension getPreferredSize()
    {
        return new Dimension(this.w, this.h);
    }

	
}




