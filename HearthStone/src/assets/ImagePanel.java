package assets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Main.ImagePanelListener;

public class ImagePanel extends JPanel{
    public Image scaledImage;
    private String imagePath;
    public int w,h;
    private boolean yetToDraw = true;
    private int DeckLabelUpdateOccur = 0;
    private boolean DeckLabelStopUpdating = false;

	private ImagePanelListener listener;


    public ImagePanel(String imgpath) {
    	this(imgpath, (new ImageIcon(imgpath)).getImage().getWidth(null),
    			(new ImageIcon(imgpath)).getImage().getWidth(null));
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
		if(yetToDraw) {
			scaledImage = new ImageIcon(imagePath).getImage().getScaledInstance(this.getWidth(),  this.getHeight(),Image.SCALE_DEFAULT);
			this.w = scaledImage.getWidth(null);
			this.h = scaledImage.getHeight(null);
			yetToDraw = false;
		}
		
		if (DeckLabelStopUpdating == false) {DeckLabelUpdateOccur +=1;}
		if(!DeckLabelStopUpdating && DeckLabelUpdateOccur >= 10 && listener != null && (this.w != -1 || this.w != 0) && (this.h != -1|| this.h != 0)) { 
			listener.updateDeckLabelLocation(scaledImage.getWidth(null), scaledImage.getHeight(null));
			DeckLabelStopUpdating = true;
		}

		g.drawImage(scaledImage, 0, 0, null);
        this.repaint();
		this.revalidate();


    	
    }
    public ImagePanelListener getListener() {
		return listener;
	}

	public void setListener(ImagePanelListener listener) {
		this.listener = listener;
	}


	
}




