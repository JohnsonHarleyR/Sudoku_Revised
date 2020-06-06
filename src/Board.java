import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Display groups of 9 with borders around it

public class Board extends JBoard {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Square> squaresLs = new ArrayList<>();
	private Square[][] squares = new Square[9][9];  //setting up to make easier
	HashMap <Integer, Square> squareHM = new HashMap<>(); //****
	HashMap <Integer, List<Square>> groupsHM = new HashMap<>();
	HashMap <Integer, List<Square>> columnsHM = new HashMap<>();
	HashMap <Integer, List<Square>> rowsHM = new HashMap<>();
	private int[] coordinates = {0, 50, 100, 150, 200, 250, 300, 350, 400};
	
	public Board() {
		//Add squares to panel
		int counter = 0;
		int g = 0;
		int n = 0;
		for (int a = 0; a < 9; a++) {
			if (a < 3) {
				g = 0;
			} else if (a < 6) {
				g = 1;
			} else {
				g = 2;
			}
			for (int b = 0; b < 9; b++) {
				if (b < 3) {
					n = 0;
				} else if (b < 6) {
					n = 1;
				} else {
					n = 2;
				}
				
				//randomize for now
				int value = 0;
				boolean solid = true;
				String sPosition = (a + 1) + "" + (b + 1);
				int position = Integer.parseInt(sPosition);
				squaresLs.add(new Square(value, solid));
				squaresLs.get(counter).setPosition(position);
				//squaresLs.get(counter).setBounds(coordinates[a], coordinates[b], 50, 50);
				squares[a][b] = squaresLs.get(counter);
				super.getGroup(g, n).add(squares[a][b]);
				counter++;
			}
		}
		
		//add groups to board
		for (int e = 0; e < 3; e++) {
			for (int f = 0; f < 3; f++) {
				super.add(super.getGroup(e, f));
			}
		}
		
		//Set up squareHM
		for (int c = 1; c <= 9; c++) {
			String sNum = "";
			int iNum = 0;
			for (int d = 1; d <= 9; d++) {
				sNum = c + "" + d;
				iNum = Integer.parseInt(sNum);
				squareHM.put(iNum, squares[c - 1][d - 1]);
			}
		}
		
		//Set up groupsHM
		List<Square> group11 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				group11.add(squares[a][b]);
				groupsHM.put(11, group11);
			}
		}
		List<Square> group12 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 3; b < 6; b++) {
				group12.add(squares[a][b]);
				groupsHM.put(12, group12);
			}
		}
		List<Square> group13 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 6; b < 9; b++) {
				group13.add(squares[a][b]);
				groupsHM.put(13, group13);
			}
		}
		List<Square> group21 = new ArrayList<>();
		for (int a = 3; a < 6; a++) {
			for (int b = 0; b < 3; b++) {
				group21.add(squares[a][b]);
				groupsHM.put(21, group21);
			}
		}
		List<Square> group22 = new ArrayList<>();
		for (int a = 3; a <  6; a++) {
			for (int b = 3; b < 6; b++) {
				group22.add(squares[a][b]);
				groupsHM.put(22, group22);
			}
		}
		List<Square> group23 = new ArrayList<>();
		for (int a = 3; a < 6; a++) {
			for (int b = 6; b < 9; b++) {
				group23.add(squares[a][b]);
				groupsHM.put(23, group23);
			}
		}
		List<Square> group31 = new ArrayList<>();
		for (int a = 6; a < 9; a++) {
			for (int b = 0; b < 3; b++) {
				group31.add(squares[a][b]);
				groupsHM.put(31, group31);
			}
		}
		List<Square> group32 = new ArrayList<>();
		for (int a = 6; a <  9; a++) {
			for (int b = 3; b < 6; b++) {
				group32.add(squares[a][b]);
				groupsHM.put(32, group32);
			}
		}
		List<Square> group33 = new ArrayList<>();
		for (int a = 6; a < 9; a++) {
			for (int b = 6; b < 9; b++) {
				group33.add(squares[a][b]);
				groupsHM.put(33, group33);
			}
		}
		
		//Set up columnsHM
		List<Square> column1 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column1.add(squares[a][0]);
		}
		columnsHM.put(1, column1);
		List<Square> column2 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column2.add(squares[a][1]);
		}
		columnsHM.put(2, column2);
		List<Square> column3 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column3.add(squares[a][2]);
		}
		columnsHM.put(3, column3);
		List<Square> column4 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column4.add(squares[a][3]);
		}
		columnsHM.put(4, column4);
		List<Square> column5 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column5.add(squares[a][4]);
		}
		columnsHM.put(5, column5);
		List<Square> column6 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column6.add(squares[a][5]);
		}
		columnsHM.put(6, column6);
		List<Square> column7 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column7.add(squares[a][6]);
		}
		columnsHM.put(7, column7);
		List<Square> column8 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column8.add(squares[a][7]);
		}
		columnsHM.put(8, column8);
		List<Square> column9 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			column9.add(squares[a][8]);
		}
		columnsHM.put(9, column9);
		
		//Set up rowsHM
		List<Square> row1 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row1.add(squares[0][a]);
			
		}
		rowsHM.put(1, row1);
		List<Square> row2 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row2.add(squares[1][a]);
			
		}
		rowsHM.put(2, row2);
		List<Square> row3 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row3.add(squares[2][a]);
			
		}
		rowsHM.put(3, row3);
		List<Square> row4 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row4.add(squares[3][a]);
			
		}
		rowsHM.put(4, row4);
		List<Square> row5 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row5.add(squares[4][a]);
			
		}
		rowsHM.put(5, row5);
		List<Square> row6 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row6.add(squares[5][a]);
			
		}
		rowsHM.put(6, row6);
		List<Square> row7 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row7.add(squares[6][a]);
			
		}
		rowsHM.put(7, row7);
		List<Square> row8 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row8.add(squares[7][a]);
			
		}
		rowsHM.put(8, row8);
		List<Square> row9 = new ArrayList<>();
		for (int a = 0; a < 9; a ++) {
			row9.add(squares[8][a]);
			
		}
		rowsHM.put(9, row9);
		
	}
	
	//Print board
	public void printBoard() {
		System.out.println(squares[0][0].getValue() + " " + squares[0][1].getValue()+ " " + squares[0][2].getValue() + "|" +
				squares[0][3].getValue() + " " + squares[0][4].getValue()+ " " + squares[0][5].getValue() + "|" +
				squares[0][6].getValue() + " " + squares[0][7].getValue()+ " " + squares[0][8].getValue());
		System.out.println(squares[1][0].getValue() + " " + squares[1][1].getValue()+ " " + squares[1][2].getValue() + "|" +
				squares[1][3].getValue() + " " + squares[1][4].getValue() + " " + squares[1][5].getValue() + "|" +
				squares[1][6].getValue() + " " + squares[1][7].getValue() + " " + squares[1][8].getValue());
		System.out.println(squares[2][0].getValue() + " " + squares[2][1].getValue() + " " + squares[2][2].getValue() + "|" +
				squares[2][3].getValue() + " " + squares[2][4].getValue() + " " + squares[2][5].getValue() + "|" +
				squares[2][6].getValue() + " " + squares[2][7].getValue() + " " + squares[2][8].getValue());
		System.out.println("---------------");
		System.out.println(squares[3][0].getValue() + " " + squares[3][1].getValue() + " " + squares[3][2].getValue() + "|" +
				squares[3][3].getValue() + " " + squares[3][4].getValue() + " " + squares[3][5].getValue() + "|" +
				squares[3][6].getValue() + " " + squares[3][7].getValue() + " " + squares[3][8].getValue());
		System.out.println(squares[4][0].getValue() + " " + squares[4][1].getValue() + " " + squares[4][2].getValue() + "|" +
				squares[4][3].getValue() + " " + squares[4][4].getValue() + " " + squares[4][5].getValue() + "|" +
				squares[4][6].getValue() + " " + squares[4][7].getValue() + " " + squares[4][8].getValue());
		System.out.println(squares[5][0].getValue() + " " + squares[5][1].getValue() + " " + squares[5][2].getValue() + "|" +
				squares[5][3].getValue() + " " + squares[5][4].getValue() + " " + squares[5][5].getValue() + "|" +
				squares[5][6].getValue() + " " + squares[5][7].getValue() + " " + squares[5][8].getValue());
		System.out.println("---------------");
		System.out.println(squares[6][0].getValue() + " " + squares[6][1].getValue() + " " + squares[6][2].getValue() + "|" +
				squares[6][3].getValue() + " " + squares[6][4].getValue() + " " + squares[6][5].getValue() + "|" +
				squares[6][6].getValue() + " " + squares[6][7].getValue() + " " + squares[6][8].getValue());
		System.out.println(squares[7][0].getValue() + " " + squares[7][1].getValue() + " " + squares[7][2].getValue() + "|" +
				squares[7][3].getValue() + " " + squares[7][4].getValue() + " " + squares[7][5].getValue() + "|" +
				squares[7][6].getValue() + " " + squares[7][7].getValue() + " " + squares[7][8].getValue());
		System.out.println(squares[8][0].getValue() + " " + squares[8][1].getValue() + " " + squares[8][2].getValue() + "|" +
				squares[8][3].getValue() + " " + squares[8][4].getValue() + " " + squares[8][5].getValue() + "|" +
				squares[8][6].getValue() + " " + squares[8][7].getValue() + " " + squares[8][8].getValue());
	}
	
	//Refresh board
	public void refreshBoard() {
		for (Square square: squaresLs) {
			square.refresh();
		}
		this.updateUI();
	}
	
	//Make a set invisible
	public void makeSetVisible(List<Square> squares) {
		for (Square square: squares) {
			square.setVisible();
		}
		this.updateUI();
	}
	//Make a set visible
	public void makeSetInvisible(List<Square> squares) {
		for (Square square: squares) {
			square.setInvisible();
		}
		this.updateUI();
	}
	
	//Make whole board visible
	public void makeBoardVisible() {
		for (Square square: squaresLs) {
			square.setVisible();
		}
		this.updateUI();
	}
	
	//Make whole board invisible
	public void makeBoardInvisible() {
		for (Square square: squaresLs) {
			square.setInvisible();
		}
		this.updateUI();
	}
		
	
	//Update all by list
	public void updateAll(List<Square> squares) {
		updateSquareHM(squares);
		updateSquaresArr();
		updateSquareLs();
		updateGroupHM();
		updateColumnsHM();
		updateRowsHM();
	}
	
	//Update all by square
	public void updateAll(Square square) {
		updateSquareHM(square);
		updateSquaresArr();
		updateSquareLs();
		updateGroupHM();
		updateColumnsHM();
		updateRowsHM();
	}
	
	//Update all by map
	public void updateAll(HashMap<Integer, Square> squares) {
		updateSquareHM(squares);
		updateSquaresArr();
		updateSquareLs();
		updateGroupHM();
		updateColumnsHM();
		updateRowsHM();
	}
	
	
	//Update SquaresHM by a list
	public void updateSquareHM(List<Square> squares) {
		for (Square square: squares) {
			int position = square.getPosition();
			squareHM.replace(position, square);
		}
	}
	
	//Update SquaresHM by a map
		public void updateSquareHM(HashMap<Integer, Square> squares) {
			for (int position: squares.keySet()) {
				Square square = squares.get(position);
				squareHM.replace(position, square);
			}
		}
	
	//Update SquaresHM by square
		public void updateSquareHM(Square square) {
			int position = square.getPosition();
			squareHM.replace(position, square);
		}
	
	
	//Update squares[][]
	public void updateSquaresArr() {
		for (int position: squareHM.keySet()) {
			String temp = "" + position;
			int a = Integer.parseInt(temp.substring(0, 1)) - 1;
			int b = Integer.parseInt(temp.substring(1)) - 1;
			squares[a][b] = squareHM.get(position);
		}
	}
	
	//Update SquaresLS - By squaresHM
		public void updateSquareLs() {
			squaresLs.clear();
			for (int a = 0; a < 9; a++) {
				for (int b = 0; b < 9; b++) {
					squaresLs.add(squares[a][b]);
				}
			}
		}
	
	//Update groupsHM
	public void updateGroupHM() {
		//Set up groupsHM
		List<Square> group11 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				group11.add(squares[a][b]);
				groupsHM.replace(11, group11);
			}
		}
		List<Square> group12 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 3; b < 6; b++) {
				group12.add(squares[a][b]);
				groupsHM.replace(12, group12);
			}
		}
		List<Square> group13 = new ArrayList<>();
		for (int a = 0; a < 3; a++) {
			for (int b = 6; b < 9; b++) {
				group13.add(squares[a][b]);
				groupsHM.replace(13, group13);
			}
		}
		List<Square> group21 = new ArrayList<>();
		for (int a = 3; a < 6; a++) {
			for (int b = 0; b < 3; b++) {
				group21.add(squares[a][b]);
				groupsHM.replace(21, group21);
			}
		}
		List<Square> group22 = new ArrayList<>();
		for (int a = 3; a <  6; a++) {
			for (int b = 3; b < 6; b++) {
				group22.add(squares[a][b]);
				groupsHM.replace(22, group22);
			}
		}
		List<Square> group23 = new ArrayList<>();
		for (int a = 3; a < 6; a++) {
			for (int b = 6; b < 9; b++) {
				group23.add(squares[a][b]);
				groupsHM.replace(23, group23);
			}
		}
		List<Square> group31 = new ArrayList<>();
		for (int a = 6; a < 9; a++) {
			for (int b = 0; b < 3; b++) {
				group31.add(squares[a][b]);
				groupsHM.replace(31, group31);
			}
		}
		List<Square> group32 = new ArrayList<>();
		for (int a = 6; a <  9; a++) {
			for (int b = 3; b < 6; b++) {
				group32.add(squares[a][b]);
				groupsHM.replace(32, group32);
			}
		}
		List<Square> group33 = new ArrayList<>();
		for (int a = 6; a < 9; a++) {
			for (int b = 6; b < 9; b++) {
				group33.add(squares[a][b]);
				groupsHM.replace(33, group33);
			}
		}
	}
	
	//Update columnsHM
	public void updateColumnsHM() {
		//Set up columnsHM
				List<Square> column1 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column1.add(squares[a][0]);
				}
				columnsHM.replace(1, column1);
				List<Square> column2 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column2.add(squares[a][1]);
				}
				columnsHM.replace(2, column2);
				List<Square> column3 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column3.add(squares[a][2]);
				}
				columnsHM.replace(3, column3);
				List<Square> column4 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column4.add(squares[a][3]);
				}
				columnsHM.replace(4, column4);
				List<Square> column5 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column5.add(squares[a][4]);
				}
				columnsHM.replace(5, column5);
				List<Square> column6 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column6.add(squares[a][5]);
				}
				columnsHM.replace(6, column6);
				List<Square> column7 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column7.add(squares[a][6]);
				}
				columnsHM.replace(7, column7);
				List<Square> column8 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column8.add(squares[a][7]);
				}
				columnsHM.replace(8, column8);
				List<Square> column9 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					column9.add(squares[a][8]);
				}
				columnsHM.replace(9, column9);
	}
	
	//Update rowsHM	
	public void updateRowsHM () {
		//Set up rowsHM
				List<Square> row1 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row1.add(squares[0][a]);
					
				}
				rowsHM.replace(1, row1);
				List<Square> row2 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row2.add(squares[1][a]);
					
				}
				rowsHM.replace(2, row2);
				List<Square> row3 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row3.add(squares[2][a]);
					
				}
				rowsHM.replace(3, row3);
				List<Square> row4 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row4.add(squares[3][a]);
					
				}
				rowsHM.replace(4, row4);
				List<Square> row5 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row5.add(squares[4][a]);
					
				}
				rowsHM.replace(5, row5);
				List<Square> row6 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row6.add(squares[5][a]);
					
				}
				rowsHM.replace(6, row6);
				List<Square> row7 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row7.add(squares[6][a]);
					
				}
				rowsHM.replace(7, row7);
				List<Square> row8 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row8.add(squares[7][a]);
					
				}
				rowsHM.replace(8, row8);
				List<Square> row9 = new ArrayList<>();
				for (int a = 0; a < 9; a ++) {
					row9.add(squares[8][a]);
					
				}
				rowsHM.replace(9, row9);
	}
	
	
		
	//Getters and setters
	public List<Square> getSquaresLs() {
		return squaresLs;
	}

	public void setSquaresLs(List<Square> squaresLs) {
		this.squaresLs = squaresLs;
	}
	
	public Square getSquare(int a, int b) {
		return squares[a][b];
	}

	public Square[][] getSquares() {
		return squares;
	}

	public void setSquares(Square[][] squares) {
		this.squares = squares;
	}

	public HashMap<Integer, Square> getSquareHM() {
		return squareHM;
	}

	public void setSquareHM(HashMap<Integer, Square> squareHM) {
		this.squareHM = squareHM;
	}

	public HashMap<Integer, List<Square>> getGroupsHM() {
		return groupsHM;
	}

	public void setGroupsHM(HashMap<Integer, List<Square>> groupsHM) {
		this.groupsHM = groupsHM;
	}

	public HashMap<Integer, List<Square>> getColumnsHM() {
		return columnsHM;
	}

	public void setColumnsHM(HashMap<Integer, List<Square>> columnsHM) {
		this.columnsHM = columnsHM;
	}

	public HashMap<Integer, List<Square>> getRowsHM() {
		return rowsHM;
	}

	public void setRowsHM(HashMap<Integer, List<Square>> rowsHM) {
		this.rowsHM = rowsHM;
	}
}
