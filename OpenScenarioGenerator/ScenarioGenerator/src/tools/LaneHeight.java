package tools;

/**
 * @author AndreaTagliavini
 * This class is used to indicate the height of a single lane
 */
public class LaneHeight {
	private double sOffset;
	private double inner;
	private double outer;
	
	public LaneHeight(double inner, double outer){
		this.inner=inner;
		this.outer=outer;
		this.sOffset=0;
	}
	
	public LaneHeight(double sOffset, double inner, double outer){
		this.sOffset=sOffset;
		this.inner=inner;
		this.outer=outer;
	}
	
	public double getSOffset(){
		return this.sOffset;
	}
	public double getInner(){
		return this.inner;
	}
	public double getOuter(){
		return this.outer;
	}
}
