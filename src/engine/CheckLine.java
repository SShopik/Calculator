package engine;
import enums.Button;
import exception.OperationException;

public class CheckLine {
	
	//method for checking mistakes 
	//if the are mistakes in input string then this method throw exception
		public static String inspection (String displayText) throws OperationException
		{
			//if after the bracket is "-" or "+", then add "0" or delete the operation
			if (displayText.indexOf (Button.BRACKET_OPEN.getValue()) != -1)
			{
				int lastBracket=0;
				while (displayText.indexOf (Button.BRACKET_OPEN.getValue() , lastBracket) != -1)
				{
					int bracket = displayText.indexOf (Button.BRACKET_OPEN.getValue() , lastBracket);
					if (displayText.charAt(bracket+1) == '-')
					{
						String line = displayText;
						displayText = line.substring (0 , bracket+1);
						displayText += Button.ZERO.getValue();
						displayText += line.substring (bracket + 1);
					} else if (displayText.charAt (bracket + 1) == '+')
						{
							String line = displayText;
							displayText = line.substring (0 , bracket + 1);
							displayText += line.substring (bracket + 2);
						}
					lastBracket = bracket + 1;
				}
			}
			
			String checkText = displayText;
			//check if the first char is *,/,^
			checkText = checkText.replace (Button.BRACKET_OPEN.getValue() , Button.NOTHING.getValue());
			checkText = checkText.replace (Button.BRACKET_CLOSE.getValue() , Button.NOTHING.getValue());
			if (checkText.startsWith (Button.MULTIPLY.getValue()) || checkText.startsWith (Button.DIVIDE.getValue()) || checkText.startsWith (Button.POWER.getValue()))
			{
				throw new OperationException (Button.INVALID_OPERATION.getValue());
			}
			//check if the last char is *,/,^
			if (checkText.endsWith (Button.MULTIPLY.getValue()) || checkText.endsWith (Button.DIVIDE.getValue()) || checkText.endsWith (Button.POWER.getValue()))
			{
				throw new OperationException (Button.INVALID_OPERATION.getValue());
			}
			//if the displayText starts with "-", then add "0" to the begin
			if (displayText.startsWith (Button.MINUS.getValue())) 
			{
				displayText = Button.ZERO.getValue() + displayText;
			}
			//if the displayText starts with "+", then delete this operation
			if (displayText.startsWith (Button.PLUS.getValue())) 
			{
				String line = displayText;
				displayText = line.substring(1);
			}
			return displayText;
		}
}
