package com.itv.supermaket;

import java.util.HashSet;
import java.util.Set;

import com.itv.supermaket.dao.ItemDAO;
import com.itv.supermaket.dao.PriceRuleDAO;
import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;

public class MySupermarket {

	private ItemDAO itemDao;
	private PriceRuleDAO priceRuleDao;
	
	
	public MySupermarket(ItemDAO itemDAO, PriceRuleDAO priceRuleDAO) {
		this.itemDao = itemDAO;
		this.priceRuleDao = priceRuleDAO;
	}
	
	/**
	 * Returns the item
	 * @param name
	 * @return
	 */
	public Item getItem(String name) {
		return itemDao.getItem(name);
	}
	
	/**
	 * Returns an immutable copy of the price rules
	 * @param items
	 * @return
	 * @throws NullPointerException If items is null
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
	
	

}
