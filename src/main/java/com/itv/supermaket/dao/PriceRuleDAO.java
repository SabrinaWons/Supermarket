package com.itv.supermaket.dao;

import java.util.Set;

import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;

/**
 * The repository of PriceRules
 * 
 */
public interface PriceRuleDAO {
	
	/**
	 * Add a new price rule to the repository
	 * @param priceRule
	 * @return The newly created price rule
	 * @throws NullPointerException If priceRule is null
	 */
	public PriceRule createPriceRule(PriceRule priceRule);
	
	/**
	 * Returns the list of price rules associated to the set of items
	 * @throws NullPointerException If items is null
	 */
	public Set<PriceRule> getPriceRules(Set<Item> items);
	
	/**
	 * Utility method to copy a priceRule
	 * @param priceRule
	 * @return
	 */


}
