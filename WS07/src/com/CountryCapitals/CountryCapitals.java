
package com.CountryCapitals;

import java.util.Scanner;
import java.util.HashMap;

public class CountryCapitals {

	private static HashMap<String, String> country = new HashMap<String, String>();
	

	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		
		// 25 countries and capitals
		country.put("canada", "Ottawa");
		country.put("united states of america", "Washington, D.C");
		country.put("united kingdom", "London");
		country.put("france", "Paris");
		country.put("china", "Beijing");
		country.put("italy", "Rome");
		country.put("japan", "Tokyo");
		country.put("germany", "Berlin");
		country.put("india", "New Delhi");
		country.put("brazil", "Brasília");
		country.put("russia", "Moscow");
		country.put("south korea", "Seoul");
		country.put("australia", "Canberra");
		country.put("turkey", "Istanbul");
		country.put("mexico", "Mexico City");
		country.put("spain", "Madrid");
		country.put("norway", "Oslo");
		country.put("iran", "Tehran");
		country.put("poland", "Warsaw");
		country.put("egypt", "Cairo");
		country.put("nigeria", "Abuja");
		country.put("netherlands", "Amsterdam");
		country.put("greece", "Athens");
		country.put("iraq", "Baghdad");
		country.put("thailand", "Bangkok");
		
		boolean stop = false;
		
		
		do {
			String search = "";
			String option = "";
			System.out.print("Enter a country and I will tell you its capital: ");
			search = input.nextLine().toLowerCase();
			
			
			if(country.get(search) != null) { 
				System.out.println("The capital city of " + search.toUpperCase() + " is " + country.get(search) );
			}
			else {
				System.out.println(search + " is not in our database.");
			}
			
			System.out.print("Do you want to search another country (Y/N): ");
			option = input2.next().toLowerCase();
			
			if(option.charAt(0) == 'n') {
				stop = true;
				System.out.print("Goodbye");
			}
			else {
				stop = false;
			}
			
			
		}
		while(!stop);
		
		input.close();
		input2.close();
		
		
	}
}
