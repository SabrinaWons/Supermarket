package com.itv.supermaket.manager;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itv.supermaket.entities.Basket;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;
import com.itv.supermaket.manager.Checkout;


public class CheckoutTest {
	
	Checkout checkout = new Checkout();
	Basket basket = new Basket();
	Set<PriceRule> priceRules = new HashSet<>();
	
	@Test
	public void testDoCheckoutWhenEmpty() {
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(0, total, 0);
		
	}
	
	@Test
	public void testDoCheckoutWithNoPriceRules() {
		Item itemA = new Item("A", 2);
		Item itemB = new Item("B", 5);
		
		basket.addItem(itemA);
		basket.addItem(itemB);
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(7, total, 0);
		
	}
	
	@Test
	public void testDoCheckoutWithNoPriceRules2() {
		Item itemA = new Item("A", 0.3);
		
		basket.addItem(itemA);
		basket.addItem(itemA);
		basket.addItem(itemA);
		
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(0.9, total, 0);
		
	}
	
	@Test
	public void testDoCheckoutWithNoPriceRules3() {
		Item itemA = new Item("A", 5.1);
		Item itemB = new Item("B", 0.1);
		
		basket.addItem(itemA);
		basket.addItem(itemB);
		
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(5.2, total, 0);
		
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
		
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(13, total, 0);
		
		
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
		
		double total = checkout.computeCheckout(basket, priceRules);
		Assert.assertEquals(36, total, 0);
		
	}

}
