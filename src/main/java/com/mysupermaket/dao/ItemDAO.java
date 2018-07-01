package com.mysupermaket.dao;

import com.mysupermaket.entities.Item;



public interface ItemDAO {

	public Item create(Item item);
	public Item getItem(String name);
	

}
