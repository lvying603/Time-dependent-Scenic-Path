Inputs:
1) road network:
   Nodes: GreedLS/Graph/LAStaticData-Nodes.csv
          Each of line is corresponding to a node, 
		  in form of "node ID, node latitude, node longitude"
		  
   Arcs: GreedLS/Graph/sampled-LAStaticData-Arcs.txt
         Each of line is corresponding to an arc, 
		 in form of "source node ID, 
		             target node ID: arc travel cost / time (in millisecond unit), 
					 arc value"
   
2) GreedLS/Graph/query.txt
   Query parameters, in form of "query source node ID, 
                                 query target node ID, 
								 budget of travel time (in millisecond unit), 
								 algorithm running time threshold (in second unit)"
   
   
   
Output:
	GreedLS/Graph/output.txt