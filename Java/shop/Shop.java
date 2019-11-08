package shop;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shop extends Customer {

	private double cash;
	private ArrayList<ProductStock> stock;

	public Shop(double a, String filename) {
		super(a);
		stock = new ArrayList<>();
				List<String> lines = Collections.emptyList(); 
		    try { 
		      lines = 
		       Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
		      System.out.println(lines.get(0));
		      cash = Double.parseDouble(lines.get(0));
		      //I am removing at index 0 as it is the only one treated differently
		      lines.remove(0);
		      for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				double price = Double.parseDouble(arr[1]);
				int quantity = Integer.parseInt(arr[2].trim());
		    	Product p = new Product(name, price);
		    	ProductStock s = new ProductStock (p, quantity); 
				stock.add(s);
				}
		    } 
		  
		    catch (IOException e) 
		    { 
		        e.printStackTrace(); 
		  	      
		    }        
		    //the CSV file is now read in and I have all the variables I need to calculate the ins and outs of the shop
		    //a = the customers budget (using Java Inheretance from Customer.java)
		    //list = the customers shoppinglist (using Java Inheretance from Customer.java)
		    
		    
		    
		    
		    
		} 
	

	
	
	public double getCash() {
		return cash;
	}

	
	
	public ArrayList<ProductStock> getStock() {
		return stock;
	}
	
	  
	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}

//	public void removeFromStock(String name, double price, int quantity)
//    { 
//
//        Product temp = new Item(name, price, quantity);
//        double totalPrice = (price * quantity);
//        productStock.getQuantity() = temp;
//        itemCount -= 1;
//        if(itemCount==capacity)
//        {
//            increaseSize();
//        }
//    }
//
//    // -------------------------------------------------------
//    //  Returns the contents of the cart together with
//    //  summary information.
//    // -------------------------------------------------------
//    public String toString()
//    {
//      NumberFormat fmt = NumberFormat.getCurrencyInstance();
//
//      String contents = "\nShopping Cart\n";
//      contents += "\nItem\t\tUnit Price\tQuantity\tTotal\n";
//
//      for (int i = 0; i < itemCount; i++)
//          contents += cart[i].toString() + "\n";
//
//      contents += "\nTotal Price: " + fmt.format(totalPrice);
//      contents += "\n";
//
//      return contents;
//    }

	
	public static void main(String args) {
		
		Shop shop = new Shop(200, "src/shop/stock.csv");
		System.out.println(shop);
		
		//System.out.println(Shop1);
		
		
		//shop.budget();

	    // Display the value of the brand attribute (from the Vehicle class) and the value of the modelName from the Car class
	    
		//		System.out.println(shop);
//		System.out.println(a);
//		System.out.println(shoppingList);	
//		removeFromStock(james.shoppingList) 
//		productStock.setQuantity(james.shoppingList)
	}

}
