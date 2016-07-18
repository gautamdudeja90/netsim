package com.feedzai.netsim.engine;

/**
 * Represents the path followed by a packet in the network.
 */
public class NetworkPath {

	private static int timeTaken; 
	
	
	

    /**
     * Time token to follow this path.
     * @return The time (ms)
     *
     */
    public void setTime(int timeTaken) {
    	this.timeTaken = timeTaken;
    	
    }
	
	
    /**
     * Time token to follow this path.
     * @return The time (ms)
     *
     */
    public int getTime() {
        return this.timeTaken;
    }
}