package greedLS;

import java.util.Vector;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;


/**
 * @author Ying Lu <ylu720@usc.edu>
 *
 * Define the class of graph, i.e., road network. 
 * Store graph in an adjacency list. 
 */
 
public class Graph {
	/* File name of the adjacency list */
	private String adjFile = null;
	
	/* File name of the nodes. */
	private String nodeFile = null;
	  
	/* Adjacency List */
	public AdjList adjList = null;
	
	/* Nodes of the graph. */
	public ArrayList<Vertex> vertices = null;
	
	
	/**
	 * Read vertices data from file
	 * 
	 * @param filename
	 * @throws IOException
	 */
	private void readVertices(String filename) throws IOException {
		vertices = new ArrayList<Vertex>(adjList.getSize());
	    List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
	    for (String line : lines) {
	    	String[] fields = line.split(",");
	    	if (fields.length == 3) {
	    		double lat = Double.parseDouble(fields[1]);
	    		double lng = Double.parseDouble(fields[2]);
	    		Vertex v = new Vertex(vertices.size(), lat, lng);
	    		vertices.add(v);
	    		}
	    	}
	    }
	  
	
	  private void init() {
		    try {
		    	adjList = new AdjList(adjFile, 0);
		    	readVertices(nodeFile);
		    } catch (IOException e) {
		      throw new ExceptionInInitializerError(e);
		    }
		}
	 
	  public Graph(String adjFile, String nodeFile) {
		    this.adjFile = adjFile;
		    this.nodeFile = nodeFile;
		    init();
		  }
	  
	  
	  
	  
}
