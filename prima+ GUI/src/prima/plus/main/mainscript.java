package prima.plus.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class mainscript implements ActionListener
{
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getActionCommand().equals("compile"))
		{
			
			try {
				haupt.compile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		if(event.getActionCommand().equals("close"))
		{
			System.exit(0);			    
		}
		else if(event.getActionCommand().equals("grundform1"))
		{
			haupt.grundform1();
		}
	}

}