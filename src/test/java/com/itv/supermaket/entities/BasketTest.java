package com.itv.supermaket.entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itv.supermaket.entities.Basket;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;

public class BasketTest {

	@Test
	public void testGetTotal() {
		Basket basket = new Basket();
		basket.addItem(new Item("A", 23));
		basket.addItem(new Item("B", 11));
		
		basket.checkout(new HashSet<>());
		Assert.assertEquals(34, basket.getTotal(), 0);
		
	}
	
	@Test
	public void testGetTotalWhenEmpty() {
		Basket basket = new Basket();
		
		basket.checkout(new HashSet<>());
		Assert.assertEquals(0, basket.getTotal(), 0);
		
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
		
		basket.checkout(priceRules);
		
		Assert.assertEquals(41, basket.getTotal(), 0);
		
	}

}
