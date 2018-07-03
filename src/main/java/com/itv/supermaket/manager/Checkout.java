package com.mysupermaket.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mysupermaket.entities.Basket;
import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;

public class Checkout {
	
	/**
	 * Updates the total of the basket, following a set of price rules
	 * @param basket
	 * @param allPriceRules
	 * @throws NullPointerException If basket or priceRules is null
	 */
	public void doCheckout(Basket basket, Set<PriceRule> allPriceRules) {

		for(Item item : basket.getItems()) {
			List<PriceRule> priceRules = getPriceRules(allPriceRules, item);
			// Try all the price rules and then select the smaller price
			List<Double> prices = new ArrayList<>();
			
			
			// Apply standard price rule
			BigDecimal price = BigDecimal.valueOf(item.getPrice());
			BigDecimal total = price.multiply(BigDecimal.valueOf(basket.getQuantity(item)));
			prices.add(total.doubleValue());
			
			for(PriceRule priceRule : priceRules) {
				// Apply price rule
				double priceItem = priceRule.getTotalPriceItemFromPriceRule(basket, item);
				prices.add(priceItem);
			}
			
			// Choose the smaller price and apply it to the basket
			double priceItem = prices.stream().mapToDouble(d -> d).min().orElse(0);
			basket.addToSubTotal(priceItem);
			

		}
		
		basket.setComputed(true);
	}
	
	/**
	 * Returns the list of price rules for the given Item.
	 * Returns an empty list if not found
	 * @param priceRules	All the price rules
	 * @param item
	 * @return
	 * @throws NullPointerException If allPriceRules or item is null
	 */
	private List<PriceRule> getPriceRules(Set<PriceRule> allPriceRules, Item item) {
		List<PriceRule> priceRules = allPriceRules.stream()
				  .filter(pr -> pr.getItem().getSku().equals(item.getSku()))
				  .collect(Collectors.toList());
		
		return priceRules;
	}


	
	
}
