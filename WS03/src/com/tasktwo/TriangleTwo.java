
package com.tasktwo;

import com.taskone.GeometricObject;
import java.text.DecimalFormat;

public class TriangleTwo extends GeometricObject{

	private double side1 = 1.0;
	private double side2 = 1.0;
	private double side3 = 1.0;
	private double area = 0;
	private double perimeter = 0;
	private String desc;
	
	TriangleTwo(){};
	
	TriangleTwo(double s1, double s2, double s3, 
			String colour, Boolean isFilled) throws TriangleException {
		
		super(colour, isFilled);
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
		validTriangle();
		this.setDesc();
		
	}
	
	private void validTriangle() throws TriangleException{
		
		Boolean invalid = false;
		
		if((side1 + side2 < side3) || (side2 + side3 < side1) || 
				(side1 + side3 < side2)) {
			throw new TriangleException();
		}	
	}	
	
	public double getSide1() {
		return this.side1;
	}
	
	public double getSide2() {
		return this.side2;
	}
	
	public double getSide3() {
		return this.side3;
	}
	
	@Override
	public double getArea() {
		double s = (side1 + side2 + side3)/2;
		this.area = Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
		return this.area;
	}
	
	@Override
	public double getPerimeter() {
		this.perimeter = side1 + side2 + side3;
		return this.perimeter;
	}
	
	public String toString() {
		return this.desc;
	}
	
	private void setDesc() {
		DecimalFormat df = new DecimalFormat("0.00");
		
		desc = "The triangle's description:"
				+"\nSide 1: " + side1 
				+"\nSide 2: " + side2 
				+"\nSide 3: " + side3 				
				+"\nArea: " + df.format(this.getArea()) + " Units"
				+ "\nPerimeter: " + df.format(this.getPerimeter()) + " Units"
				+ "\nColour: " + this.getColour() 
				+ "\nFilled: " + this.isFilled();
	}
	
	public class TriangleException extends Exception{
		public TriangleException() {
			System.out.println("\nThe triangle's dimension "+
					"\nSide 1: " + side1 +  
					"\nSide 2: " + side2 + 
					"\nSide 3: " + side3 + 
					"\ncreates an invalid triangle");
		}
	}
	
}
