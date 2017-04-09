package tools;

/**
 * @author AndreaTagliavini
 * This class is used to easily store a 2D coordinate
 * it implements the methods get and set for each value
 */
public class Coordinate {
	private double x;
	private double y;
	
	public Coordinate(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}
}
