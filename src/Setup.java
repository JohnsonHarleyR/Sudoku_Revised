import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

//This is for setting up all the board values
public class Setup {
	
	//CREATE METHOD that only displays certain numbers on the board, then test it
	//to make sure that board would be solvable
	
	
	//write solutions to file for later
	public static void saveSolution(Board board) {
		
		String dirPath = "src/";
		Path path = Paths.get(dirPath + "sudokuSolutions.txt");
		
		List<String> lines = new ArrayList<>();
		
		//Put all lines into an array
		try {
			List<String> temp = Files.readAllLines(path);
			lines = temp;
			//Since the list doesn't seem to stay after the try catch ends
			
		} catch (IOException e) {
			System.out.println("Error loading file.");
			e.printStackTrace();
		}
		
		String line = "";
		
		board.updateAll(board.getSquareHM());
		for (int a = 0; a < 9; a++) {
			for (int b = 0; b < 9; b++) {
				if(a == 0 && b == 0) {
					line += board.getSquare(a, b).getValue();
				} else {
					line += "~" + board.getSquare(a, b).getValue();
				}
				
			}
		}
		
		lines.add(line);

		try {
			Files.write(path, lines, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Error: Unable to add to file.");
			e.printStackTrace();
		}
		
	}
	
	
	//Method that keeps list of steps for the sake of backtracking
	//It will reset a value if no solutions are available for a square
	
	//GENERATE BOARD
	public static void genBoard(Board board) {
		
		
		int counter = 0; //counting steps
		int spot = 0;
		int solution = 0;
		int num = 0;
		boolean isSolved = false;
		List<Integer> spotsLeft = new ArrayList<>();
		List<Integer> positionsGenerated = new ArrayList<>(); //keep track of nums
		List<Integer> numsGenerated = new ArrayList<>();
		List<Integer> solutions = new ArrayList<>();
		
		//Reset board to 0s
		newReset(board.getSquaresLs());
		board.updateAll(board.getSquaresLs());
		board.refreshBoard();
		
		
		
		//1. Put all position nums in list spotsLeft
		for (int i = 0; i < board.getSquaresLs().size(); i++) {
			if (board.getSquaresLs().get(i).getValue() == 0) {
				spotsLeft.add(board.getSquaresLs().get(i).getPosition());
				
				System.out.print(board.getSquaresLs().get(i).getPosition() + " "); //test
			}
		}
		
		
		//Also continue while keepGoing, which depends whether
		//there is enough info to solve the grid or not

		//Go as long as there are spots left
		while (isSolved == false || spotsLeft.size() > 0) {
			
			board.updateAll(board.getSquareHM());
			board.refreshBoard();
			
			//regenerate spotsLeft
			spotsLeft.clear();
			solutions.clear();
			for (Square square: board.getSquaresLs()) {
				if (square.getValue() == 0) {
					spotsLeft.add(square.getPosition());
				}
			}
			
			
			//2. generate random spot (position number)
			if (!spotsLeft.isEmpty()) {
				num = genRandomInRange(spotsLeft.size()) - 1;
				
				
				spot = spotsLeft.get(num);
			}
			
			
			/*
			if (counter < 9) {
				spot = genPos(4, counter + 1);
			} else if (counter < 18) {
				spot = genPos(counter + 1 - 9, 8);
			}
			*/
			
			
			System.out.println("\nSpot: " + spot); //test
			
			//test if it's solvable yet
			int canSolve = isSolvable(board);
			int tempNum = 0;
			
			//If it's not yet solvable but not unsolvable, continue generating
			if (canSolve == 1) { //this is to fix a glitch I ran into
				int reiter = 0;
				do {
					
					//3. Determine possible solutions for that spot
					solutions = genSolutions(board.getSquareHM().get(spot), board);
					
					if (!solutions.isEmpty()) {
						System.out.print("Possible solutions: ");
						for (int sol: solutions) {
							System.out.print(sol + " ");  //test
						}
						System.out.println();
						
						tempNum = solutions.size() - 1;
						reiter++;
					}
					
				} while (solutions.get(0) == -1 & reiter < 5);
				
				if (reiter == 5) {//debug
					canSolve = -1; //if it's stuck in -1, just regenerate
				}
			}
			
			
			//4. If there are no solutions, backtrack (return list with a 0 if so)
			//Also do this if the grid is unsolvable
			if (canSolve == -1 && counter > 0) {
				counter--;
				int tempPos = positionsGenerated.get(positionsGenerated.size() - 1);
				spotsLeft.add(positionsGenerated.get(counter));
				positionsGenerated.remove(counter);
				numsGenerated.remove(counter);
				board.getSquareHM().get(tempPos).setValue(0);
			} else if (canSolve == -1) {
				counter = 0;
				genBoard(board);
			} else if (canSolve == 0) {
				solveBoard(board);
				isSolved = true;
			} else {
				//5. Choose random solution for square, store solution
				if (solutions.size() == 1) {
					solution = solutions.get(0);
				} else {
					solution = solutions.get(genRandomInRange(solutions.size()) - 1);
				}
				
				System.out.println("\nSolution: " + solution); //test
				
				Square sq = board.getSquareHM().get(spot); //temporary square
				//If the above doesn't work, turn it back to how it was
				//anything that says sq goes back to board.getSquareHM().get(spot)
				board.getSquareHM().get(spot).setValue(solution);
				board.updateAll(sq);
				sq.repaint();
				//6. Remove from spotsLeft, add to positionsGenerated, add to numsGenerated
				//   add 1 to counter
				positionsGenerated.add(spot);
				numsGenerated.add(solution);
				counter++;
				//7. Go to next number
				
			}
			int empties = 0;
			
			for (Square square: board.getSquaresLs()) {
				if (square.getValue() == 0) {
					empties += 1;
				}
			}
			
			if (empties == 0) {
				isSolved = true;
			}
			
			if (!spotsLeft.isEmpty()) {
				System.out.println("Empties left: " + empties + "\n"); //test
			}
			board.printBoard(); //test
			if (isSolved) {
				spotsLeft.clear();
			}
		}
		
		
	}
	
	//Method to check if it's possible to solve board
	//Return -1 if there's no solution, 1 if there's not enough info, 0 if solvable
	public static int isSolvable(Board board) {
		Board tempBoard = board;
		tempBoard.makeBoardInvisible();
		
		boolean solvableSquare = false;
		List<Integer> solutions = new ArrayList<>();
		List<Square> emptySquares = new ArrayList<>();
		
		//Loop this
		//1. if it gets through board with no solvable squares, return 1
		//2. if all squares are solved, return 0
		//3. if there comes a square with no solution, return -1
		while (true) {
			//clear it all
			solvableSquare = false;
			solutions.clear();
			emptySquares.clear();
			//populate empty square list
			for (Square square: tempBoard.getSquaresLs()) {
				if (square.getValue() == 0) {
					emptySquares.add(square);
				}
			}
			//if emptySquares is empty, the puzzle is solvable so return 0
			if (emptySquares.isEmpty()) {
				return 0;
			} else { //not solved yet, do this
				for (Square square: emptySquares) { //loop through empty squares
					solutions.clear();
					solutions = genSolutions(square, tempBoard);
					if (solutions.contains(-1)) { //If the solution comes back as -1,
						return -1;               // then the square is unsolvable and
					                              //so is the puzzle.
					} else if (solutions.size() == 1) {
						square.setValue(solutions.get(0));
						solvableSquare = true;
					} //If there's only one solution though, set that square to it
				}
				//If solvableSquare is still false at this point, there's not enough
				//info to solve the puzzle, so return 1
				if (!solvableSquare) {
					return 1;
				}
			}
		}
		
		
	}
	
	//Only use this method if you know a board is solvable
	public static void solveBoard(Board board) {
		
		boolean solved = false;
		boolean solvableSquare = false;
		List<Integer> solutions = new ArrayList<>();
		List<Square> emptySquares = new ArrayList<>();
		
		while (!solved) {
			//clear it all
			solvableSquare = false;
			solutions.clear();
			emptySquares.clear();
			//populate empty square list
			for (Square square: board.getSquaresLs()) {
				if (square.getValue() == 0) {
					emptySquares.add(square);
				}
			}
			//if emptySquares is empty, the puzzle is solvable so return 0
			if (emptySquares.isEmpty()) {
				solved = true;
			} else { //not solved yet, do this
				for (Square square: emptySquares) { //loop through empty squares
					solutions.clear();
					solutions = genSolutions(square, board);
					if (solutions.contains(-1)) { 
						System.out.println("This puzzle is impossible to solve.");
					} else if (solutions.size() == 1) {
						square.setValue(solutions.get(0));
					} //If there's only one solution though, set that square to it
				}
				//If solvableSquare is still false at this point, there's not enough
				//info to solve the puzzle, so return 1
				if (!solvableSquare) {
					System.out.println("There is likely not enough info to solve this.");
				}
			}
		}
		//update board
		board.updateAll(board.getSquaresLs());
	}
	
	
	
	//Reset square values for new game, before generating
	public static void newReset(List<Square> squares) {
		for (Square square: squares) {
			square.setValue(0);
		}
	}
	
	//Generate a set of 9 numbers
	public static List<Square> genSet(List<Square> squares) {
		while (!checkSetValues(squares)) {
			for (Square square: squares) {
				if (square.getValue() == 0) {
					square.setValue(genNumInSet(squares));
				}
			}
		} return squares;
	}
	
	//Same as genSet except it resets numbers
	public static List<Square> genNewSet(List<Square> squares) {
		while (!checkSetValues(squares)) {
			for (Square square: squares) {
				if (square.getValue() == 0) {
					square.setValue(genNumInSet(squares));
				}
			}
		} return squares;
	}
	
	//Generate random number between 1 and 9
	public static int genRandom() {
		int num = (int)(Math.random() * 9) + 1;
		return num;
	}
	
	//Generate random number in a range
		public static int genRandomInRange(int range) {
			int num = (int)(Math.random() * range) + 1;
			return num;
		}
	
	//Generate random solution from list
		public static int genSolution(List<Integer> solutions) {
			int numOfSol = solutions.size();
			int num = (int)(Math.random() * numOfSol) + 1;
			return solutions.get(num);
		}
	
	//Generate a number that's available in a set
	public static int genNumInSet(List<Square> squares) {
		boolean tryAgain = true;
		int newNum = 0;
		while (tryAgain) {
			newNum = genRandom();
			if (!isInSet(newNum, squares)) {
				tryAgain = false;
			}
		}
		return newNum;
	}
	
	//Check if a value is in a set
	public static boolean isInSet(int value, List<Square> squares) {
		if (value == 0) {
			return false;
		} else {
			for (Square square: squares) {
				if (square.getValue() == value) {
					return true;
				}
			} return false;
		}
		
	}
	
	//Check if values in a set equal 1 through 9. False if not.
	public static boolean checkSetValues(List<Square> squares) {
		boolean chk[] = new boolean[squares.size()];
		int[] regValues = new int[9];
		boolean retVal = true;
		for (int i = 0; i < 9; i++) {
			chk[i] = false;
			regValues[i] = i + 1;
		}
		
		//check off values that match
		for (int i = 0; i < squares.size(); i++) {
			int value = squares.get(i).getValue();
			for (int a = 0; a < 9; a++) {
				if (value == regValues[a]) {
					chk[a] = true;
				}
			}
		}
		
		//See if any don't match, return true or false
		for (int i = 0; i < chk.length; i++) {
			if (chk[i] == false) {
				retVal = false;
			}
		}
		return retVal;
		
	}
	
	//Find possible solutions for a square
	public static List<Integer> genSolutions(Square square, Board board) {
		int position = square.getPosition();
		int newPosition;
		String temp = "" + position;
		int rowNum = Integer.parseInt(temp.substring(0, 1));
		int colNum = Integer.parseInt(temp.substring(1));
		final int[] POS_NUMS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int sPos;
		int gRow;
		int gCol;
		//Lists
		List<Integer> solutions = new ArrayList<>();
		List<Integer> usedNums = new ArrayList<>();
		List<Integer> rowPos = new ArrayList<>();
		List<Integer> colPos = new ArrayList<>();
		List<Integer> bigSquarePos = new ArrayList<>();
		//Update board just in case
		board.updateAll(square);
		//Generate row positions to check
		for (int i = 1; i <= 9; i ++) {
			int tempNum = genPos(rowNum, i);
			rowPos.add(tempNum);
		}
		//Generate col positions
		for (int i = 1; i <= 9; i ++) {
			int tempNum = genPos(i, colNum);
			colPos.add(tempNum);
		}
		//Figure out group position
		//Row
		if (rowNum >= 1 && rowNum <= 3) {
			gRow = 1;
		} else if (rowNum >= 4 && rowNum <= 6) {
			gRow = 2;
		} else {
			gRow = 3;
		}
		//Col
		if (colNum >= 1 && colNum <= 3) {
			gCol = 1;
		} else if (colNum >= 4 && colNum <= 6) {
			gCol = 2;
		} else {
			gCol = 3;
		}
		//Which big square pos
		sPos = genPos(gRow, gCol);
		//Determine big Square pos numbers
		switch (sPos) {
		case 11:
			bigSquarePos.add(11); bigSquarePos.add(12); bigSquarePos.add(13);
			bigSquarePos.add(21); bigSquarePos.add(22); bigSquarePos.add(23);
			bigSquarePos.add(31); bigSquarePos.add(32); bigSquarePos.add(33);
			break;
		case 12:
			bigSquarePos.add(14); bigSquarePos.add(15); bigSquarePos.add(16);
			bigSquarePos.add(24); bigSquarePos.add(25); bigSquarePos.add(26);
			bigSquarePos.add(34); bigSquarePos.add(35); bigSquarePos.add(36);
			break;
		case 13:
			bigSquarePos.add(17); bigSquarePos.add(18); bigSquarePos.add(19);
			bigSquarePos.add(27); bigSquarePos.add(28); bigSquarePos.add(29);
			bigSquarePos.add(37); bigSquarePos.add(38); bigSquarePos.add(39);
			break;
		case 21:
			bigSquarePos.add(41); bigSquarePos.add(42); bigSquarePos.add(43);
			bigSquarePos.add(51); bigSquarePos.add(52); bigSquarePos.add(53);
			bigSquarePos.add(61); bigSquarePos.add(62); bigSquarePos.add(63);
			break;
		case 22:
			bigSquarePos.add(44); bigSquarePos.add(45); bigSquarePos.add(46);
			bigSquarePos.add(54); bigSquarePos.add(45); bigSquarePos.add(56);
			bigSquarePos.add(64); bigSquarePos.add(45); bigSquarePos.add(66);
			break;
		case 23:
			bigSquarePos.add(47); bigSquarePos.add(48); bigSquarePos.add(49);
			bigSquarePos.add(57); bigSquarePos.add(58); bigSquarePos.add(59);
			bigSquarePos.add(67); bigSquarePos.add(68); bigSquarePos.add(69);
			break;
		case 31:
			bigSquarePos.add(71); bigSquarePos.add(72); bigSquarePos.add(73);
			bigSquarePos.add(81); bigSquarePos.add(82); bigSquarePos.add(83);
			bigSquarePos.add(91); bigSquarePos.add(92); bigSquarePos.add(93);
			break;
		case 32:
			bigSquarePos.add(74); bigSquarePos.add(75); bigSquarePos.add(46);
			bigSquarePos.add(84); bigSquarePos.add(85); bigSquarePos.add(56);
			bigSquarePos.add(94); bigSquarePos.add(95); bigSquarePos.add(66);
			break;
		case 33:
			bigSquarePos.add(77); bigSquarePos.add(78); bigSquarePos.add(79);
			bigSquarePos.add(87); bigSquarePos.add(88); bigSquarePos.add(89);
			bigSquarePos.add(97); bigSquarePos.add(98); bigSquarePos.add(99);
			break;
		default:
		}
		//Find out used numbers in same row
		for (int pos: rowPos) {
			if (pos != position) {
				int num = board.getSquareHM().get(pos).getValue();
				if (num != 0 && !usedNums.contains(num)) {
					usedNums.add(board.getSquareHM().get(pos).getValue());
				}
			}
		}
		//Find out used numbers in same column
		for (int pos: colPos) {
			if (pos != position) {
				int num = board.getSquareHM().get(pos).getValue();
				if (num != 0 && !usedNums.contains(num)) {
					usedNums.add(board.getSquareHM().get(pos).getValue());
				}
			}
		}
		//Find out used numbers in same group
		for (int pos: bigSquarePos) {
			if (pos != position) {
				int num = board.getSquareHM().get(pos).getValue();
				if (num != 0 && !usedNums.contains(num)) {
					usedNums.add(board.getSquareHM().get(pos).getValue());
				}
			}
		}
		//Find out what solutions are left
		for (int i = 0; i < POS_NUMS.length; i ++) {
			if (!usedNums.contains(POS_NUMS[i])) {
				solutions.add(POS_NUMS[i]);
			}
		}
		//return list
		if (solutions.isEmpty()) {
			solutions.add(-1);
		}
		return solutions;
	}
	
	
	//Generate position number
	public static int genPos(int row, int col) {
		String sNum = row + "" + col;
		return Integer.parseInt(sNum);
	}

}
