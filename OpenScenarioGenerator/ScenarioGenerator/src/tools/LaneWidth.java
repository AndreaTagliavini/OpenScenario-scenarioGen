package tools;

/**
 * @author AndreaTagliavini
 * This class is used to indicate the width of a single lane
 */
public class LaneWidth {
	private double sOffset;
	private double a;
	private double b;
	private double c;
	private double d;
	
	public LaneWidth(double a){
		this.a = a;
		this.sOffset = 0;
		this.b=0;
		this.c=0;
		this.d=0;
	}
	
	public LaneWidth(double sOffset, double a, double b, double c, double d){
		this.sOffset=sOffset;
		this.a=a;
		this.b=b;
		this.c=c;
		this.d=d;
	}
	
	public double getSOffset(){
		return this.sOffset;
	}
	
	public double getA(){
		return this.a;
	}
	
	public double getB(){
		return this.b;
	}
	
	public double getC(){
		return this.c;
	}
	
	public double getD(){
		return this.d;
	}
}
