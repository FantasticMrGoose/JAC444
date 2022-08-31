
import java.text.DecimalFormat;
import java.util.Scanner;

public class MaxLocation {
	
	public int Row;
	public int Column;
	public double maxValue;
	

	public static void main(String[] args) {		 
		
		DecimalFormat df = new DecimalFormat("##.##");
		double[][] locationArr;
		
		//ask for size of the double array
		System.out.print("Enter the number of rows and columns in the array: ");
		Scanner input = new Scanner(System.in);
		
		locationArr = new double [input.nextInt()][input.nextInt()];

		
		System.out.println("Enter the array: ");
		Scanner input2 = new Scanner(System.in);
		
		
		for (int row = 0; row < locationArr.length; row++) {
			for (int column = 0; column < locationArr[row].length; column++) {
				locationArr[row][column] = input2.nextDouble();
			}
		}
		
		MaxLocation large = largest(locationArr);
		
		System.out.print("The location of the largest element is " + (df.format(large.maxValue)) + " at (" + large.Row 
				+ ", " + large.Column + ")");
		
		input.close();
		input2.close();
	}
	
	public static MaxLocation largest(double[][] locArr) {
		
		MaxLocation largest = new MaxLocation();
		largest.maxValue = locArr[0][0];
		largest.Row = 0;
		largest.Column = 0;
		
		for (int row = 0; row < locArr.length; row++) {
			for (int column = 0; column < locArr[row].length; column++) {
				if(locArr[row][column] > largest.maxValue) {
					largest.maxValue = locArr[row][column];
					largest.Row = row;
					largest.Column = column;
				}
			}
		}
		
		return largest;
	}

}
