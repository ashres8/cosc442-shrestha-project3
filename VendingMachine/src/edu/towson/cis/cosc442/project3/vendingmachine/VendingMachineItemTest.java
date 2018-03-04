package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {
	VendingMachineItem chips_vi, soda_vi, ex_vi;

	@Before
	public void setUp() throws Exception {
		chips_vi = new VendingMachineItem("Chips", 2.99);
	}

	@After
	public void tearDown() throws Exception {
		chips_vi = null;
		soda_vi = null;
	}

	@Test
	public void testVendingMachineItem() {
		soda_vi = new VendingMachineItem("Soda", 2.66);	
		assertEquals(2.66, soda_vi.getPrice(), 0.001);
	}
	
	@Test
	public void testVendingMachineItemException() {
		try {
			ex_vi = new VendingMachineItem("Soda", -1.00);
	        fail("Expected an VendingMachineException to be thrown");
	    } catch (VendingMachineException e) {
	    	assertEquals(e.getMessage(), "Price cannot be less than zero");
	    }
	}

	@Test
	public void testGetName() {
		assertEquals("Chips", chips_vi.getName());
	}

	@Test
	public void testGetPrice() {
		assertEquals(2.99, chips_vi.getPrice(), 0.001);
	}

}
