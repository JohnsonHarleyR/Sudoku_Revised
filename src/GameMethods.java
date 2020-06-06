import java.util.List;

public class GameMethods {
	
	//Get random integer in specified range
	public static int getRandom(int range) {
		int number = (int)(Math.random() * range) + 1;
		return number;
	}
	
	//Returns true or false at random
	public static boolean randomTrueFalse() {
		int random = (int)(Math.random() * 2) + 1;
		if (random == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	//testing method - print row, column, group, etc.
	public static void printList(List<Square> list) {
		for (Square item: list) {
			System.out.print(item.getValue() + "");
		}
	}
	
}
