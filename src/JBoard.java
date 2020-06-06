import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JBoard extends JPanel {
	
	private final int WIDTH = 400;
	private final int LENGTH = 400;
	
	GridLayout layout = new GridLayout(3,3);
	private JGroup[][] jGroups = new JGroup[3][3]; 
	//JBoard within a jBoard
	
	//set group panels within this
	
	//do a board within a board
	//if
	
	public JBoard() {
		this.setPreferredSize(new Dimension(WIDTH, LENGTH));
		this.setBackground(Color.WHITE);
		//this.setBorder(groupBorder);
		this.setLayout(layout);
		
		for (int e = 0; e < 3; e++) {
			for (int f = 0; f < 3; f++) {
				jGroups[e][f] = new JGroup();
			}
		}
	}

	public JGroup[][] getjGroups() {
		return jGroups;
	}

	public void setjGroups(JGroup[][] jGroups) {
		this.jGroups = jGroups;
	}
	
	public JGroup getGroup(int g, int n) {
		return jGroups[g][n];
	}
	
	
}
