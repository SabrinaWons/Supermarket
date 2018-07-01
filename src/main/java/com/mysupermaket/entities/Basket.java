package com.mysupermaket.entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.mysupermaket.manager.Checkout;

/**
 * The basket contains all the items the user has put in his basket
 * 
 */
public class Basket {
	
	private final Map<Item, Integer> items = new HashMap<>();
	
	private double subTotal;
	private boolean isComputed;
	
	
	public boolean isComputed() {
		return isComputed;
	}

	public void setComputed(boolean isComputed) {
		this.isComputed = isComputed;
	}

	public Set<Item> getItems() {
		return items.keySet();
	}

	public void addItem(Item item) {
		Integer quantity = items.get(item) == null ? 1 : items.get(item) + 1;
		items.put(item, quantity);
		isComputed = false;
	}

	
	public void addToSubTotal(double price) {
		this.subTotal += price;
	}
	
	/**
	 * Returns the quantity of the given item found in the basket
	 * If item not found, returns 0
	 * @param item
	 * @return
	 */
	public int getQuantity(Item item) {
		Integer quantity = items.get(item) == null ? 0 : items.get(item);
		return quantity;
	}

	/** Returns the total of a basket
	 * Applies a set of price rules
	 * @return
	 */
	public double getTotal(Set<PriceRule> priceRules) {
		if(isComputed) {
			return subTotal;
		}
		Checkout checkout = new Checkout();
		checkout.doCheckout(this, priceRules);
		isComputed = true;
		return subTotal;
	}
	
	/** Returns the total of a basket
	 * @return
	 */
	public double getTotal() {
		Set<PriceRule> priceRules = new HashSet<>();
		return getTotal(priceRules);
	}

}
