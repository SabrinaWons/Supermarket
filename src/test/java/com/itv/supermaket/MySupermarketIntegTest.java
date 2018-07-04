package com.itv.supermaket;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.itv.supermaket.dao.ItemDAO;
import com.itv.supermaket.dao.ItemDAOInMemory;
import com.itv.supermaket.dao.PriceRuleDAO;
import com.itv.supermaket.dao.PriceRuleDAOInMemory;
import com.itv.supermaket.entities.Basket;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;

public class MySupermarketIntegTest {
	
	@Test
	public void test() {
		ItemDAO itemDAO = ItemDAOInMemory.getInstance();
		PriceRuleDAO priceRuleDAO = PriceRuleDAOInMemory.getInstance();
		initDatabase(itemDAO, priceRuleDAO);
		
		MySupermarket supermarket = new MySupermarket(itemDAO, priceRuleDAO);
		
		Basket basket = new Basket();
		basket.addItem(supermarket.getItem("A"));
		basket.addItem(supermarket.getItem("B"));
		basket.addItem(supermarket.getItem("A"));
		
		
		Set<PriceRule> priceRules = supermarket.getCopyOfPriceRules(basket.getSetOfItems());
		basket.checkout(priceRules);
		double total = basket.getTotal();
		
		Assert.assertEquals(130.0, total, 0);
		
	}
	
	public static void initDatabase(ItemDAO itemDao, PriceRuleDAO priceRuleDao) {
		
		Item item1 = new Item("A", 50);
		Item item2 = new Item("B", 30);
		Item item3 = new Item("C", 20);
		Item item4 = new Item("D", 15);
		Item item5 = new Item("E", 0.3);
		
		itemDao.create(item1);
		itemDao.create(item2);
		itemDao.create(item3);
		itemDao.create(item4);
		itemDao.create(item5);
		
		
		
		PriceRule priceRule1 = new PriceRuleMultipleItems(item1, 3, 130);
		PriceRule priceRule2 = new PriceRuleMultipleItems(item2, 2, 45);
		
		priceRuleDao.createPriceRule(priceRule1);
		priceRuleDao.createPriceRule(priceRule2);
		
		
		
	}

}
