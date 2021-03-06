package com.itv.supermaket.entities;


import org.junit.Assert;
import org.junit.Test;


public class PriceRuleMultipleItemsTest {
	
	@Test
	public void testTotalPrice() {
		Item item = new Item("A", 32);
		PriceRule priceRule = new PriceRuleMultipleItems(item, 2, 60);
		
		Basket basket = new Basket();
		basket.addItem(item);
		basket.addItem(item);
		double total = priceRule.getTotalPriceItemFromPriceRule(basket, item);
		
		Assert.assertEquals(60, total, 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testNullPointerExceptionWhenBasketNull() {
		Item item = new Item("A", 32);
		PriceRule priceRule = new PriceRuleMultipleItems(item, 2, 60);
		priceRule.getTotalPriceItemFromPriceRule(null, item);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWhenItemIsNull() {
		new PriceRuleMultipleItems(null, 2, 60);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWhenThreasholdIsNegative() {
		Item item = new Item("A", 32);
		new PriceRuleMultipleItems(item, -2, 60);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWhenPriceIsNegative() {
		Item item = new Item("A", 32);
		new PriceRuleMultipleItems(item, 2, -60);
	}
	

}
