
package com.MatrixAdd;

import java.util.Arrays;

public class MatrixAdd {
	
	static long start, end;
	
	public static double[][] parallelAddMatrix(double[][] a, double[][] b){
		double [][] addition = new double[a.length][a[0].length];
		
		start = System.currentTimeMillis();
		
		parallelAddMatrix m1 = new parallelAddMatrix(Arrays.copyOfRange(a, 0, 500), Arrays.copyOfRange(b, 0, 500));
		parallelAddMatrix m2 = new parallelAddMatrix(Arrays.copyOfRange(a, 501, 1000), Arrays.copyOfRange(b, 501, 1000));
		parallelAddMatrix m3 = new parallelAddMatrix(Arrays.copyOfRange(a, 1001, 1500), Arrays.copyOfRange(b, 1001, 1500));
		parallelAddMatrix m4 = new parallelAddMatrix(Arrays.copyOfRange(a, 1501, 2000), Arrays.copyOfRange(b, 1501, 2000));
			
		Thread t1 = new Thread(m1);
		Thread t2 = new Thread(m2);
		Thread t3 = new Thread(m3);
		Thread t4 = new Thread(m4);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		t3.start();
		t4.start();
		
		
		end = System.currentTimeMillis();
		
		System.out.println("The parallel calculation took: " +
				(end - start) + " miliseconds");
		
		return addition;
	}
	
	public static double[][] sequentialAddMatrix(double[][] c, double[][] d){
		
		double [][] addition = new double[c.length][c[0].length];
		
		start = System.currentTimeMillis();
		for(int i = 0; i < c.length; i++) {
            for(int j = 0; j < d.length; j++) {
            	addition[i][j] = c[i][j] + d[i][j];
            }
        }
		end = System.currentTimeMillis();
		
		System.out.println("The sequential calculation took: " +
				(end - start) + " miliseconds");
		
		return addition;
	}
}

class parallelAddMatrix implements Runnable{
	
	private static double[][] a;
	private static double[][] b;
	public double[][] addition;
	
	//constructor
	parallelAddMatrix(double[][] a, double[][] b) {
		
		this.a = a;
		this.b = b;
	}
	
	public double[][] getResult(){
		return addition;
	}
	
	@Override
	public void run() {
		
		addition = new double[a.length][a[0].length];
		for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < b.length; j++) {
            	addition[i][j] = a[i][j] + b[i][j];
            }
        }
	}
	
}
