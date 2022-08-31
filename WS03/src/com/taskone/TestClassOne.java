

package com.taskone;

import java.util.Scanner;

public class TestClassOne {
	
	public static Scanner input = new Scanner(System.in);

	public static void main (String[] args) {
		
		double side[] = new double[3];
		String colour;
		String filled;
		Boolean keepGoing = true;
		Boolean isFilled = false;
		
		do {
			// record triangle dimensions
			System.out.println("Please enter the triangle dimensions.");
			System.out.print("Side 1: ");
			side[0] = input.nextDouble();
			System.out.print("Side 2: ");
			side[1] = input.nextDouble();
			System.out.print("Side 3: ");
			side[2] = input.nextDouble();
			
			for(int i = 0; i < side.length; i++) {
				if(side[i] <= 0) {
					System.out.println("Please enter a valid number");
					i = side.length;
					keepGoing = true;
				}
				else {
					keepGoing = false;
				}
			}
			
		} while(keepGoing);
		
		// records the triangle colour
		System.out.print("Please enter the triangle colour: ");
		colour = input.next();
		
		keepGoing = true;
		
		do {
			// records if triangle is filled
			System.out.print("Is the triangle filled? (Yes/No): ");
			filled = input.next();
			
			if(filled.equals("Yes") || filled.equals("yes")) {
				isFilled = true;
				keepGoing = false;
			}
			else if(filled.equals("No") || filled.equals("no")) {
				isFilled = false;
				keepGoing = false;
			}
			else {
				System.out.println("Please enter Yes or No.");
			}
		} while (keepGoing);
		
		
		Triangle tri = new Triangle(side[0], side[1], side[2], colour, isFilled);
		
		System.out.print(tri.toString());
		
		input.close();
		
	}
	
}
