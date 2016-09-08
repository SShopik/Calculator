package engine;
import java.util.ArrayList;

import enums.Button;
import exception.OperationException;

public class GeneralCount 
{
	static ArrayList <String> finalList = new ArrayList <> ();
	String rezult = Button.SPACE.getValue();
	public static String generalCount (String displayText)
	{
	   	ParseString.finalList.clear();
   		ParseString.operations.clear();
 		//checking for mistakes
   		try
   		{
   			displayText = CheckLine.inspection (displayText);
   			finalList = ParseString.parse (displayText);
   		} catch (OperationException e)
   		{
   			return e.getMessage();
   		}
   		displayText = CountList.count (finalList, ParseString.operations);
   		return displayText ;
	}
}
