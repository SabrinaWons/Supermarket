package com.mysupermaket.manager;

import java.math.BigDecimal;
import java.util.Set;

import com.mysupermaket.entities.Basket;
import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;

public class Checkout {
	
	/**
	 * Updates the total of the given basket, following a set of price rules
	 * @param basket
	 * @param priceRules
	 */
	public void doCheckout(Basket basket, Set<PriceRule> priceRules) {
		
		for(Item item : basket.getItems()) {
			PriceRule priceRule = getFirstPriceRule(priceRules, item);
			
			double priceItem = 0;
			if(priceRule == null) {
				// Apply standard price rule
				BigDecimal price = BigDecimal.valueOf(item.getPrice());
				BigDecimal total = price.multiply(BigDecimal.valueOf(basket.getQuantity(item)));
				priceItem = total.doubleValue();
			}  else {
				// Apply price rule
				priceItem = priceRule.getTotalPriceItemFromPriceRule(basket, item);
			}
			basket.addToSubTotal(priceItem);
			

		}
		
		basket.setComputed(true);
	}
	
	/**
	 * Returns one price rule from the given Item.
	 * Returns null if not found
	 * @param priceRules
	 * @param item
	 * @return
	 */
	private PriceRule getFirstPriceRule(Set<PriceRule> priceRules, Item item) {
		PriceRule priceRule = priceRules.stream()
				  .filter(pr -> pr.getItem().getSku().equals(item.getSku()))
				  .findFirst()
				  .orElse(null);
		
		return priceRule;
	}


	
	
}
