package engine;
import java.math.BigDecimal;

import enums.Button;

public class Power 
{
	//recursive counting of exponent
	public static BigDecimal power (BigDecimal number , BigDecimal exponent)
	{
		if (String.valueOf (exponent).equals (Button.ZERO.getValue()))
		{
			return BigDecimal.ONE;
		}
		if (String.valueOf (exponent).equals(Button.ONE.getValue()))
		{
			return number;
		} else
		{
			return number.multiply (power (number , exponent.subtract(BigDecimal.ONE)));
		}
	}
}
