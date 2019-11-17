package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ShoppingBasket extends Shop {
//note it extends Shop class
//source: //https://github.com/vivianamarquez-2013/Object-Oriented-Programming-with-Java/blob/master/II-8-12-OnlineShop

	
public static ArrayList<String> ShopBasket = new ArrayList<String>();

HashMap<String, Purchase> basket = new HashMap<String, Purchase>() {
};


//shopping basket class is established and qualityInput, Storehouse and Scanner are all shared from Shop class) 
public ShoppingBasket(int quantityInput, Storehouse store, Scanner reader)  {
	super(store, reader, quantityInput);
}

//creates method to add products to shopping basket
public void add(String product, double price) {
	
			
  Purchase p = new Purchase(product, quantityInput, price);

  //if products already exist there, increase their numbers
  if (basket.containsKey(product)) {

      basket.get(product).increaseAmount();
      ShoppingBasket.ShopBasket.add(product);
 //othewise add them     
  } else {

      basket.put(product, p);
      ShoppingBasket.ShopBasket.add(product);

  }

}

//method to establish price per product in basket
public double price() {

  double price = 0;

  for (Purchase p : basket.values()) {
      price = price + p.price();
  }

  return price;

}

// method to print out basket
public void print() {

  String s = "";

  for (Purchase p : basket.values()) {

      s = s + p + "\n";
  }

  System.out.println(s);

}
}
