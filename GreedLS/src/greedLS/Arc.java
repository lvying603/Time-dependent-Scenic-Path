package greedLS;


import java.util.ArrayList;
import java.util.List;
//import java.util.PriorityQueue;



/**
 * @author Ying Lu <ylu720@usc.edu>
 *
 * Define the class of arcs in the road network. 
 */

public class Arc { 
	public int source;
	public int target;
	public Pair<Integer,Integer> cost_value_list; //millisecond, photo#
	//public int earliestArrive;
	//public int latestDeparture;
	//public double criteria; //to see
	
	Arc(){
		this.source = -1;
		this.target = -1;
	}
	Arc(int ps, int pt){
		this.source = ps;
		this.target = pt;
	}
	void copyArc(Arc a){
		this.source = a.source;
		this.target = a.target;
		this.cost_value_list = a.cost_value_list;
	}
	
	@Override
	public boolean equals(Object aa){
		Arc a = (Arc)aa;
		if(this.source==a.source && this.target==a.target) return true;
		else return false;
	}
	
	//to merge and remove duplicates from multiple lists in Java (addAll)
	//This is required for HashSet
    //Note that if you override equals, you should override this
    //as well. See: http://stackoverflow.com/questions/27581/overriding-equals-and-hashcode-in-java
    @Override 
    public int hashCode()
    {
        return ((Integer)source).hashCode() + ((Integer)target).hashCode();
    }
	
	Boolean equalNULL(){
		if(this.source == -1 && this.target ==-1) return true;
		else return false;
	}
}
