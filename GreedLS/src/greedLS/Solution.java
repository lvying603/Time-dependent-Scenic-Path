package greedLS;

import java.util.LinkedList;
import java.util.List;

public class Solution {
	public List<Gap> gapList;
	
	/* The travel time of the solution path [millisecond] */
	public int totalCost; 
	
	/* The total value collected along the solution path. */
	public int totalValue; 
	
	Solution(){
		this.gapList = new LinkedList<Gap>();
		this.totalCost = 0;
		this.totalValue = 0;
	}
	
	/**
	 * Insert an arc into a gap in the solution path. 
	 *
	 * @param gap: the gap (vi, vj) in the solution path.
	 * @param best_vi_vm: a gap (vi, vm)
	 * @param best_vn_vj: a gap (vn, vj)
	 * (vm, vn) is the best selected arc to be inserted into the solution. 
	 * @param deltaValue: the improved value.
	 * @param deltaCost: detour travel cost. 
	 */
	public void insertArc(Gap gap, 
	                      Gap best_vi_vm, 
						  Gap best_vn_vj, 
						  int deltaValue, 
						  double deltaCost){
		this.gapList.add(this.gapList.indexOf(gap), best_vi_vm);
		this.gapList.add(this.gapList.indexOf(gap), best_vn_vj);
		this.gapList.remove(gap);
		this.totalCost += deltaCost;
		this.totalValue += deltaValue;
	}
	
	public void empty(){
		this.gapList.clear();
		this.totalCost = 0;
		this.totalValue = 0;
	}
}
