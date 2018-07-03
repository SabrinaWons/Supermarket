package com.mysupermaket.entities;

public interface PriceRule {
	
	public void setId(int id);
	public int getId();
	public Item getItem();
	
	public double getTotalPriceItemFromPriceRule(Basket basket, Item item);
	
	

}
