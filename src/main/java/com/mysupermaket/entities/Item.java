package com.mysupermaket.entities;


public class Item {
	
	private int id;
	private String sku;
	private double price;


	public Item() {
		
	}
	
	public Item(String sku, double price) {
		this.sku = sku;
		if(price < 0) {
			throw new IllegalArgumentException("The price can't be negative");
		}
		this.price = price;
	}
	
	public Item(Item item) {
		this.id = item.id;
		this.sku = item.sku;
		this.price = item.price;
		
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getSku() {
		return sku;
	}
	
	public double getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
		Item other = (Item) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}


	


	
	
}
