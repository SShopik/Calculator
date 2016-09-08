package enums;

public enum Button 
{
	ONE("1"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	ZERO("0"),
	DOT("."),
	PLUS("+"),
	MINUS("-"),
	MULTIPLY("*"),
	DIVIDE("/"),
	EQUAL("="),
	BRACKET_OPEN("("),
	BRACKET_CLOSE(")"),
	BACKSPACE("<-"),
	POWER("^"),
	INVALID_OPERATION("Invalid operation"),
	NOTHING(""),
	SPACE(" ");
	private String value;
	Button(String value) 
	{
		this.value = value;
	}
	public String getValue() 
	{
		return value;
	}
}
