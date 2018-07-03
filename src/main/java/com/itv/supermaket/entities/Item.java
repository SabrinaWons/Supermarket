package com.mysupermaket.entities;


public class Item {
	
	private int id;
	private String sku;
	private double price;

	/**
	 * Constructor
	 * @param sku
	 * @param price
	 * @throws IllegalArgumentException If sku is null or price is negative
	 */
	public Item(String sku, double price) {
		if(sku == null) {
			throw new IllegalArgumentException("The sku is required");
		}
		this.sku = sku;
		if(price < 0) {
			throw new IllegalArgumentException("The price can't be negative");
		}
		this.price = price;
	}
	
	/**
	 * Constructor
	 * @param sku
	 * @param price
	 * @throws NullPointerException If item is null
	 * @throws IllegalArgumentException If sku is null or price is negative
	 */
	public Item(Item item) {
		this(item.sku, item.price);
		this.id = item.id;
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
