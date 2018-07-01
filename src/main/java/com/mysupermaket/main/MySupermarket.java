package com.mysupermaket.main;

import java.util.HashSet;
import java.util.Set;

import com.mysupermaket.dao.ItemDAO;
import com.mysupermaket.dao.ItemDAOInMemory;
import com.mysupermaket.dao.PriceRuleDAO;
import com.mysupermaket.dao.PriceRuleDAOInMemory;
import com.mysupermaket.manager.PriceRuleManager;
import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;
import com.mysupermaket.entities.PriceRuleMultipleItems;

public class MySupermarket {

	private ItemDAO itemDao = ItemDAOInMemory.getInstance();
	private PriceRuleDAO priceRuleDao = PriceRuleDAOInMemory.getInstance();
	
	private static MySupermarket instance = new MySupermarket();
	private MySupermarket() {}
	
	public static MySupermarket getInstance() {
		return instance;
	}
	
	public void setItemDAO(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	
	public Item getItem(String name) {
		return itemDao.getItem(name);
	}
	
	/**
	 * Returns an immutable copy of the price rules
	 * @param items
	 * @return
	 */
	public Set<PriceRule> getCopyOfPriceRules(Set<Item> items) {
		Set<PriceRule> priceRules = priceRuleDao.getPriceRules(items);
		PriceRuleManager prm = new PriceRuleManager();
		
		Set<PriceRule> result = new HashSet<>();
		for(PriceRule priceRule : priceRules) {
			PriceRule pr = prm.copyPriceRule(priceRule);
			result.add(pr);
			
		}
		
		return result;
				
	}
	
	public void initDatabase() {
		
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
