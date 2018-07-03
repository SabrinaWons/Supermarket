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
	 * Removes the price rule from the map
	 * @returns	true is the price rule has been deleted, false otherwise
	 * @throws NullPointerException If priceRule is null
	 */
	public boolean deletePriceRule(PriceRule priceRule);
	
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
	public static PriceRule copyPriceRule(PriceRule priceRule) {
		
		if(priceRule instanceof PriceRuleMultipleItems) {
			PriceRuleMultipleItems priceRuleMI = (PriceRuleMultipleItems) priceRule;
			PriceRuleMultipleItems pr = new PriceRuleMultipleItems(new Item(priceRule.getItem()), priceRuleMI.getThreshold(), priceRuleMI.getPrice());
			return pr;
		}
		return null;
	}

}
