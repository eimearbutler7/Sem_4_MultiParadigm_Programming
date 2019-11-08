package shop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
	

	private String name;
	private double budget;
	private ArrayList<ProductStock> shoppingList;
	
	public Customer(double a){
		this.budget = a;
		//this.shoppingList.Product = list;
		}

	
	public Customer(String filename) {
		shoppingList = new ArrayList<>();
		List<String> lines = Collections.emptyList(); 
		  
		try { 
			  lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
		      String[] firstline = lines.get(0).split(",");
		      name = firstline[0];
		      budget = Double.parseDouble(firstline[1]);
		      //I am removing at index 0 as it is the only one treated differently
		      lines.remove(0);
		      for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				int quantity = Integer.parseInt(arr[1].trim());
		    	Product p = new Product(name, 0);
		    	ProductStock s = new ProductStock (p, quantity); 
				shoppingList.add(s);
				}
		    } 
		  
		    catch (IOException e) 
		    { 
		  
		      // do something 
		      e.printStackTrace(); 
		    } 
		   
	}

	public String getName() {
		return name;
	}

	public double getBudget() {
		return budget;
	}

	public ArrayList<ProductStock> getShoppingList() {
		return shoppingList;
	}
	
	@Override
	public String toString() {
		String ret = "Customer [name=" + name + ", budget=" + budget + ", \nshoppingList =\n";
		for (ProductStock productStock : shoppingList) {
			ret+= productStock.getProduct().getName() + "  Quantity:  " + productStock.getQuantity() + "\n";
					
		}
		return ret +"]";
	}
	
	public static void main(String[] args) {
		Customer james = new Customer("src/shop/Customer.csv");
		System.out.println(james.shoppingList);	
		
			} 
	}

	

