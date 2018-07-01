package com.mysupermaket.main;

import java.util.Set;

import com.mysupermaket.entities.Customer;
import com.mysupermaket.entities.PriceRule;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start supermarket");
		MySupermarket supermarket = MySupermarket.getInstance();
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
