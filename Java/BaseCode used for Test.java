/*
Exercise 12: Online Shop
Link: http://mooc.cs.helsinki.fi/programming-part2/material/week-8?noredirect=1#e12
*/

///MAIN

package pkg041715_onlineshop;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*
         Storehouse store = new Storehouse();
         store.addProduct("coffee", 5, 1);

         System.out.println("stocks:");
         System.out.println("coffee:  " + store.stock("coffee"));
         System.out.println("sugar: " + store.stock("sugar"));

         System.out.println("we take a coffee " + store.take("coffee"));
         System.out.println("we take a coffee " + store.take("coffee"));
         System.out.println("we take sugar " + store.take("sugar"));

         System.out.println("stocks:");
         System.out.println("coffee:  " + store.stock("coffee"));
         System.out.println("sugar: " + store.stock("sugar"));
         */
        //12.3
        /*
         Storehouse store = new Storehouse();
         store.addProduct("milk", 3, 10);
         store.addProduct("coffee", 5, 6);
         store.addProduct("buttermilk", 2, 20);
         store.addProduct("jogurt", 2, 20);

         System.out.println("products:");
         for (String product : store.products()) {
         System.out.println(product);
         }*/
        //12.4
        /*
         Purchase purchase = new Purchase("milk", 4, 2);
         System.out.println( "the total price of a purchase containing four milks is " + purchase.price() );
         System.out.println( purchase );
         purchase.increaseAmount();
         System.out.println( purchase );
         */
        //12.5
        /*
         ShoppingBasket basket = new ShoppingBasket();
         basket.add("milk", 3);
         basket.add("buttermilk", 2);
         basket.add("cheese", 5);
         System.out.println("basket price: " + basket.price());
         basket.add("computer", 899);
         System.out.println("basket price: " + basket.price());
        
         basket.print();
         */
        //12.7
       /*
         ShoppingBasket basket = new ShoppingBasket();
         basket.add("milk", 3);
         basket.print();
         System.out.println("basket price: " + basket.price() +"\n");

         basket.add("buttermilk", 2);
         basket.print();
         System.out.println("basket price: " + basket.price() +"\n");

         basket.add("milk", 3);
         basket.print();
         System.out.println("basket price: " + basket.price() +"\n");

         basket.add("milk", 3);
         basket.print();
         System.out.println("basket price: " + basket.price() +"\n");
         */
        //12.8
        Storehouse store = new Storehouse();
        store.addProduct("coffee", 5, 10);
        store.addProduct("milk", 3, 20);
        store.addProduct("milkbutter", 2, 55);
        store.addProduct("bread", 7, 8);

        Shop shop = new Shop(store, new Scanner(System.in));
        shop.manage("Pekka");
    }

}


/// CLASS Purchase

package pkg041715_onlineshop;

public class Purchase {

    private String product;
    private int amount;
    private int unitPrice;

    public Purchase(String product, int amount, int unitPrice) {

        this.product = product;
        this.amount = amount;
        this.unitPrice = unitPrice;

    }

    public int price() {

        //public int price(), which returns the purchase price. This is obtained by raising the unit amount by the unit price.
        //return (int) Math.pow(this.amount, this.unitPrice);
        return amount * unitPrice;

    }

    public void increaseAmount() {
        this.amount = this.amount + 1;
    }

    public String toString() {

        String s = "";

        s = s + this.product + ": " + this.amount;

        return s;
    }

}


/// CLASS Shop

package pkg041715_onlineshop;

import java.util.Scanner;
import pkg041715_onlineshop.ShoppingBasket;
import pkg041715_onlineshop.Storehouse;

public class Shop {

    private Storehouse store;
    private Scanner reader;

    public Shop(Storehouse store, Scanner reader) {
        this.store = store;
        this.reader = reader;
    }

    // the method to deal with a customer in the shop
    public void manage(String customer) {
        ShoppingBasket basket = new ShoppingBasket();
        System.out.println("Welcome to our shop " + customer);
        System.out.println("below is our sale offer:");

        for (String product : store.products()) {
            System.out.println(product);
        }

        while (true) {
            System.out.print("what do you want to buy (press enter to pay):");
            String product = reader.nextLine();
            if (product.isEmpty()) {
                break;
            }

            //Principio de encasulapción.
            if (store.getStocks().get(product) > 0) {
                store.take(product);
                basket.add(product, store.price(product));
            } else {
                System.out.println("We don't have any more " + product+".");
            }

            // here, you write the code to add a product to the shopping basket, if the storehouse is not empty
            // and decreases the storehouse stocks
            // do not touch the rest of the code!
        }

        System.out.println("your purchases are:");
        basket.print();
        System.out.println("basket price: " + basket.price());
    }
}

///CLASS ShoppingBasket

package pkg041715_onlineshop;

import com.sun.org.apache.xml.internal.security.keys.content.KeyValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingBasket {

    //ArrayList<Purchase> basket = new ArrayList<Purchase>();
    HashMap<String, Purchase> basket = new HashMap<String, Purchase>() {
    };

    public ShoppingBasket() {

    }

    public void add(String product, int price) {

        Purchase p = new Purchase(product, 1, price);

        if (basket.containsKey(product)) {

            basket.get(product).increaseAmount();

            //p.increaseAmount();
           // basket.put(product, (basket.get(product)+price));
        } else {

            //Purchase p = new Purchase(product, price);
            basket.put(product, p);
        }
        //System.out.println(p.price());

    }

    public int price() {

        int price = 0;

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

///CLASS Storehouse

package pkg041715_onlineshop;

import java.util.HashMap;
import java.util.Set;

public class Storehouse {

   //Principio de encasulapción.
    private HashMap<String, Integer> prices = new HashMap<String, Integer>();
    private HashMap<String, Integer> stocks = new HashMap<String, Integer>();

    public void addProduct(String product, int price, int stock) {
        prices.put(product, price);
        stocks.put(product, stock);
    }

    public int price(String product) {
        if (prices.containsKey(product)) {
            return prices.get(product);
        } else {
            return -99;
        }
    }

    public int stock(String product) {
        if (stocks.containsKey(product)) {
            return stocks.get(product);
        } else {
            return 0;
        }
    }

    public boolean take(String product) {
        if (stocks.containsKey(product)) {
            if ((stocks.get(product) - 1) >= 0) {
                stocks.put(product, (stocks.get(product) - 1));
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Set<String> products() {

        return prices.keySet();

    }

}