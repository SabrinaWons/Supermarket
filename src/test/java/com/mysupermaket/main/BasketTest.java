package com.mysupermaket.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.mysupermaket.entities.Basket;
import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;
import com.mysupermaket.entities.PriceRuleMultipleItems;

public class BasketTest {

	@Test
	public void testGetTotal() {
		Basket basket = new Basket();
		basket.addItem(new Item("A", 23));
		basket.addItem(new Item("B", 11));
		
		double total = basket.getTotal();
		Assert.assertEquals(34, total, 0);
		
	}
	
	@Test
	public void testGetTotalWhenEmpty() {
		Basket basket = new Basket();
		
		double total = basket.getTotal();
		Assert.assertEquals(0, total, 0);
		
	}
	
	@Test
	public void testGetTotalWithPriceRules() {
		Item itemA = new Item("A", 23);
		Item itemB = new Item("B", 11);
		
		
		Basket basket = new Basket();
		basket.addItem(itemA);
		basket.addItem(itemB);
		basket.addItem(itemA);
		
		PriceRule rule = new PriceRuleMultipleItems(itemA, 2, 30);
		Set<PriceRule> priceRules = new HashSet<>(Arrays.asList(rule));
		
		
		double total = basket.getTotal(priceRules);
		
		Assert.assertEquals(41, total, 0);
		
	}
}
