package com.feedzai.netsim.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Represents the overall computer network.
 */
public class Network {

	public static Map<String,Nodes> NetworkMap;
	public static int defaultTimeLatency = 0;


	public static Network createWithLatency(int latency) {
		Network net = new Network(latency);
		return net;
		
	}
	
	public Network(int timeLetency){
		NetworkMap = new TreeMap<String,Nodes>();
		defaultTimeLatency = timeLetency; 
		
	}
	
	public void connect(String idA, String idB) {


		addNode(idA, idB, 1);

	}

	public int connect(String idA, String idB, int latency) {

		if(latency <= 0) {
			System.out.println("Latency time between network nodes must be greator than zero!\nNo Connection created.");
			return -1;
		}	
		addNode(idA,idB, latency);
		return 0;

	}

	public NetworkPath sendPacket(String idA, String idB) {
		NetworkPath netpath = new NetworkPath();
		Nodes sourceNode = getNodes(idA, true);
		Nodes destinationNode = getNodes(idB, true);

		List<String> list = dijkstra(sourceNode,destinationNode);

		

	    netpath.setTime(Integer.valueOf(list.get(list.size() -1)));
		return netpath;
	}

	/**
	 * Adds a new Node to the graph.
	 */
	public int addNode(String sourceName, String destName, int timeLatency) {

		// Get (or create, if null) the Node with name sourceName
		Nodes v = getNodes(sourceName);

		// Get (or create, if null) the Node with name destName
		Nodes w = getNodes(destName);

		NodeConnection e = getNodeConnection(sourceName, destName);
		if(getNodeConnection(sourceName, destName) == null) {
			// Create the connection from sourceName => destName, with timeLatency.
			e = new NodeConnection(w, timeLatency);

			// Add the connection to the source's adjacency list.
			v.adj.add(e);

			// Signal that we created a *new* NodeConnection.
			return 1;
		}
		else {
			e.dist = timeLatency;

			// Signal that we just modified a *current* NodeConnection.
			return 0;
		}

	} 

	/**
	 * If Node name is not present, add it to the NetworkMap.
	 * In either case, return the Node object.
	 */
	public Nodes getNodes(String nodeName) {
		// Attempt to grab the Node from the list of vertices.
		nodeName = nodeName.toLowerCase();
		Nodes v = NetworkMap.get(nodeName);

		// Create a new Node, if Node NodeName does not exist.
		if(v == null) {

			v = new Nodes(nodeName);
			NetworkMap.put(nodeName, v);

			nodeName = nodeName.substring(0, 1).toUpperCase() + nodeName.substring(1);
		}

		return v;

	}


	/**
	 * Override, that does not create a new Node if Node is not found.
	 */
	public Nodes getNodes(String nodeName, boolean noCreation) {

		if(noCreation == false) {
			Nodes v = getNodes(nodeName);
			return v;
		}

		// Attempt to grab the Node from the list of Nodes.
		nodeName = nodeName.toLowerCase();
		Nodes v = NetworkMap.get(nodeName);

		return v;

	}


	/**
	 * Toggles an NodeConnection's up/down status.
	 */
	public NodeConnection getNodeConnection(String source, String dest) {

		Nodes v = getNodes(source, true);
		Nodes w = getNodes(dest, true);

		if(v == null || w == null) { return null; }

		for(NodeConnection e : v.adj) {
			if(e.dest.name.equals(dest)) {
				return e;
			}
		}

		return null;

	}

	public List<String> dijkstra(Nodes source, Nodes dest) {

		PriorityQueue<Nodes> q = new PriorityQueue<Nodes>();
		List<String> paths = new ArrayList<String>();

		int finalMin=0;
		for(Nodes v : NetworkMap.values()) {
			v.dist = NodeConnection.INFINITY;
			v.visited = false;
			v.prev = null;
		}

		source.dist = 0;
		q.add(source);

		while(!q.isEmpty()) {

			// Find Node in q with shortest distance.
			Nodes u = source;
			int min = Nodes.INFINITY;
			for(Nodes s : q) {
				if(s.dist <= min && s.status == true && s.visited == false) { // Find the Node in q with the minimum distance from u and that is "up."
					min = s.dist;
					u = s;
				}
			}

			if(u.name.equals(dest.name)) { break; }

			q.remove();
			u.visited = true;

			for(NodeConnection e : u.adj) {
				if(e.status != false) {
					int alt = u.dist + e.dist;
					finalMin = alt;
					if(alt < e.dest.dist && e.dest.visited == false) {
						e.dest.dist = alt;
						e.dest.prev = u;
						q.add(e.dest);
					}
				}
			}

		} // End of while loop

		// Prepend each Node's name to the path list.
		Nodes k = dest;
		while(k.prev != null) {
			paths.add(0, k.name);
			k = k.prev;
		}

		// Prepend source to path list.
		paths.add(0, k.name);

		Nodes x = getNodes(dest.name, true);

		// Tack on the distance.
		paths.add(Integer.toString(x.dist));

		return paths;

	} // End of dijkstra method.



} // End of Network class.




