package com.itv.supermaket.manager;

import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;
import com.itv.supermaket.entities.PriceRuleMultipleItems;

public class PriceRuleManager {

	public PriceRule copyPriceRule(PriceRule priceRule) {
		
		if(priceRule instanceof PriceRuleMultipleItems) {
			PriceRuleMultipleItems priceRuleMI = (PriceRuleMultipleItems) priceRule;
			PriceRuleMultipleItems pr = new PriceRuleMultipleItems(new Item(priceRule.getItem()), priceRuleMI.getThreshold(), priceRuleMI.getPrice());
			return pr;
		}
		return null;
	}
}
