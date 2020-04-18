package assets;

import javax.swing.JOptionPane;

public class alertBox extends  JOptionPane{
		
	public void error( String Message)
	 {
		String titleBar = "Error";
		display(Message, titleBar);
    }
	
	public void info( String Message)
	 {
		String titleBar = "Notification";
		display(Message, titleBar);
   }
	
	private void display(String Message, String titleBar) {
	       JOptionPane.showMessageDialog(null, Message, "MessageBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);

	}

}
