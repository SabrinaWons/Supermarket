package com.mysupermaket.dao;

import java.util.Set;

import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;

/**
 * The repository of PriceRules
 * 
 */
public interface PriceRuleDAO {
	
	public PriceRule createPriceRule(PriceRule priceRule);
	public boolean deletePriceRule(PriceRule priceRule);
	public Set<PriceRule> getPriceRules(Set<Item> items);

}
