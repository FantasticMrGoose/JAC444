
public class Craps {
	
	int die1, die2;

	public static void main(String[] args) {
		
		Craps game = roll();
		int sum = game.die1 + game.die2;
		
		rollMsg(game.die1, game.die2, sum);
		
		if ((sum == 2) || (sum == 3) || (sum == 12)){
			winMsg();
		}
		else if ((sum == 7) || (sum == 11)){
			loseMsg();
		}
		else {
			int point = sum;
			System.out.println("Point is (established) set to " + point);
			boolean keepRolling = true;
			
			do {
				game = roll();
				sum = game.die1 + game.die2;
				rollMsg(game.die1, game.die2, sum);
				if (sum == point){
					winMsg();
					keepRolling = false;
				}
				else if (sum == 7){
					loseMsg();
					keepRolling = false;
				}
				else {
					keepRolling = true;
				}
				
				
			} while (keepRolling);
		}
	}
	
	public static Craps roll() {
		
		Craps roll = new Craps();
		
		roll.die1 = (int) (1 + Math.random() * 6);
		roll.die2 = (int) (1 + Math.random() * 6);
		return roll;
	}
	
	public static void winMsg() {
		System.out.println("Congratulations, You win");
	}
	
	public static void loseMsg() {
		System.out.println("Craps, Better Luck Next Time, You lose");
	}
	
	public static void rollMsg(int die1, int die2, int sum) {
		System.out.println("You rolled " + die1 + " + " + die2 + " = "
				+ sum);
	}

}
