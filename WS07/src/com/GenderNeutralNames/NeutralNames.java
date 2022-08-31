

package com.GenderNeutralNames;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

public class NeutralNames {
	private static ArrayList<String> nameLines = new ArrayList<>();
	private static ArrayList<String> boyNameList = new ArrayList<>();
	private static ArrayList<String> girlNameList = new ArrayList<>();
	private static ArrayList<String> commonName = new ArrayList<>();
	
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException{
		
		List list = new ArrayList();
		list.add("Red");
		list.add(new Integer(100));
		list.add(new ArrayList()); 
	 	list.add(new java.util.Date());

		
		
		System.out.print("Enter a file name for baby name ranking: ");
		
		try {
			// read the file
			BufferedReader buffRead = new BufferedReader(
					new FileReader("Babynames files\\" + input.next())
					);
			
			String s;
			while ((s = buffRead.readLine()) != null) { 
				nameLines.add(s);
			} 
			
			buffRead.close();
			
			loadNames();
			matchNames();
			printResults();
			
			sortAndPrint();
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}
		
		input.close();
	}

	public static void loadNames() {
		
		// Splits the name and trims white space
		for (String names: nameLines) {
			String[] tempNames = names.split("\t");
			for(int i = 0; i < tempNames.length; i++) {
				tempNames[i] = tempNames[i].trim();
			}
			
			boyNameList.add(tempNames[1]);
			girlNameList.add(tempNames[3]);
		}
	}
	
	public static void matchNames() {
		for (String boyNameLists: boyNameList) {
			for(String girlNameLists: girlNameList) {
				if(girlNameLists.equals(boyNameLists)) {
					commonName.add(boyNameLists);
				}
			}
		}
	}
	
	public static void printResults() {
		
		if(commonName.size() > 0) {
			System.out.println(commonName.size() + " names used for both genders");
			System.out.print("They are ");
			for(String names: commonName) {
				System.out.print(names + " ");
			};
		}
		else {
			System.out.println("There are no common names for this year");
		}
				
	}
	
	private static void sortAndPrint() {
		Collections.sort(boyNameList);
		Collections.sort(girlNameList);
			
		// removing duplicates using hash set
		boyNameList = new ArrayList<String>(new LinkedHashSet<String>(commonName));
		girlNameList = new ArrayList<String>(new LinkedHashSet<String>(girlNameList));
			
		System.out.println("\n-------------------------------------------");
			
		System.out.println("\nSorted Boys name without duplicates: ");
		for(String names: boyNameList) {
			System.out.print(names + "\n");
		};
			
		System.out.println("\n-------------------------------------------");
			
		System.out.println("Sorted Girls name without duplicates: ");
		for(String names: girlNameList) {
			System.out.print(names + "\n");
		};
		
	}
			
	
}

