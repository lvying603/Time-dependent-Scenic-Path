package greedLS;


/**
 * @author Ying Lu <ylu720@usc.edu>
 *
 * Define the class of vertices of the road network. 
 */
 
public class Vertex {
	public int id; // node id.
	
	/* The location of the node. */
	public	double lat; //latitude
	public	double lng; //longitude
	
	public static final int INVALID_ID = -1;

	public Vertex(int pid, double plat, double plng){
		this.id = pid;
		this.lat = plat;
		this.lng = plng;
	}
	
	public double getLat()
	{
		return this.lat;
	}
	
	public double getLng()
	{
		return this.lng;
	}
	
	public int getId()
	{
		return this.id;
	}
}
