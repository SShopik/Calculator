package ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import engine.CalculatorEngine;
import enums.Button;;


public class Calculator 
{
	public static final String DISPLAY_FIELD_COMPONENT_NAME = "North";
	public static final String BUTTONS_COMPONENT_NAME = "Center";
	public static final String FRAME_NAME = "Calculator";
	JPanel window;
	private JTextField displayField;
	JPanel buttons;
	//set value on display
	public void setDisplayField(String value)
	{
		displayField.setText (value);
	}//get value from display
	public String getDisplayField()
	{
		return displayField.getText();
	}
	public Calculator()
	{
		window = new JPanel();
		BorderLayout borderLayout = new BorderLayout();
		window.setLayout (borderLayout);
		displayField = new JTextField(20);
		displayField.setFont ( new Font("SansSerif", Font.BOLD, 16));
		window.add (DISPLAY_FIELD_COMPONENT_NAME , displayField);
		buttons = new JPanel();
		GridLayout gridLayout = new GridLayout(5,4);
		buttons.setLayout(gridLayout);
		//sending instance of Calculator to CalculatorEngine 
		CalculatorEngine listener = new CalculatorEngine(this);
		//adding buttons	
		addButtons(listener);
		window.add (BUTTONS_COMPONENT_NAME , buttons);
		JFrame frame = new JFrame(FRAME_NAME);
		frame.setContentPane(window);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize ( new Dimension(250, 170));
		frame.setMinimumSize ( new Dimension(250, 170));
		frame.pack();
		frame.setVisible(true);
	}
	//method for making and adding button to the panel
	private void addButton (String label , CalculatorEngine listener)
	{
		JButton button = new JButton(label);
		button.addActionListener(listener);
		buttons.add(button);
	}
	//method for adding buttons 
	private void addButtons (CalculatorEngine listener)
	{
		addButton(Button.BACKSPACE.getValue() , listener);
		addButton(Button.BRACKET_OPEN.getValue() , listener);
		addButton(Button.BRACKET_CLOSE.getValue() , listener);
		addButton(Button.POWER.getValue() , listener);
		addButton(Button.SEVEN.getValue() , listener);
		addButton(Button.EIGHT.getValue() , listener);
		addButton(Button.NINE.getValue() , listener);
		addButton(Button.DIVIDE.getValue() , listener);
		addButton(Button.FOUR.getValue() , listener);
		addButton(Button.FIVE.getValue() , listener);
		addButton(Button.SIX.getValue() , listener);
		addButton(Button.MULTIPLY.getValue() , listener);
		addButton(Button.ONE.getValue() , listener);
		addButton(Button.TWO.getValue() , listener);
		addButton(Button.THREE.getValue() , listener);
		addButton(Button.PLUS.getValue() , listener);
		addButton(Button.ZERO.getValue() , listener);
		addButton(Button.DOT.getValue() , listener);
		addButton(Button.EQUAL.getValue() , listener);
		addButton(Button.MINUS.getValue() , listener);
	} 
}
