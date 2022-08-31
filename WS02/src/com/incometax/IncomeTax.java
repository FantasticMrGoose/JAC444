
package com.incometax;

public class IncomeTax {

	public static int SINGLE_FILER = 0;
	public static int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
	public static int MARRIED_SEPARATELY = 2;
	public static int HEAD_OF_HOUSEHOLD = 3;
	public static int numberOfStatus = 4;
	
	private int filingStatus = 0;
	private int[][] intervals = {{0}};
	private double[] rates = {0};
	private double taxableIncome = 0;
	
	// No arg constructor
	IncomeTax(){}
	
	// 4 arg constructor
	IncomeTax(int filingStatus, int[][] intervals, double[] rates, 
			double taxableIncome){
		setFilingStatus(filingStatus);
		setIntervals(intervals);
		setRates(rates);
		setTaxableIncome(taxableIncome);
	}
	
	public int getFilingStatus() {
		return this.filingStatus;
	}
	
	public void setFilingStatus(int status) {
		this.filingStatus = status;
	}
	
	public int[][] getIntervals(){
		return this.intervals;
	}
	
	public void setIntervals(int [][] intervals) {
		this.intervals = new int[intervals.length][intervals[0].length];
		
		// deep copy of 2d array
		for (int i = 0; i < intervals.length; i++) {
			System.arraycopy(intervals[i], 0, this.intervals[i], 0, intervals[i].length);
		}
	}
	
	public double[] getRates() {
		return this.rates;
	}

	public void setRates(double[] rates) {
		this.rates = new double[rates.length];
		System.arraycopy(rates, 0, this.rates, 0, rates.length);
	}
	
	public double getTaxableIncome() {
		return this.taxableIncome;
	}
	
	public void setTaxableIncome(double taxableIncome) {
		this.taxableIncome = taxableIncome;
	}
	
	// calculates the income tax amount for the current object
	public double calculateIncomeTax() {
		
		int i = 0;
		double taxAmount = 0;
		boolean keepGoing = true;		
		
		// calculates the first tax bracket
					if(this.taxableIncome >= intervals[this.filingStatus][i]) {
						taxAmount += rates[i] * (intervals[this.filingStatus][i]);
					}
					else {
						taxAmount += this.taxableIncome * rates[i];
						keepGoing = false;
					}
					
					// calculates the subsequent tax brackets
					for(i = 1; i < intervals[this.filingStatus].length && keepGoing; i++) {
						
						if(this.taxableIncome >= intervals[this.filingStatus][i]) {
							taxAmount += rates[i] * (intervals[this.filingStatus][i] -
									intervals[this.filingStatus][i-1]);
						}
						else {
							taxAmount += rates[i] * (this.taxableIncome - intervals[this.filingStatus][i-1]);
							keepGoing = false;
						}
					}
		
		// calculates tax for values greater than tax bracket range
		if(i == intervals[this.filingStatus].length && 
				this.taxableIncome > intervals[this.filingStatus][i-1]) {
			taxAmount += rates[i] * (this.taxableIncome - intervals[this.filingStatus][i-1]);
		}


		return taxAmount;
	}
}
