package com.itv.supermaket.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.itv.supermaket.entities.Item;

/**
 * This class is an implementation of the repository ItemDAO
 * All the items are contained in a Map. The key is the SKU, the value is the Item
 * The SKU is unique. You can't have 2 different items for the same SKU
 *
 */
public class ItemDAOInMemory implements ItemDAO {

	// Eager instantiation
	private static ItemDAOInMemory itemDAOImpl = new ItemDAOInMemory();
	
	// Prevents instantiation outside from the class
	private ItemDAOInMemory() {
		
	}
	// Singleton
	public static ItemDAOInMemory getInstance() {
		return itemDAOImpl;
	}
	
	// The map contains all the Items
	// The key is the sku, the value is the Item
	private Map<String, Item> map = new ConcurrentHashMap<>();
	private AtomicInteger nextSequence = new AtomicInteger();
	
	

	@Override
	public Item create(Item item) {
		Item it = new Item(item);
		if(map.get(it.getSku()) != null) {
			throw new IllegalArgumentException("The item already exists");
		}
		int id = nextSequence.incrementAndGet();
		it.setId(id);
		map.put(it.getSku(), it);
		return it;
	}

	@Override
	public Item getItem(String sku) {
		Item item = map.get(sku);
		return item;
	}
	



}
