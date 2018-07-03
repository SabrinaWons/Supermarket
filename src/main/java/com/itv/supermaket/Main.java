package com.itv.supermaket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.itv.supermaket.dao.ItemDAO;
import com.itv.supermaket.dao.ItemDAOInMemory;
import com.itv.supermaket.dao.PriceRuleDAO;
import com.itv.supermaket.dao.PriceRuleDAOInMemory;
import com.itv.supermaket.entities.Basket;
import com.itv.supermaket.entities.PriceRule;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start supermarket");
		
		ItemDAO itemDAO = ItemDAOInMemory.getInstance();
		PriceRuleDAO priceRuleDAO = PriceRuleDAOInMemory.getInstance();
		
		MySupermarket supermarket = new MySupermarket(itemDAO, priceRuleDAO);

		
		supermarket.initDatabase();
		
		Basket basket = new Basket();
		basket.addItem(supermarket.getItem("A"));
		basket.addItem(supermarket.getItem("B"));
		basket.addItem(supermarket.getItem("A"));
		
		
		Set<PriceRule> priceRules = supermarket.getCopyOfPriceRules(basket.getSetOfItems());
		basket.checkout(priceRules);
		double total = basket.getTotal();
		
		System.out.println(total);
		
		
		List<String> list = new ArrayList<>();
		list.add("");
		
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("");
		
		ItemDAO itemDAO1 = ItemDAOInMemory.getInstance();
		itemDAO1.getItem("");
		
		ItemDAOInMemory itemDAO2 = ItemDAOInMemory.getInstance();
		itemDAO2.getItem("");
		
		
		
		
	}

}
