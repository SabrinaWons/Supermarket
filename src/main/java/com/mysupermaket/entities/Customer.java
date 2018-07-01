package com.mysupermaket.entities;

import java.util.Set;

public class Customer {
	
	private Basket basket = new Basket();
	
	public void buyItem(Item item) {
		basket.addItem(item);
	}
	
	public double checkout(Set<PriceRule> priceRules) {
		return basket.getTotal(priceRules);
	}
	
	public double checkout() {
		return basket.getTotal();
	}
	
	public Set<Item> getSetOfItems(){
		return basket.getItems();
	}

}
