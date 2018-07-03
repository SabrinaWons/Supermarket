package com.itv.supermaket.entities;

import java.math.BigDecimal;

/**
 * This class is an implementation of PriceRule
 * 
 * This is a discount of the type : x items for £x.xx
 */
public class PriceRuleMultipleItems implements PriceRule {
	
	private int id;
	private Item item;
	private int threshold;
	private double price;
	

	/**
	 * Constructor
	 * @param item
	 * @param threshold
	 * @param price
	 * @throws IllegalArgumentException If item is null, or if threshold is lower than 1, 
	 * or if price is negative
	 */
	public PriceRuleMultipleItems(Item item, int threshold, double price) {
		if(item == null) {
			throw new IllegalArgumentException("The item can't be null");
		}
		this.item = item;
		if(threshold < 1) {
			throw new IllegalArgumentException("The threshold must be greater than 1");
		}
		this.threshold = threshold;
		if(price < 0) {
			throw new IllegalArgumentException("The price should be positive");
		}
		this.price = price;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + threshold;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceRuleMultipleItems other = (PriceRuleMultipleItems) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (threshold != other.threshold)
			return false;
		return true;
	}



	/**
	 * Applies this priceRule to the given type of Item
	 * For one type of item (Ex: Item "A"), returns the total price of all the items "A"
	 * that are present in the basket
	 * @throws NullPointerException If basket or item is null
	 */
	@Override
	public double getTotalPriceItemFromPriceRule(Basket basket, Item item) {
		
		int quantity = basket.getQuantity(item);
		
		int quotient  = quantity / threshold;
		int remainder = quantity % threshold;
		
		BigDecimal quotientBG = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(quotient));
		BigDecimal remainderBD = BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(remainder));
		
		BigDecimal total = quotientBG.add(remainderBD);
		return total.doubleValue();
		
	}


	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Item getItem() {
		return item;
	}

	public int getThreshold() {
		return threshold;
	}

	public double getPrice() {
		return price;
	}

	
}
