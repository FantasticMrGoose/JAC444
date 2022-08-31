
package com.incometax;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TestClass {

	// Tax brackets
	public static int[][] interval2001 = {
			{27050, 65550, 136750, 297350},
			{45200, 109250, 166500, 297350},
			{22600, 54625, 83250, 148675},
			{36250, 93650, 151650, 297350}
	};
	
	public static int[][] interval2009 = {
			{8350, 33950, 82250, 171550, 372950},
			{16700, 67900, 137050, 208850,372950},
			{8350, 33950, 68525, 104425, 186475},
			{11950, 45500, 117450, 190200, 372950},
	};
	
	// Tax rates
	public static double[] rates2001 = {
			0.15, 0.275, 0.305, 0.355, 0.391
	};
	
	public static double[] rates2009 = {
			0.10, 0.15, 0.25, 0.28, 0.33, 0.35
	};
	
	public static Scanner input = new Scanner(System.in);
	
	public static void main (String[] args) {
		
		printMenu();
		
		input.close();
		
	}
	
	public static void printMenu() {
		
		int option;
		
		//display menu
		do {
			System.out.println("---------------------------------------------------------");	
			System.out.println("                     Main Menu");
			System.out.println("---------------------------------------------------------");		
			System.out.println("1 - Compute personal income Tax");
			System.out.println("2 - Print the tax tables for taxable incomes (with range)");
			System.out.println("3 - Exit");
			System.out.println("---------------------------------------------------------");
			System.out.print("Please select an option: ");
			
			option = input.nextInt();
			
			if(option == 1) {
				computeTax();
			}
			else if(option == 2) {
				printTaxTable();
			}
			else if(option == 3){
				System.out.println("Have a nice day!");
			}
			else {
				System.out.println("Please select one of the available options");
			}
			System.out.println();

			
		} while(option != 3);

	}
	
	public static void printTableHeader() {
		System.out.println("|---------------------------------------------------------------------------------|");
		System.out.println("|Taxable         Single           Married Joint      Married           Head of    |");
		System.out.println("|Income                           or Qualifying      Separate          a House    |");
		System.out.println("|                                  Widow(er)                                      |");
		System.out.println("|---------------------------------------------------------------------------------|");
	}
	
	public static void computeTax() {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		IncomeTax incomeTax = new IncomeTax();
		int filingStatus;
		int taxYear;
		double income; 
		double tax;
		
		// display filing option
		do {
			System.out.println();
			System.out.println(IncomeTax.SINGLE_FILER + " - single filer");
			System.out.println(IncomeTax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOW + " - married jointly or qualifying"
					+ " widow(er)");
			System.out.println(IncomeTax.MARRIED_SEPARATELY + " - married separately");
			System.out.println(IncomeTax.HEAD_OF_HOUSEHOLD + " - head of household");
			System.out.print("Enter the filling status: ");
			
			filingStatus = input.nextInt();
			
			if(filingStatus < 0 || filingStatus > 4) {
				System.out.println("Please select one of the availabe options");
			}
			else {
				incomeTax.setFilingStatus(filingStatus);
			}
			
			
		} while (filingStatus < 0 || filingStatus > 4);
		
		// select tax year
		do {
			System.out.println("\nTax Year: 2001 | 2009");
			System.out.print("Please choose a Tax Year: ");
			taxYear = input.nextInt();
			
			
			if(taxYear == 2001) {
				incomeTax.setIntervals(interval2001);
				incomeTax.setRates(rates2001);
			}
			else if(taxYear == 2009){
				incomeTax.setIntervals(interval2009);
				incomeTax.setRates(rates2009);
			}
			else {
				System.out.println("Please select one of the availabe options");
			}
		} while(taxYear != 2001 && taxYear != 2009);
		
		do {
			System.out.print("\nEnter the Taxable Income: $");
			income = input.nextDouble();
			
			if(income < 0) {
				System.out.println("Please enter a valid amount");
			}
			else {
				incomeTax.setTaxableIncome(income);
			}
		} while(income < 0);
		
		tax = incomeTax.calculateIncomeTax();
		
		System.out.println("\nTax is: $" + df.format(tax));
		
	}
	
	public static void printTaxTable() {
		DecimalFormat df = new DecimalFormat("0.00");
		
		IncomeTax tax2001;
		IncomeTax tax2009;
		
		int fromAmount = 0;
		int toAmount = 0;
		int rowCount; 
		System.out.print("\nPlease enter values in $1000 increments (i.e. $40000 to $50000)");
		
		// Get values from the user
		do {
			System.out.print("\nEnter the amount From: $");
			fromAmount = input.nextInt();
			
			System.out.print("\nEnter the amount To: $");
			toAmount = input.nextInt();
			
			if((fromAmount + toAmount) % 1000 != 0 ){
				System.out.println("Please enter values in $1000 increments (i.e. $40000 to $50000)");
			}
		} while ((fromAmount + toAmount) % 1000 != 0 ); // ensure values are intervals of 1000
		
		rowCount = (int) ((toAmount - fromAmount) / 1000); // the number of increments
		
		System.out.println("\n 2001 tax tables for taxable income from $" + df.format(fromAmount) 
			+ " to $" + df.format(toAmount));
		
		printTableHeader();
		
		// Display 2001 tax table
		for(int i = 0; i <= rowCount; i++) {
			
			System.out.print(" ");
			System.out.print((i * 1000 + fromAmount));
			
			//initialize tax objects with arguments
			for(int j = 0; j < IncomeTax.numberOfStatus; j++) {
				tax2001 = new IncomeTax(j, interval2001, rates2001, i * 1000 + fromAmount);
				System.out.printf("%18s", df.format(tax2001.calculateIncomeTax()));
			}
			System.out.println("\n -------------------------------------------------------------------------------");
		}
		
		// Display 2009 tax table
		System.out.println("\n");
		System.out.println("\n 2009 tax tables for taxable income from $" + df.format(fromAmount) 
			+ " to $" + df.format(toAmount));
		
		printTableHeader();
		
		// Tax table for 2009
		for(int i = 0; i <= rowCount; i++) {
			
			System.out.print(" ");
			System.out.print((i * 1000 + fromAmount));
			
			//initialize tax objects with arguments
			for(int j = 0; j < IncomeTax.numberOfStatus; j++) {
				tax2001 = new IncomeTax(j, interval2009, rates2009, i * 1000 + fromAmount);
				System.out.printf("%18s", df.format(tax2001.calculateIncomeTax()));
			}
			System.out.println("\n -------------------------------------------------------------------------------");
		}
		System.out.println("\n");
	}
	
	
	
	
}
