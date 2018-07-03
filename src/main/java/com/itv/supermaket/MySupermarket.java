package com.itv.supermaket;

import java.util.HashSet;
import java.util.Set;

import com.itv.supermaket.dao.ItemDAO;
import com.itv.supermaket.dao.PriceRuleDAO;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;

public class MySupermarket {

	private ItemDAO itemDao;
	private PriceRuleDAO priceRuleDao;
	
	
	public MySupermarket(ItemDAO itemDAO, PriceRuleDAO priceRuleDAO) {
		this.itemDao = itemDAO;
		this.priceRuleDao = priceRuleDAO;
	}
	
	public void setItemDAO(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setPriceRuleDAO(PriceRuleDAO priceRuleDao) {
		this.priceRuleDao = priceRuleDao;
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
		
		
		Set<PriceRule> result = new HashSet<>();
		for(PriceRule priceRule : priceRules) {
			PriceRule pr = PriceRuleDAO.copyPriceRule(priceRule);
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
