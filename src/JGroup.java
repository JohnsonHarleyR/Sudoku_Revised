import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class JGroup extends JPanel {
	
	GridLayout layout = new GridLayout(3,3);
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
	//JBoard within a jBoard
	
	//set group panels within this
	
	//do a board within a board
	//if
	
	public JGroup() {
		this.setBackground(Color.WHITE);
		this.setBorder(border);
		this.setLayout(layout);
	}
}
