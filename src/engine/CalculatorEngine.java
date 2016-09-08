package engine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import ui.Calculator;
import enums.Button;


public class CalculatorEngine implements ActionListener {
	Calculator parent;
	//save instance of Calculator
	public CalculatorEngine (Calculator parent)
	{
		this.parent = parent;
	}
	public void actionPerformed (ActionEvent e) 
	{
		JButton clickedButton = (JButton) e.getSource();
	   	String displayFieldText = parent.getDisplayField();
	   	String clickedButtonLabel = clickedButton.getText();
	   	//check if the last operation was wrong
	   	if (displayFieldText.equals (Button.INVALID_OPERATION.getValue()))
	   	{
	   		parent.setDisplayField (Button.NOTHING.getValue());
	   	} //if the clicked button is "="
	   	else if (clickedButtonLabel.equals (Button.EQUAL.getValue()))
	   	{
		   		parent.setDisplayField (GeneralCount.generalCount (displayFieldText));
	  	}
	   		//if the clicked button is backspace
	   	  else if (clickedButtonLabel.equals (Button.BACKSPACE.getValue()))
	   	  {
	   		  if (displayFieldText.length() > 0)
	   		  {
	   			  parent.setDisplayField (displayFieldText.substring (0 , displayFieldText.length() - 1));
	   		  }
	   	  } //then set on display the clicked button
	   	  else
	   	  {
	   		  parent.setDisplayField( displayFieldText + clickedButtonLabel);
		  }
	}
}
 
