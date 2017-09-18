package greedLS;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ying Lu <ylu720@usc.edu>
 *
 * Define the class of gap in a path. 
 */
 
public class Gap {
	
	/* The starting node of the gap */
	public int start;
	
	/* The ending node of the gap */
	public int end;
	
	/**
     * The travel time of the time-depedent shortest path 
	 * from start node to end node (i.e., tdsp closing the gap). 
	 */
	public int SPCost;
	
	//public double SPDist;
	
	/* The time to enter the start node. */
	public int actualStarttime; 
	
	/* The value collected along the tdsp closing the gap. */
	public int collectedValue;
	
	public List<Integer> vexIDList;
	
	Gap(){
		this.start = -1;
		this.end = -1;
		this.SPCost = Integer.MAX_VALUE;
		//this.SPDist = Integer.MAX_VALUE;
		this.collectedValue = 0;
		this.actualStarttime = 0;
		this.vexIDList = new LinkedList<Integer>();
	}
	
	Boolean isEmpty(){
		if(this.start==-1 && this.end==-1) return true;
		return false;
	}
	
	Boolean equalNULL(){
		if(this.start == -1 && this.end ==-1) return true;
		else return false;
	}
	
	void Empty(){
		this.start = -1;
		this.end = -1;
		this.SPCost = Integer.MAX_VALUE;
		//this.SPDist = Integer.MAX_VALUE;
		this.collectedValue = 0;
		this.actualStarttime = 0;
		this.vexIDList.clear();
	}
}
