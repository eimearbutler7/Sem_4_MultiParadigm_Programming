package test;


///CLASS Storehouse

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;



public class Storehouse{
	


//Principio de encasulapción.
private HashMap<String, Double> prices = new HashMap<String, Double>();
HashMap<String, Integer> stocks = new HashMap<String, Integer>();

public void addProduct(String product, double price, int stock) {
prices.put(product, price);
stocks.put(product, stock);
}

public double price(String product) {
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
    	} } else {
    		return false;
		}
			return false;
	}

public Set<String> products() {

return prices.keySet();

}



}
