/**
 * 
 */
package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asims
 *
 */
public class VendingMachineTest {
	VendingMachine vm;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		vm = new VendingMachine();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		vm = null;
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * Creates a new VendingMachineItem then gets the Item for the Checking if it exists in the
	 * Vending Machine 
	 * 
	 */
	@Test
	public void testAddItem() {
		VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
		vm.addItem(vi1, VendingMachine.A_CODE);
		assertEquals(vm.getItem(VendingMachine.A_CODE), vi1);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * Creates a new VendingMachineItem then gets the Item for the Checking if it exists in the
	 * Vending Machine 
	 * 
	 */
	@Test
	public void testAddItem_With_Occupied() {
		try{
			VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
			VendingMachineItem vi2 = new VendingMachineItem("Chip2", 5.00);
			vm.addItem(vi1, VendingMachine.A_CODE);
			vm.addItem(vi2, VendingMachine.A_CODE);
			assertEquals(vm.getItem(VendingMachine.A_CODE), vi1);
			assertEquals(vm.getItem(VendingMachine.A_CODE), vi2);
	    } catch (VendingMachineException e) {
	    	assertEquals(e.getMessage(), "Slot " + VendingMachine.A_CODE + " already occupied");
	    }
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * This tests if it is possible to add an Item to the VendingMachine even if the Code of the
	 * Vending Machine doesn't exists
	 */
	@Test
	public void testAddItem_Wrong_Code() {
		try{
			VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
			vm.addItem(vi1, "Y");
			assertEquals(vm.getItem("Y"), vi1);
	    } catch (VendingMachineException e) {
	    	assertEquals(e.getMessage(), "Invalid code for vending machine item");
	    }
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#removeItem(java.lang.String)}.
	 * This test the remove item Method for the Vending Machine. This Uses the correct Vending
	 * Machine Code and Item
	 */
	@Test
	public void testRemoveItem() {
		VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
		vm.addItem(vi1, VendingMachine.B_CODE);
		assertEquals(vm.removeItem(VendingMachine.B_CODE), vi1);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#removeItem(java.lang.String)}.
	 * Trying to remove an Item from the Vending Machine with out having any Items inserted to the Machine
	 * 
	 */
	@Test
	public void testRemoveItem_Without_Adding() {
		try{
			assertEquals(vm.removeItem(VendingMachine.C_CODE), null);
	    } catch (VendingMachineException e) {
	    	assertEquals(e.getMessage(), "Slot " + VendingMachine.C_CODE + " is empty -- cannot remove item");
	    }
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#insertMoney(double)}.
	 * This method tests for weather the money inserted is valid or not
	 * 
	 */
	@Test
	public void testInsertMoney() {
		vm.insertMoney(5.00);
		assertEquals(5.00, vm.getBalance(), 0.0001);
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#insertMoney(double)}.
	 * Tests for the invalid amount of money that was inserted.
	 */
	@Test
	public void testInsertMoney_Less_Than_Zero() {
		try{
			vm.insertMoney(-1.00);
			assertEquals(-1.00, vm.getBalance(), 0.0001);
		} catch (VendingMachineException e) {
	    	assertEquals(e.getMessage(), "Invalid amount.  Amount must be >= 0");
	    }
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#getBalance()}.
	 * 
	 * Checks for the Correct amount of balance that is currently in the Vending Machine.
	 */
	@Test
	public void testGetBalance() {
		vm.insertMoney(5.00);
		assertEquals(5.00, vm.getBalance(), 0.0001);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * Tests for Making a Purchase with the Vending Machine Code having an item and having enough money.
	 * 
	 */
	@Test
	public void testMakePurchase() {
		VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
		vm.addItem(vi1, VendingMachine.D_CODE);
		vm.insertMoney(6.00);
		assertEquals(true, vm.makePurchase(VendingMachine.D_CODE));
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * 
	 * Testing Make Purchase without any Item in the Vending Machine
	 */
	@Test
	public void testMakePurchase_No_Item() {
		vm.insertMoney(6.00);
		assertEquals(false, vm.makePurchase(VendingMachine.A_CODE));
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * Testing Make Purchase without any Money in the Vending Machine
	 */
	@Test
	public void testMakePurchase_Not_Enough_Money() {
		VendingMachineItem vi1 = new VendingMachineItem("Chip", 5.00);
		vm.addItem(vi1, VendingMachine.A_CODE);
		vm.insertMoney(2.00);
		assertEquals(false, vm.makePurchase(VendingMachine.A_CODE));
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#returnChange()}.
	 * 
	 * Checks if the Vending Machine would return the balance that is in the system
	 */
	@Test
	public void testReturnChange() {
		vm.insertMoney(2.00);
		assertEquals(2.00, vm.returnChange(), 0.0001);
	}

}
