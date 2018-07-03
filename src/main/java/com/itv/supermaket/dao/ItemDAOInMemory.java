package com.mysupermaket.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.mysupermaket.entities.Item;


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
	
	
	/**
	 * Creates an item
	 * @throws IllegalArgumentException If the item already exists
	 */
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

	/**
	 * Returns the item
	 * If not found or if sku is null, returns null
	 */
	@Override
	public Item getItem(String sku) {
		Item item = map.values()
			.stream()
			.filter(i -> i.getSku().equals(sku))
			.findFirst()
			.orElse(null);
		return item;
	}
	
	/**
	 * Deletes an item
	 * @throws NullPointerException If item is null
	 */
	@Override
	public boolean delete(Item item) {
		boolean isAlreadyPresent = map.containsKey(item.getSku());
		if(isAlreadyPresent) {
			map.remove(item.getSku());
		}
		return isAlreadyPresent;
	}

}
