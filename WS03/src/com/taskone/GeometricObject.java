
package com.taskone;

public abstract class GeometricObject {

	private String colour = "white";
	private Boolean filled;
	
	protected GeometricObject(){};
	
	protected GeometricObject(String colour, Boolean filled){
		this.colour = colour;
		this.filled = filled;
	}
	
	public String getColour() {
		return this.colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public Boolean isFilled() {
		return this.filled;
	}
	
	public void setFilled(Boolean filled) {
		this.filled = filled;
	}
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
}
