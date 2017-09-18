package greedLS;

public class QuerySetting {
	public static int SourceVexID = 1; 
	public static int TargetVexID = 10; 
	
	/** 
	 * Always use the road network at snapshot startTime=0. 
	 * (i.e., static road network).
	 */
	public static int startTime = 0 ; 
	
	/* budget of traveling time [milisecond]*/
	public static int budgetTime = 291940; 
	
	/* Running time threshold [second] */
	public static int runingTimeThreshold = 1; 
}
