package com.mysupermaket.dao;

import com.mysupermaket.entities.Item;


/**
 * The repository of Items
 * 
 */
public interface ItemDAO {

	public Item create(Item item);
	public boolean delete(Item item);
	
	public Item getItem(String name);
	

}
