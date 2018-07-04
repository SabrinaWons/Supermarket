package com.itv.supermaket;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.itv.supermaket.dao.ItemDAO;
import com.itv.supermaket.dao.PriceRuleDAO;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;


public class MySupermarketTest {
	
	ItemDAO itemDAO = Mockito.mock(ItemDAO.class);
	PriceRuleDAO priceRuleDAO = Mockito.mock(PriceRuleDAO.class);
	MySupermarket supermarket = new MySupermarket(itemDAO, priceRuleDAO);
	
	@Test
	public void testGetItem() {
		
		// Mock the service
		Mockito.when(itemDAO.getItem("A")).thenReturn(new Item("A", 7));
		
		// Method to test
		Item item = supermarket.getItem("A");
		
		// Check the result
		Assert.assertNotNull(item);
		Assert.assertEquals(item.getSku(), "A");
		Assert.assertEquals(item.getPrice(), 7, 0);
		
	}
	
	@Test
	public void testGetItemReturnsNullWhenAbsent() {
		// Method to test
		Item item = supermarket.getItem("");
		// Check the result
		Assert.assertNull(item);
	}

	@Test
	public void testGetItemReturnsNullWhenNameIsNull() {
		// Method to test
		Item item = supermarket.getItem(null);
		// Check the result
		Assert.assertNull(item);
	}
	
	@Test	
	public void testGetCopyOfPriceRules() {
		// Prepare the test
		Set<Item> items = new HashSet<>();
		Item item = new Item("A", 50);
		Set<PriceRule> priceRules = new HashSet<>();
		PriceRule priceRule = new PriceRuleMultipleItems(item, 3, 130);
		priceRules.add(priceRule);
		Mockito.when(priceRuleDAO.getPriceRules(items)).thenReturn(priceRules);
		
		// Method to test
		Set<PriceRule> priceRulesActual = supermarket.getCopyOfPriceRules(items);
		
		Assert.assertEquals(priceRulesActual.size(), 1);
		
		PriceRule priceRuleActual = priceRulesActual.stream().findFirst().get();
		Assert.assertEquals(priceRule, priceRuleActual);
		
		priceRule.setId(2);
		Assert.assertNotEquals(priceRule.getId(), priceRuleActual.getId());
		
		
	}

}
