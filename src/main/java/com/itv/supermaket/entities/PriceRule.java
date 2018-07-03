package com.itv.supermaket.entities;

/**
 * This interface represents a PriceRule that is composed of :
 * 		an id
 * 		an item (the price rule is applied to one item)
 * 		a total (how to apply the discount)
 * 
 * An implementation of this interface will have his own rule to apply the price rule
 *
 */
public interface PriceRule {
	
	/**
	 * Returns the id of the priceRule
	 * @return
	 */
	public int getId();
	
	/**
	 * Sets the id for this price rule
	 * @param id
	 */
	public void setId(int id);
	
	/**
	 * Returns the item of the price rule
	 * @return
	 */
	public Item getItem();
	
	/**
	 * For a given basket and item, return the computed price for this item
	 * @param basket
	 * @param item
	 * @return
	 */
	public double getTotalPriceItemFromPriceRule(Basket basket, Item item);
	
	

}
