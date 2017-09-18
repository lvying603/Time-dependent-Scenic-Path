package greedLS;

import java.util.ArrayList;
import java.util.List;

public class solutionArc {
	public int source;
	public int target;
	public int cost;
	public int value;
	
	solutionArc(){
		this.source = -1;
		this.target = -1;
		this.cost = 0;
		this.value = 0;
	}
	
	solutionArc(int s, int t, int c, int v){
		this.source = s;
		this.target = t;
		this.cost = c;
		this.value = v;
	}
	
	
	void copysolutionArc(solutionArc a){
		this.source = a.source;
		this.target = a.target;
		this.cost = a.cost;
		this.value = a.value;
	}
	
	@Override
	public boolean equals(Object aa){
		solutionArc a = (solutionArc)aa;
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
