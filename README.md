# Supermarket

SuperMarket is an application that lets customers choose and put items in their basket.
When the customer has finished his shopping, he can do the checkout.
Sometimes, the Supermarket offers discounts on some items. Those discounts will be apply when the customer starts the checkout.

**Main method:**

Launch the class Main.java.
This class will populate few items and price rules in the database.
It will also create a customer who buys few items, and then checkouts.




**Database:**

In this project, the database is a Map.
ItemDAO and PriceRuleDAO are interfaces, you can then switch the implementation to use a real database.


**Price rules:**

PriceRule is an interface. At the moment, there is only one implementation: PriceRuleMultipleItems.
You can add other implementations. Ex: PriceRuleBuyThenFree (buy two, one free)

  