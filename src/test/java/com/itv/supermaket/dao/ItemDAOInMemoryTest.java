package com.itv.supermaket.dao;

import org.junit.Assert;
import org.junit.Test;

import com.itv.supermaket.entities.Item;

public class ItemDAOInMemoryTest {
	
	ItemDAOInMemory itemDAO = ItemDAOInMemory.getInstance();
	
	
	@Test(expected = NullPointerException.class)
	public void testDeleteWhenItemIsNullThrowsException() {
		itemDAO.delete(null);
	}
	
	@Test
	public void testDeleteItemWhenItemNotPresent() {
		boolean isDeleted = itemDAO.delete(new Item("BB", 2));
		Assert.assertFalse(isDeleted);
	}
	
	@Test
	public void testDeleteItem() {
		itemDAO.create(new Item("AA", 2));
		Assert.assertNotNull(itemDAO.getItem("AA"));;
		
		
		boolean isDeleted = itemDAO.delete(new Item("AA", 2));
		Assert.assertTrue(isDeleted);
		Assert.assertNull(itemDAO.getItem("AA"));
	}

}
