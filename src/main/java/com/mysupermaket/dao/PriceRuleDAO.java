package com.mysupermaket.dao;

import java.util.Set;

import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;

public interface PriceRuleDAO {
	
	public PriceRule createPriceRule(PriceRule priceRule);
	public Set<PriceRule> getPriceRules(Set<Item> items);

}
