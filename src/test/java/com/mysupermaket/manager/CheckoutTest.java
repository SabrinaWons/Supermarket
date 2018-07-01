package com.mysupermaket.manager;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.mysupermaket.entities.Basket;
import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;
import com.mysupermaket.entities.PriceRuleMultipleItems;


public class CheckoutTest {
	
	Checkout checkout = new Checkout();
	Basket basket = new Basket();
	Set<PriceRule> priceRules = new HashSet<>();
	
	@Test
	public void testDoCheckoutWhenEmpty() {
		
		checkout.doCheckout(basket, priceRules);
		Assert.assertEquals(0, basket.getTotal(), 0);
		
	}
	
	@Test
	public void testDoCheckoutWithNoPriceRules() {
		Item itemA = new Item("A", 2);
		Item itemB = new Item("B", 5);
		
		basket.addItem(itemA);
		basket.addItem(itemB);
		
		checkout.doCheckout(basket, priceRules);
		Assert.assertEquals(7, basket.getTotal(), 0);
		
	}
	
	@Test
	public void testDoCheckoutWithNoPriceRules2() {
		Item itemA = new Item("A", 0.3);
		
		basket.addItem(itemA);
		basket.addItem(itemA);
		basket.addItem(itemA);
		
		
		checkout.doCheckout(basket, priceRules);
		Assert.assertEquals(0.9, basket.getTotal(), 0);
		
	}
	
	@Test
	public void testDoCheckout() {
		Item itemA = new Item("A", 2);
		Item itemB = new Item("B", 5);
		
		basket.addItem(itemA);
		basket.addItem(itemB);
		basket.addItem(itemA);
		basket.addItem(itemB);
		
		Set<PriceRule> priceRules = new HashSet<>();
		PriceRule priceRuleA = new PriceRuleMultipleItems(itemA, 2, 3);
		PriceRule priceRuleB = new PriceRuleMultipleItems(itemB, 3, 10);
		priceRules.add(priceRuleA);
		priceRules.add(priceRuleB);
		
		
		checkout.doCheckout(basket, priceRules);
		Assert.assertEquals(13, basket.getTotal(), 0);
		
		
	}
	
	@Test
	public void testDoCheckoutWhenSeveralPriceRules() {
		Item itemA = new Item("A", 10);
		
		basket.addItem(itemA);
		basket.addItem(itemA);
		basket.addItem(itemA);
		basket.addItem(itemA);
		
		Set<PriceRule> priceRules = new HashSet<>();
		PriceRule priceRuleA = new PriceRuleMultipleItems(itemA, 2, 18);
		PriceRule priceRuleB = new PriceRuleMultipleItems(itemA, 3, 27);
		priceRules.add(priceRuleA);
		priceRules.add(priceRuleB);
		
		checkout.doCheckout(basket, priceRules);
		Assert.assertEquals(36, basket.getTotal(), 0);
		
	}

}
