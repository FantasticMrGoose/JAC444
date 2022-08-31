
package com.TaskTwo;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class letterCounter {
	
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException{
		
		// initialize a char array of the alphabet (lower and upper)
		char[] abt = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();
		int[] letterCnt = new int[abt.length];
		
		System.out.print("Enter a filename: ");
		
		try {
			// read the file
			BufferedReader buffRead = new BufferedReader(
					new FileReader(input.next())
					);
			
			String s; 
			while ((s = buffRead.readLine()) != null) { 
				// iterate through the alphabet array
				for(int i = 0; i < abt.length; i++) {
					// iterate through the letters in the line
					for(int j = 0; j < s.length(); j++) {
						if(abt[i] == s.charAt(j)) {
							letterCnt[i]++;
						}
					}
					
				}
			} 

			for (int i = 0; i < abt.length; i++) {
				System.out.println("Number of " + abt[i] + "'s: " + letterCnt[i]);	
	        }
			
			buffRead.close();
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		input.close();
	}
}
