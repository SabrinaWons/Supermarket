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
	
	
	@Override
	public Item create(Item item) {
		Item it = new Item(item);
		int id = nextSequence.incrementAndGet();
		it.setId(id);
		synchronized(this) {
			if(map.get(it.getSku()) != null) {
				throw new IllegalArgumentException("The item already exists");
			}
			map.put(it.getSku(), it);
		}
		return it;
	}

	
	@Override
	public Item getItem(String sku) {
		Item item = map.values()
			.stream()
			.filter(i -> i.getSku().equals(sku))
			.findFirst()
			.orElse(null);
		return item;
	}

}
