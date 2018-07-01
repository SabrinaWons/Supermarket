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
	
	private static final PriceRuleDAOInMemory instance = new PriceRuleDAOInMemory();
	
	private Map<Integer, PriceRule> map = new ConcurrentHashMap<>();
	private AtomicInteger nextSequence = new AtomicInteger();
	
	private PriceRuleDAOInMemory() {
		
	}
	
	public static PriceRuleDAOInMemory getInstance() {
		return instance;
	}

	@Override
	public PriceRule createPriceRule(PriceRule priceRule) {
		int id = nextSequence.incrementAndGet();
		PriceRuleManager prm = new PriceRuleManager();
		PriceRule pr = prm.copyPriceRule(priceRule);
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
	
	

}
