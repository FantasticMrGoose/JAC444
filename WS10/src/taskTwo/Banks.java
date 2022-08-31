

package taskTwo;

import java.util.Scanner;

public class Banks {
	
	public static void main(String[] args) {
		
		int bankID, assetLimit;
		double loaned; 
				
		Scanner input = new Scanner(System.in);
		
		System.out.print("Number of banks: ");
		int numBanks = input.nextInt();
		System.out.print("Minimum asset limit: ");
		assetLimit = input.nextInt();
		
		double[] balance = new double[numBanks];
		double[][] banksBorrow = new double[numBanks][numBanks];
		
		// Stores the bank info
		for(int i = 0; i < numBanks; i++) {
			System.out.println("For Bank # " + i);
			System.out.print("\tBalance: ");
			balance[i] = input.nextDouble();
			System.out.print("\tNumber of banks Loaned: ");
			int numBanksLoaned = input.nextInt();
			
			for (int j = 0; j < numBanksLoaned; j++) {
				
				System.out.print("\t\tBank ID who gets the loan: ");
				bankID = input.nextInt();
				
				System.out.print("\t\tLoaned Amount: ");
				loaned = input.nextDouble();
				banksBorrow[i][bankID] = loaned;
			}
		}
		
		// checks if each bank is safe
		boolean[] notSafe = new boolean[numBanks];
		boolean keepGoing = false;
		
		do {
			keepGoing = false;
			for(int i = 0; i < numBanks; i++) {
				double assets = balance[i];
				for (int j = 0; j < banksBorrow[i].length; j++) {
					assets += banksBorrow[i][j];
				}
				if(assets < assetLimit) {
					notSafe[i] = true;
					
					// if the bank is not safe, sets the loan to 0
					// so it is not counted
					for (int j = 0; j < banksBorrow.length; j++) {
						if (banksBorrow[j][i] != 0) {
							banksBorrow[j][i] = 0;
							keepGoing = true;
						}
					}
				}
					
				
			}
		} while(keepGoing);
		
		System.out.print("Unsafe banks are:");
		for (int i = 0; i < notSafe.length; i++) {
			if (notSafe[i]) {
				System.out.print(" Bank " + i + " ");
		  }
		
		input.close();
		}
		}
	}
