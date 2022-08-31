
package com.tasktwo;

import java.util.Scanner;

public class TestClassTwo {
	
	public static Scanner input = new Scanner(System.in);

	public static void main (String[] args) {
		
		try {
			TriangleTwo legalTri = new TriangleTwo(5, 7, 7, "Red", true);
			System.out.println("Creating a legal triangle...\n");
			System.out.println(legalTri.toString());
			
			System.out.println("\n----------------\n");
			
			System.out.println("Creating an illegal triangle...");
			TriangleTwo illegalTri = new TriangleTwo(10, 5, 3, "Blue", false);
			System.out.print(illegalTri.toString());
			
		}
		catch(TriangleTwo.TriangleException exception) {
			System.out.println("Demo completed");
		}
		finally {
			System.out.println("Goodbye");
		}
		
	}
	
}

		
		
