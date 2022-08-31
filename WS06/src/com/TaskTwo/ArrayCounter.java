
package com.TaskTwo;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayCounter {
	
	public static Scanner input = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#.###");
	
	// Max value
	public static final ArrayProcessor max = arr -> {
		Arrays.sort(arr);
		return arr[arr.length - 1];
	};
	
	// Min value
	public static final ArrayProcessor min = arr -> {
		Arrays.sort(arr);
		return arr[0];
	};
	
	// Sum
	public static final ArrayProcessor sum = arr -> {
		double sum = 0;
		for (double x : arr) {
			sum += x;
		}
		return sum;
	};
	
	// Average
	public static final ArrayProcessor avg = arr -> {
		return (sum.apply(arr) / arr.length);
	};
	
	// Counter
	public static ArrayProcessor counter(double value) {
		ArrayProcessor count = arr -> {
			int cnt = 0;
			for (double x : arr) {
				if(x == value)
					cnt++;
			}
			return cnt;
		};
		return count;
		
	}
	
	public static void main(String[] args) {
		
		int numElem;
		double count;
		
		System.out.print("Enter the number of elements you want in the array: ");
		numElem = input.nextInt();
		
		double[] arr = new double[numElem];
		
		System.out.print("Enter the numbers you want in your array of " + numElem + ": ");
		
		for(int i = 0; i < numElem; i++) {
			arr[i] = input.nextDouble();
		}
		
		System.out.println("----------------------------------------------------------");
		System.out.println("The max value in your array is: " + df.format(max.apply(arr)));
		System.out.println("The min value in your array is: " + df.format(min.apply(arr)));
		System.out.println("The sum of your array is: " +  df.format(sum.apply(arr)));
		System.out.println("The average value of your array is: " +  df.format(avg.apply(arr)));
		System.out.println("----------------------------------------------------------");
		System.out.print("Enter a number to count the number of times it occurs in your array: ");
		
		count = input.nextDouble();
		
		System.out.println("\nThe number " +  df.format(count) + " occurs " +  df.format(counter(count).apply(arr)) + 
				" time(s) in your array.");
		
		input.close();
	}
}
