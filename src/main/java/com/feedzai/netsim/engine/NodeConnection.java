package com.feedzai.netsim.engine;

public class NodeConnection {

	public static final int INFINITY = Integer.MAX_VALUE;

	public Nodes dest;
	public int dist;
	public boolean status; // true = "up", false = "down."

	public NodeConnection(Nodes dest, int dist) {
		this.dest = dest;
		this.dist = dist;
		this.status = true;
	}
}