package com.mysupermaket.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.mysupermaket.entities.Item;
import com.mysupermaket.entities.PriceRule;
import com.mysupermaket.manager.PriceRuleManager;


public class PriceRuleDAOInMemory implements PriceRuleDAO {
	
	// Eager instantiation
	private static final PriceRuleDAOInMemory instance = new PriceRuleDAOInMemory();
	
	// The map contains all the PrceRules
	// The key is the priceRule.id, the value is the PriceRule
	private Map<Integer, PriceRule> map = new ConcurrentHashMap<>();
	private AtomicInteger nextSequence = new AtomicInteger();
	
	// Prevents instantiation outside from the class
	private PriceRuleDAOInMemory() {
	}
	
	// Singleton
	public static PriceRuleDAOInMemory getInstance() {
		return instance;
	}

	/**
	 * Add a new price rule to the map
	 * @return The newly created price rule
	 */
	@Override
	public PriceRule createPriceRule(PriceRule priceRule) {
		int id = nextSequence.incrementAndGet();
		PriceRuleManager prm = new PriceRuleManager();
		PriceRule pr = prm.copyPriceRule(priceRule);
		pr.setId(id);
		map.put(id, pr);
		return pr;
	}

	/**
	 * Returns the list of price rules associated to the set of items
	 * @throws NullPointerException If items is null
	 */
	@Override
	public Set<PriceRule> getPriceRules(Set<Item> items) {
		Set<PriceRule> priceRules = new HashSet<>();
		for(Item item : items) {
			for(PriceRule priceRule : map.values()) {
				if(priceRule.getItem().getSku().equals(item.getSku())) {
					priceRules.add(priceRule);
				}
			}
		}
		return priceRules;
	}

	/**
	 * Removes the price rule from the map
	 * @returns	true is the price rule has been deleted, false otherwise
	 * @throws NullPointerException If priceRule is null
	 */
	@Override
	public boolean deletePriceRule(PriceRule priceRule) {
		boolean isAlreadyPresent = map.containsKey(priceRule.getId());
		if(isAlreadyPresent) {
			map.remove(priceRule.getId());
		}
		return isAlreadyPresent;
	}
	
	

}
