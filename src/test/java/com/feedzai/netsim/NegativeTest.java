package com.feedzai.netsim;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feedzai.netsim.engine.Network;

public class NegativeTest {

	@Test
	public void LeastLatencyTest() {

		Network net = Network.createWithLatency(1);
		int a = net.connect("K", "H", 0);
		            // Connect K computer to H router with a 10ms latency
		assertEquals("-1", Integer.toString(a));
	}
	
}
