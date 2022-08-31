
package com.MatrixAdd;

public class TestClass {
	
	private static double[][] a = new double[2000][2000];
	private static double[][] b = new double[2000][2000];
	private static double[][] result;

	public static void main (String[] args) {
		
		//populate the matrix
		for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) 
            { a[i][j] = Math.random(); }
        }
		
		for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) 
            { b[i][j] = Math.random(); }
        }		
		
		System.out.println("Parallel implementation");
		
		result = MatrixAdd.parallelAddMatrix(a, b);

		System.out.println("\nSequential implementation");;
		
		result = MatrixAdd.sequentialAddMatrix(a, b);	
	}
	
}
