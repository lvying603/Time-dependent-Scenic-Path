package greedLS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FindTDSP {
	
	  /**
	   * Class Node. Cost and ArrTime are used interchangablely, 
	   * I kept them for compatibility with
	   * Ugur's <demiryur@usc.edu> code dist is of no use.
	   * 
	   * @author Dingxiong <dengdx@gmail.com>
	   */
	  class TDSPNode implements Comparable<TDSPNode> {
	    TDSPNode(int id, int c, int t, int val) {
	      nodeId = id;
	      cost = c;
	      arrTime = t;
	      sofarValue = val;
	      // dist = new Vector(10, 5);
	    }

	    TDSPNode(int id, int t, int val) {
	      nodeId = id;
	      cost = t;
	      arrTime = t;
	      sofarValue = val;
	      // dist = new Vector(10, 5);
	    }

	    public int getNodeId() {
	      return nodeId;
	    }

	    public int getCost() {
	      return cost;
	    }

	    public int getArrTime() {
	      return arrTime;
	    }
	    
	    public int getSofarValue(){
	    	return sofarValue;
	    }

	    public void setArrTime(int t) {
	      arrTime = t;
	    }

	    public void setCost(int t) {
	      cost = t;
	    }
	    
	    public void setValue(int val) {
	    	sofarValue = val;
	    }

	    private int nodeId, cost, arrTime, sofarValue;

	    // private Vector dist; // denoted by 'c' in the algorithm

	    @Override
	    public int compareTo(TDSPNode o) {
	      // TODO Auto-generated method stub
	      return this.cost - o.cost;
	    }

	  }
	  
	  
	  
	  
	/**
	   * This tdsp use an array visited[] to indicate whether a node 
	   * has been visited so that we do not need to scan the priority 
	   * queue before updating it.
	   * 
	   * @modified Yaguang Li <yaguang@usc.edu>
	   * 
	   * @param startId start node ID
	   * @param endId end node ID
	   * @param startTime milliseconds from 00:00
	   * @return
	   */
	  public Gap tdsp(int startId, int endId, int startTime) {
	    HashSet<Integer> endIds = new HashSet<Integer>();
	    endIds.add(endId);
	    return tdsp(startId, endIds, startTime);
	  }

	  /**
	   * Finds the shortest path from startId to a set of endIds
	   * 
	   * @param startId start node id
	   * @param endIds a set of end node ids
	   * @param startTime milliseconds from 00:00
	   * @return
	   */
	  public Gap tdsp(int startId, HashSet<Integer> endIds, int startTime) {
	    int curTime = startTime;
	    int curValue = 0;
	    int len = GreedLS.graph.vertices.size();
	    int[] arrivalTime = new int[len];
	    int[] totalCollectedValues = new int[len];
	    int[] parent = new int[len];
	    boolean[] visited = new boolean[len];

	    int endId = Vertex.INVALID_ID;
	    PriorityQueue<TDSPNode> priorityQ = new PriorityQueue<TDSPNode>(20);

	    // initialize visited node, parent and arrival time of each node
	    for (int i = 0; i < len; i++) {
	      visited[i] = false;
	      parent[i] = -1;
	      arrivalTime[i] = Integer.MAX_VALUE; // indicating infinity
	      totalCollectedValues[i] = 0;
	    }
	    arrivalTime[startId] = startTime;
	    totalCollectedValues[startId] = 0;

	    // creating the starting node with nodeId = start and
	    // arrival time = time
	    TDSPNode curNode, s = new TDSPNode(startId, curTime, curValue);
	    // inserting s into the priority queue
	    priorityQ.offer(s);
	    while ((curNode = priorityQ.poll()) != null) {
	      int curId = curNode.getNodeId();
	      // using visited vector
	      if (visited[curId])
	        continue;
	      visited[curId] = true;
	      if (endIds.contains(curId)) {
	        endId = curId;
	        break;
	      }
	      Map<Integer, Pair<Integer,Integer>> neighbors = 
		                                   GreedLS.graph.adjList.getList().get(curId);
	      if (neighbors == null)
	        continue;
	      for (Map.Entry<Integer, Pair<Integer,Integer>> pair : neighbors.entrySet()) {
	        int node = pair.getKey();
	        int curInterval = GreedLS.TimeCost2Idx(curTime);
	        int travelTime = pair.getValue().getLeft();
	        int gainValue = pair.getValue().getRight();
	        /**
	         * if the node is visited, we bypass it if we find a node with updated distance, 
			 * just insert it to the priority queue even we pop out another node with 
			 * same id later, we know that it was visited and will ignore it. 
	         */
	        if (visited[node] == true)
	          continue;
	        if (curNode.getArrTime() + travelTime < arrivalTime[node]) {
	          arrivalTime[node] = curNode.getArrTime() + travelTime;
	          totalCollectedValues[node] = curNode.getSofarValue() + gainValue;
	          parent[node] = curId;
	          priorityQ.offer(new TDSPNode(node, arrivalTime[node], totalCollectedValues[node]));
	        }
	      }
	    }

	    // retrieve the path in the array of nextNode
	    Gap result = new Gap();
	    result.start = startId;
	    result.end = endId;
	    if (endId != Vertex.INVALID_ID && arrivalTime[endId] < Integer.MAX_VALUE) {
	      int curId = endId;
	      int totalCost = arrivalTime[endId] - startTime;
	      int collectedValue = totalCollectedValues[endId];
	      while (curId != -1) {
	        result.vexIDList.add(curId);
	        curId = parent[curId];
	      }
	      Collections.reverse(result.vexIDList);
	      result.SPCost = totalCost;
	      result.collectedValue = collectedValue;
	      result.actualStarttime = arrivalTime[endId];
	    } else {
	      // unreachable
	      result.SPCost = Integer.MAX_VALUE;
	      result.collectedValue = 0;
	    }
	    return result;
	  }
	  
	  
	  
	  
	  //---used for GreedLS Algorithm
	  /**
	   * Finds the shortest path from startId to a set of endIds
	   * 
	   *@modified Ying Lu <ylu720@usc.edu>
	   *
	   * @param startId start node id
	   * @param endIds a set of end node ids
	   * @param startTime milliseconds from 00:00
	   * @return
	   */
	  public Map<Integer,Gap> tdspGreedLS(int startId, 
	                                      Map<Integer,Pair<Integer,Integer>> endIds, 
										  int startTime, int budget) {
	    int curTime = startTime;
	    int curValue = 0;
	    int len = GreedLS.graph.vertices.size();
	    int[] arrivalTime = new int[len];
	    int[] totalCollectedValues = new int[len];
	    int[] parent = new int[len];
	    boolean[] visited = new boolean[len];
	    Map<Integer,Gap> Results = new HashMap<Integer,Gap>();

	    int endId = Vertex.INVALID_ID;
	    PriorityQueue<TDSPNode> priorityQ = new PriorityQueue<TDSPNode>(20);

	    // initialize visited node, parent and arrival time of each node
	    for (int i = 0; i < len; i++) {
	      visited[i] = false;
	      parent[i] = -1;
	      arrivalTime[i] = Integer.MAX_VALUE; // indicating infinity
	      totalCollectedValues[i] = 0;
	    }
	    arrivalTime[startId] = startTime;
	    totalCollectedValues[startId] = 0;

	    // creating the starting node with nodeId = start and
	    // arrival time = time
	    TDSPNode curNode, s = new TDSPNode(startId, curTime, curValue);
	    // inserting s into the priority queue
	    priorityQ.offer(s);
	    while ((curNode = priorityQ.poll()) != null) {
	      int curId = curNode.getNodeId();
	      // using visited vector
	      if (visited[curId])
	        continue;
	      visited[curId] = true;
	      if (endIds.containsKey(curId)) {
	        endId = curId;
	        
	        //--- retrieve the path in the array of nextNode
		    Gap result = new Gap();
		    result.start = startId;
		    result.end = endId;
		    if (endId != Vertex.INVALID_ID && arrivalTime[endId] < Integer.MAX_VALUE) {
		      int resCurId = endId;
		      int totalCost = arrivalTime[endId] - startTime;
		      int collectedValue = totalCollectedValues[endId];
		      while (resCurId != -1) {
		        result.vexIDList.add(resCurId);
		        resCurId = parent[resCurId];
		      }
		      Collections.reverse(result.vexIDList);
		      result.SPCost = totalCost;
		      result.collectedValue = collectedValue;
		      result.actualStarttime = arrivalTime[endId];
		    } else {
		      // unreachable
		      result.SPCost = Integer.MAX_VALUE;
		      result.collectedValue = 0;
		    }
		    if(result.SPCost<=budget){
		    	Results.put(endId, result);
		    }
	        //break;
	      }
	      Map<Integer, Pair<Integer,Integer>> neighbors = GreedLS.graph.adjList.getList().get(curId);
	      if (neighbors == null)
	        continue;
	      for (Map.Entry<Integer, Pair<Integer,Integer>> pair : neighbors.entrySet()) {
	        int node = pair.getKey();
	        int curInterval = GreedLS.TimeCost2Idx(curTime);
	        int travelTime = pair.getValue().getLeft();
	        int gainValue = pair.getValue().getRight();
	        /*
	         * if the node is visited, we bypass it if we find a node with updated distance, just insert
	         * it to the priority queue even we pop out another node with same id later, we know that it
	         * was visited and will ignore it
	         */
	        if (visited[node] == true)
	          continue;
	        if (curNode.getArrTime() + travelTime < arrivalTime[node]) {
	          arrivalTime[node] = curNode.getArrTime() + travelTime;
	          totalCollectedValues[node] = curNode.getSofarValue() + gainValue;
	          parent[node] = curId;
	          priorityQ.offer(new TDSPNode(node, arrivalTime[node], totalCollectedValues[node]));
	        }
	      }
	    }

	    
	    return Results;
	  }
	  
	  
	  

}
