package com.mysupermaket.manager;

import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;
import com.mysupermaket.entities.PriceRuleMultipleItems;

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
