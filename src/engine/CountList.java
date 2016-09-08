package engine;
import java.math.BigDecimal;
import java.util.ArrayList;

import enums.Button;

public class CountList 
{
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULTIPLY = "*";
	private static final String DIVIDE = "/";
	private static final String POWER = "^";
	private static final int DIVISION_SCALE = 4;
	static int index;
	static String displayText;
	//counting
	public static String count (ArrayList <String> finalList , ArrayList <String>  operations)
	{
		while (finalList.size() != 1)
		{
			index = finalList.indexOf (operations.get(0));
			BigDecimal firstOperation = new BigDecimal (finalList.get (index-2));
			BigDecimal secondOperation = new BigDecimal (finalList.get (index-1));
			switch (operations.get(0))
			{
				case PLUS: 
					finalList.add (index-2 , String.valueOf (firstOperation.add (secondOperation)));
					break;
				case MINUS: 
					finalList.add (index-2 , String.valueOf (firstOperation.subtract (secondOperation)));
					break;
				case MULTIPLY: 
					finalList.add (index-2 , String.valueOf (firstOperation.multiply (secondOperation)));
					break;
				case DIVIDE: 
					finalList.add (index-2 , String.valueOf (firstOperation.divide (secondOperation , DIVISION_SCALE , BigDecimal.ROUND_HALF_UP)));
					break;	
				case POWER: 
					//call method power to count up exponent
					finalList.add(index-2 , String.valueOf (Power.power (firstOperation, secondOperation)));
					break;	
				}	
			finalList.remove (index-1);
			finalList.remove(index-1);
			finalList.remove(index-1);
			operations.remove(0);
		}
		displayText = finalList.get(0);
		deleteZero();
		return displayText;
	}
	//if last char is zero and it goes after dot, then delete zero 
	public static void deleteZero()
	{
		if (!displayText.isEmpty() && displayText.indexOf (Button.DOT.getValue()) > 0)
		{
			int lastChar = displayText.length() - 1;
			while (true)
			{
				if (String.valueOf (displayText.charAt (lastChar)).equals (Button.ZERO.getValue()))
				{
					displayText = displayText.substring (0 , lastChar);
				} 
				else if (String.valueOf (displayText.charAt(lastChar)).equals(Button.DOT.getValue()))
				{
					displayText = displayText.substring (0 , lastChar);
					break;
				}
				else
				{
					break;
				}
				lastChar--;
			}
		}
	}
}
