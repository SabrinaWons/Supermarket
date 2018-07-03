package com.itv.supermaket.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.itv.supermaket.manager.Checkout;

/**
 * The basket contains all the items the user has put in his basket
 * 
 */
public class Basket {
	
	// Contains all the items in the basket
	private final Map<Item, Integer> items = new HashMap<>();
	
	private double subTotal;
	

	/**
	 * Returns the set of items in the basket.
	 * It does not take into account the quantity for each item.
	 * @return
	 */
	public Set<Item> getSetOfItems() {
		return items.keySet();
	}

	/**
	 * Adds an item to the basket. 
	 * Increments the quantity of item if the item is already present in the basket
	 * @param item
	 */
	public void addItem(Item item) {
		Integer quantity = items.get(item) == null ? 1 : items.get(item) + 1;
		items.put(item, quantity);
	}
	
	/**
	 * Removes the item from the basket
	 * Decrements the quantity of the item if the item is already present in the basket
	 * @param item
	 */
	public void deleteOneItem(Item item) {
		Integer quantity = items.get(item) == null ? 0 : items.get(item) - 1;
		if(quantity < 1) {
			items.remove(item);
		} else {
			items.put(item, quantity);
		}
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

	/** 
	 * Computes and updates the total
	 * Applies a set of price rules
	 * @return
	 */
	public void checkout(Set<PriceRule> priceRules) {
		Checkout checkout = new Checkout();
		double total = checkout.computeCheckout(this, priceRules);
		subTotal = total;
	}
	
	/** Returns the total of a basket
	 * @return
	 */
	public double getTotal() {
		return subTotal;
	}

}
