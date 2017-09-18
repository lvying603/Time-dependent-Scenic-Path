package greedLS;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		/**
		 * Initialize the solution path with NULL, 
		 * i.e., gap(source, destination). 
		 */
		String startSolution = "NULL";
		
		/**
		 * Initialize the solution path with the 
		 * time-dependent shortest path.
		 */
		//String startSolution = "SP"; 
		
		GreedLS greedLS = new GreedLS();
		greedLS.startSolution = startSolution;
		greedLS.GreedLSAlgorithm(QuerySetting.budgetTime);
	}
}

