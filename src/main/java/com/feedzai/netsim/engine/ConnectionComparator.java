package com.feedzai.netsim.engine;


import java.util.*;

public class ConnectionComparator implements Comparator<NodeConnection> {
	    public int compare(NodeConnection first, NodeConnection second) {
	        return first.dest.name.compareTo(second.dest.name);
	    }
	}