
package com.taskone;

import java.text.DecimalFormat;

public class Triangle extends GeometricObject{

	private double side1 = 1.0;
	private double side2 = 1.0;
	private double side3 = 1.0;
	private double area = 0;
	private double perimeter = 0;
	private String desc;
	
	Triangle(){};
	
	Triangle(double s1, double s2, double s3, 
			String colour, Boolean isFilled){
		
		super(colour, isFilled);
		this.side1 = s1;
		this.side2 = s2;
		this.side3 = s3;
		
		this.setDesc();
		
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
		
		desc = "Area: " + df.format(this.getArea()) + " Units"
				+ "\nPerimeter: " + df.format(this.getPerimeter()) + " Units"
				+ "\nColour: " + this.getColour() 
				+ "\nFilled: " + this.isFilled();
	}
}
