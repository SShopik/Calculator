package engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import enums.Button;
import exception.OperationException;

public class ParseString 
{
	//final list with elements and operations
	static ArrayList <String> finalList = new ArrayList<>();
	//list with operations
	static ArrayList <String> operations= new ArrayList<>();
	//I put all operations and brackets into the hashmap 
	public static final Map <String , Integer> SYMBOLS; 
	static
	{
		SYMBOLS = new HashMap <String , Integer>();
		SYMBOLS.put(Button.POWER.getValue(), 1);
		SYMBOLS.put(Button.MULTIPLY.getValue(), 2);
		SYMBOLS.put(Button.DIVIDE.getValue(), 2);
		SYMBOLS.put(Button.PLUS.getValue(), 3);
		SYMBOLS.put(Button.MINUS.getValue(), 3);
		SYMBOLS.put(Button.BRACKET_OPEN.getValue(), 4);
		SYMBOLS.put(Button.BRACKET_CLOSE.getValue(), 4);
	}
	static int index;
	//method which parses string
	public static ArrayList <String> parse(String displayText) throws OperationException
	{
		//stack with operations
		Stack <String> stack = new Stack<>();
		displayText = displayText.replace (Button.SPACE.getValue(), Button.NOTHING.getValue());
		index=0;
		//flag for checking if there are more operations
		boolean nextOperation = true;
		int nextSymbolIndex;
		String nextSymbol;
		//check if the displayText is empty or with invalid operation
		if (displayText.isEmpty() || displayText.equals (Button.INVALID_OPERATION.getValue()))
		{
			throw new OperationException (displayText);
		}
		//variable for checking, if all the brackets are closed
		int numberOfBracket = 0;
		//parsing displayText
		do
		{
			nextSymbolIndex = displayText.length();
			nextSymbol = Button.NOTHING.getValue();
			//Searching the nearest operation
			for (String s:SYMBOLS.keySet())
			{
				int i = displayText.indexOf(s,index);
				if (i >= 0 && i < nextSymbolIndex)
				{
					nextSymbol = s;
					nextSymbolIndex = i;
				}
			}
			//if true, then no more operations
			if (nextSymbolIndex == displayText.length())
			{
				nextOperation = false;
				continue;
			}
			if (index != nextSymbolIndex)
			{
				//whether the number is entered
				try
				{
					Double.valueOf (displayText.substring (index,nextSymbolIndex));
				} catch(Exception e)
				{
					throw new OperationException (Button.INVALID_OPERATION.getValue());
				}
				//adding the number to the final list
				finalList.add (displayText.substring (index, nextSymbolIndex));
			}
			//check if after open bracket goes "*","/" or "^" 
			if (!stack.empty() && stack.peek().equals (Button.BRACKET_OPEN.getValue()) && (nextSymbol.equals (Button.MULTIPLY.getValue()) || nextSymbol.equals (Button.DIVIDE.getValue()) || nextSymbol.equals (Button.POWER.getValue())))
			{
				throw new OperationException (Button.INVALID_OPERATION.getValue());
			}
			//if the next operation is "(", then push the bracket to the stack
			if (nextSymbol.equals (Button.BRACKET_OPEN.getValue()))
			{
				stack.push (nextSymbol);
				numberOfBracket++;
			//if the next operation is ")", then move all the operations from the stack to the arraylist	
			} else if (nextSymbol.equals (Button.BRACKET_CLOSE.getValue()))
			{
				numberOfBracket--;
				try 
				{
					while (!stack.peek().equals (Button.BRACKET_OPEN.getValue()))
					{
						if (stack.empty())
						{
							throw new OperationException (Button.INVALID_OPERATION.getValue());
						}
						operations.add (stack.peek());
						finalList.add (stack.pop());
					}
					stack.pop();
				}
				catch (Exception e)
				{
					throw new OperationException (Button.INVALID_OPERATION.getValue());
				} 
			}
			//checking the operation's priority
			else
			{
				while (!stack.empty() && !stack.peek().equals (Button.BRACKET_OPEN.getValue()) && SYMBOLS.get (nextSymbol) >= SYMBOLS.get (stack.peek()))
				{
					if (stack.peek().equals (Button.POWER.getValue()) && nextSymbol.equals (Button.POWER.getValue()))
					{
						break;
					}
					operations.add (stack.peek());
					finalList.add (stack.pop());
				}
				stack.push (nextSymbol);
			}
			index = nextSymbolIndex + 1;
		} while (nextOperation);
		//save the last number
		if (index != displayText.length())
		{
			finalList.add (displayText.substring (index));
		}	
		//save the remaining operations
		while (!stack.empty())
		{
			operations.add (stack.peek());
			finalList.add (stack.pop());
		}
		//check if all brackets are closed
		if (numberOfBracket != 0)
		{
			throw new OperationException (Button.INVALID_OPERATION.getValue());
		}
		//checking if there are more numbers then operations
		if (finalList.size() - 2*operations.size() < 1)
		{
			throw new OperationException (Button.INVALID_OPERATION.getValue());
		}
		return finalList;
	}
}
