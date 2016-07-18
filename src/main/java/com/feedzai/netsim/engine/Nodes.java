package com.feedzai.netsim.engine;

import java.util.*;

/**
 * Represents a Nodes in a directed graph.
 */
public class Nodes implements Comparable<Nodes> {

	public static final int INFINITY = Integer.MAX_VALUE;

  public String name;          // Vertex identifier.
  public boolean visited;      // Has vertex been visited?
  public List<NodeConnection> adj;       // Adjacent vertice edges.
  public Nodes prev;          // Previous vertex on shortest path.
  public int dist;           // Path distance.
  public boolean status;       // true = "up", false = "down."

  /**
   * Constructor
   * Sets the Node's name and instantiates as new Linked List for it's adjacent Nodes.
   */
  public Nodes(String name) {
    this.name = name;
    adj = new LinkedList<NodeConnection>();
    reset();
  }

  /**
   * Resets the Nodes
   */
  public void reset() {
    dist = Nodes.INFINITY;
    visited = false;
    prev = null;
    status = true;
  }

  public int compareTo(Nodes other) {
    if(dist < other.dist) {
      return -1;
    }

    if(dist > other.dist) {
      return 1;
    }

    return 0;
  }


}
