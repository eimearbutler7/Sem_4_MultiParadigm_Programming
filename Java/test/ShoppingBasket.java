package test;

///CLASS ShoppingBasket


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ShoppingBasket extends Shop {

//ArrayList<Purchase> basket = new ArrayList<Purchase>();
HashMap<String, Purchase> basket = new HashMap<String, Purchase>() {
};

public ShoppingBasket(int quantityInput, Storehouse store, Scanner reader)  {
	super(store, reader, quantityInput);
//	int QI = String.valueOf(Shop.quantityInput());

}

public void add(String product, double price) {
	
  Purchase p = new Purchase(product, quantityInput, price);

  if (basket.containsKey(product)) {

      basket.get(product).increaseAmount();

     // p.increaseAmount();
    //  basket.put(product, (basket.get(product)+price));
  } else {

//      Purchase p = new Purchase(product,quantityInput, price);
      basket.put(product, p);
      System.out.printf("Entry to basket");

  }
 // System.out.println(p.price());

}

public double price() {

  double price = 0;

  for (Purchase p : basket.values()) {
      price = price + p.price();
  }

  return price;

}

public void print() {

  String s = "";

  for (Purchase p : basket.values()) {

      s = s + p + "\n";
  }

  System.out.println(s);

}
}
