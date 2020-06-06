import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

//A single sudoku square

public class Square extends JPanel {
	
	private Border squareBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private JTextField textField = new JTextField();
	private JLabel solidLabel = new JLabel();
	private Font SOLID_FONT = new Font("Serif", Font.PLAIN, 30); //display font
	private Font INPUT_FONT = new Font("Serif", Font.PLAIN, 30); //display font
	private String RANGE_REGEX = "[0-9]"; //range that can be entered
	private int WIDTH = 40; 
	private int HEIGHT = 40;
	private String entryText = ""; //if you enter text
	private boolean isSolid; //if text can be entered or not
	private int value; //Value of the square
	private boolean isCorrect = false;
	private int position;
	private boolean hasValue = false;
	
	private List<Integer> possibleSolutions = new ArrayList<>();
	
	
	public Square(int value, boolean isSolid) {
		this.value = value;
		this.isSolid = isSolid;
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBorder(squareBorder);
		this.setBackground(Color.WHITE);
		
		//Solid
		solidLabel.setText("" + value);
		solidLabel.setHorizontalAlignment(JLabel.CENTER);
		solidLabel.setVerticalAlignment(JLabel.CENTER);
		solidLabel.setSize(WIDTH, HEIGHT);
		solidLabel.setFont(SOLID_FONT);
		solidLabel.setForeground(Color.BLACK);
		solidLabel.setVisible(false);
		//set text color to black
		//Dynamic
		textField.setSize(WIDTH, HEIGHT);
		textField.setText("    ");
		textField.setFont(INPUT_FONT);
		textField.setForeground(Color.BLUE);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setVisible(false);
		//textField.setHorizonalAlignment(JTextField.CENTER); Figure out how to align
		//set text color to blue
		
		if (isSolid) {
			this.add(solidLabel);
			solidLabel.setVisible(true);
			
		} else {
			
			this.add(textField);
			textField.setVisible(true);
		}
	}
	
	//set text
	public void setLabelText() {
		//Solid
		solidLabel.setText("" + value);
		solidLabel.setHorizontalAlignment(JLabel.CENTER);
		solidLabel.setVerticalAlignment(JLabel.CENTER);
		solidLabel.setSize(WIDTH, HEIGHT);
		solidLabel.setFont(SOLID_FONT);
		solidLabel.setForeground(Color.BLACK);
		solidLabel.setVisible(false);
	}
	
	//Set square's value to visible
	public void setVisible() {
		isSolid = true;
		solidLabel.setVisible(true);
		solidLabel.setText(value + "");
		textField.setVisible(false);
		setLabelText();
		refresh();
		this.updateUI();
	}
	
	//Set square's value to invisible
	public void setInvisible() {
		isSolid = false;
		solidLabel.setVisible(false);
		solidLabel.setText("" + value);
		textField.setVisible(true);
		refresh();
		this.updateUI();
	}

	//refresh the square
	public void refresh() {
		solidLabel.setText("" + value);
		if (isSolid) {
			solidLabel.setVisible(true);
			textField.setVisible(false);
		} else {
			solidLabel.setVisible(false);
			textField.setVisible(true);
		}
		this.updateUI();
	}
	
	/*//Determine which square group
	public int getSquareGroup () {
		int pos = position;
		int sqGroup = 0;
	}*/
	
	//Determine if number has been filled or not
	public boolean getHasValue () {
		return hasValue;
	}
	
	public void setHasValue(boolean hasValue) {
		this.hasValue = hasValue;
	}
	
	public void setPossibleSolutions(List<Integer> solutions) {
		this.possibleSolutions = solutions;
	}
	
	public List<Integer> getPossibleSolutions() {
		return possibleSolutions;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getPosition() {
		return position;
	}

	public boolean isSolid() {
		return isSolid;
	}


	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}

	
	
	
	
}
