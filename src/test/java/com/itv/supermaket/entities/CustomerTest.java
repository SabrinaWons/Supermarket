package com.mysupermaket.main;

import org.junit.Assert;
import org.junit.Test;

import com.mysupermaket.entities.Customer;
import com.mysupermaket.entities.Item;


public class CustomerTest {
	@Test
	public void testCheckout() {
		Customer customer = new Customer();
		
		Item item1 = new Item("A", 32);
		Item item2 = new Item("B", 11);
		
		customer.buyItem(item1);
		customer.buyItem(item2);
		
		double total = customer.checkout();
		Assert.assertEquals(43, total, 0);
		
		
	}
	
	@Test
	public void testCheckoutWhenBasketEmpty() {
		Customer customer = new Customer();
		double total = customer.checkout();
		Assert.assertEquals(0, total, 0);
		
	}
}

