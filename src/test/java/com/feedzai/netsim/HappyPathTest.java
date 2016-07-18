package com.feedzai.netsim;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.feedzai.netsim.engine.Network;
import com.feedzai.netsim.engine.NetworkPath;

public class HappyPathTest {

	@Test
	public void HappyTest() {
		
		Network net = Network.createWithLatency(1);

        // Interconnect network elements
        net.connect("A", "D");                // Uses default network latency
        net.connect("B", "D");
        net.connect("C", "E");
        net.connect("I", "G");
        net.connect("F", "J");
        net.connect("K", "H", 10);            // Connect K computer to H router with a 10ms latency
        net.connect("D", "E", 3);             // D to E has a 3ms latency
        net.connect("D", "F", 2);             // D to F has a 2ms latency
        net.connect("E", "F", 4);             // E to F has a 4ms latency
        net.connect("E", "G", 5);             // E to G has a 5ms latency
        net.connect("G", "F", 3);             // G to F has a 3ms latency
        net.connect("F", "H", 5);             // F to H has a 5ms latency

        // Simulate sending a packet from "C" to "J"
        NetworkPath path = net.sendPacket("C", "J");

        // Print out the network path and how much time it took to send the packet
        System.out.println( path );
        System.out.println( path.getTime() );
		assertEquals(path.getTime(),6);
		
	}

}
