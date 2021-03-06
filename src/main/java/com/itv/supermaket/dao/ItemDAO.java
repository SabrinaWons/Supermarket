package com.itv.supermaket.dao;

import com.itv.supermaket.entities.Item;


/**
 * The repository of Items
 * 
 */
public interface ItemDAO {

	/**
	 * Creates an item
	 * @param item
	 * @return The newly created Item
	 * @throws IllegalArgumentException If the item already exists
	 */
	public Item create(Item item);
		
	/**
	 * Returns the item
	 * If not found or if sku is null, returns null
	 * @param name
	 * @return
	 */
	public Item getItem(String name);
	

}
