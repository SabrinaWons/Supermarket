package com.itv.supermaket.entities;

import org.junit.Test;

public class ItemTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWhenSkuIsNullThenThrowsException() {
		new Item(null, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWhenPriceIsNegativeThenThrowsException() {
		new Item("", -1);
	}

}
