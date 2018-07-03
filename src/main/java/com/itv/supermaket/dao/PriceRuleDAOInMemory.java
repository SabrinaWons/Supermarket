package com.itv.supermaket.dao;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.itv.supermaket.entities.Item;
import com.itv.supermaket.entities.PriceRule;


public class PriceRuleDAOInMemory implements PriceRuleDAO {
	
	// Eager instantiation
	private static final PriceRuleDAOInMemory instance = new PriceRuleDAOInMemory();
	
	// The map contains all the PriceRules
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


	@Override
	public PriceRule createPriceRule(PriceRule priceRule) {
		int id = nextSequence.incrementAndGet();
		
		PriceRule pr = PriceRuleDAO.copyPriceRule(priceRule);
		pr.setId(id);
		map.put(id, pr);
		return pr;
	}


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


	@Override
	public boolean deletePriceRule(PriceRule priceRule) {
		boolean isAlreadyPresent = map.containsKey(priceRule.getId());
		if(isAlreadyPresent) {
			map.remove(priceRule.getId());
		}
		return isAlreadyPresent;
	}
	
	

}
