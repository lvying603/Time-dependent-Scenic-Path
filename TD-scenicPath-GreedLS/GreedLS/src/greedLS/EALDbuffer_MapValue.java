package greedLS;


/**
 * @author Ying Lu <ylu720@usc.edu>
 *
 * Define the class of arcs in the HashMap Buffer. 
 */
 
public class EALDbuffer_MapValue {
	//for map key (v0, v)
	//public int t0; //startTime of v0
	//public int b; //budget
	public int EACost;
	public int LDCost;
	
	EALDbuffer_MapValue(){
		//this.t0 = -1;
		//this.b = -1;
		this.EACost = 0;
		this.LDCost = 0;
	}
	
	EALDbuffer_MapValue(int ea, int ld){
		//this.t0 = t;
		//this.b = budget;
		this.EACost = ea;
		this.LDCost = ld;
	}
	
	public void setEA(int ea){
		//this.t0 = t;
		//this.b = budget;
		this.EACost = ea;
	}
	public void setLD(int ld){
		//this.t0 = t;
		//this.b = budget;
		this.LDCost = ld;
	}
}
