package com.mysupermaket.main;

import java.util.Set;

import com.mysupermaket.dao.ItemDAOInMemory;
import com.mysupermaket.dao.PriceRuleDAOInMemory;
import com.mysupermaket.entities.Customer;
import com.mysupermaket.entities.PriceRule;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start supermarket");
		MySupermarket supermarket = MySupermarket.getInstance();
		supermarket.setItemDAO(ItemDAOInMemory.getInstance());
		supermarket.setPriceRuleDAO(PriceRuleDAOInMemory.getInstance());
		supermarket.initDatabase();
		
		Customer customer = new Customer();
		customer.buyItem(supermarket.getItem("A"));
		customer.buyItem(supermarket.getItem("B"));
		customer.buyItem(supermarket.getItem("A"));
		
		
		Set<PriceRule> priceRules = supermarket.getCopyOfPriceRules(customer.getSetOfItems());
		double total = customer.checkout(priceRules);
		
		System.out.println(total);
		
		

	}

}
