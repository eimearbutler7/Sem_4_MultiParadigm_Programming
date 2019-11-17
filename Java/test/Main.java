package test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//Note: The following source was used as base code to get started, comparison of original and final in github repository
//https://github.com/vivianamarquez-2013/Object-Oriented-Programming-with-Java/blob/master/II-8-12-OnlineShop
//My work was primarily to significantly expand the functionality through manipulating Main class and Shop class. Much of the ShoppingBasket, Purchase and Storehouse functionality was sourced from the github example.

public class Main {
	 public static int counter = 0; 	 //Source: https://stackoverflow.com/questions/20256541/how-to-define-global-variable-outside-class
	 public static double shopCash = 0;  

public static void main(String[] args) {

//use counter to skip the introduction when calling the Main method after the first time
	
if (counter == 0) {    
		Main.counter++; 
		
    	
		System.out.println("** Welcome to A-Different-Approach.com **\n");
		System.out.println("\n");
		System.out.println("** NOTE: some continuing loops connecting operations do not work **");
		System.out.println("** if you receive an error, please run again and choose a different route! **\n");

//Firstly extract data from .csv and add items to the Storehouse 
	
		String filename = "src/test/stock.csv";
		ArrayList<String> storeIn = new ArrayList<>();
		ArrayList<String> PRO = new ArrayList<>();
		ArrayList<String> PRI = new ArrayList<>();
		ArrayList<String> QUA = new ArrayList<>();

		
		List<String> lines = Collections.emptyList(); 
			try {
				lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
			} catch (IOException e) {
					e.printStackTrace();
			} 
			
			String title = lines.get(0);
			String[] arr4 = title.split(",");
			int shopCash1 = Integer.parseInt(arr4[0].trim());
			Main.shopCash = (Main.shopCash + shopCash1);
			
			lines.remove(0);
			for (String line : lines) {
				String[] arr3 = line.split(",");
				String SproductInput = arr3[0];
				String SpriceInput = arr3[1];
				String SquantityInput = arr3[2];
				
//add items to the Storehouse
				PRO.add(arr3[0]);	
				PRI.add(arr3[1]);
				QUA.add(arr3[2]);
			
			}
		
    	Storehouse store = new Storehouse();
    	store.addProduct(PRO.get(0), Double.parseDouble(PRI.get(0).trim()), Integer.parseInt(QUA.get(0).trim()));
        store.addProduct(PRO.get(1), Double.parseDouble(PRI.get(1).trim()), Integer.parseInt(QUA.get(1).trim()));
        store.addProduct(PRO.get(2), Double.parseDouble(PRI.get(2).trim()), Integer.parseInt(QUA.get(2).trim()));
        store.addProduct(PRO.get(3), Double.parseDouble(PRI.get(3).trim()), Integer.parseInt(QUA.get(3).trim()));
        store.addProduct(PRO.get(4), Double.parseDouble(PRI.get(4).trim()), Integer.parseInt(QUA.get(4).trim()));

//print items for sale to the console
        System.out.println("----Items for Sale----");
    	for (String product : store.products()) {
         System.out.printf(product +"  " + store.price(product)+"\n");
        }

        
        System.out.println("\n");
      //  System.out.println(String.valueOf(store.products()));

//for the sake of the assignment print the stock levels too
        System.out.println("----Starting Stock Levels----");
        System.out.println("CokeCan:  " + store.stock("CokeCan"));
        System.out.println("Bread: " + store.stock("Bread"));
        System.out.println("Spagetti: " + store.stock("Spagetti"));
        System.out.println("TomatoSauce: " + store.stock("TomatoSauce"));
        System.out.println("BinBags: " + store.stock("BinBags"));
        System.out.println("\n");
	
        System.out.println("----Starting Store Cash----");
        System.out.println("        €"+Main.shopCash);
        System.out.println("---------------------------");
        System.out.println(" ");
        
        
        
        try {
    	    Scanner scan = new Scanner(System.in);
    	    
//create options for the user suing integer input to minimise user error
    		
    		System.out.print("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
    		int directionInput = scan.nextInt();
    		if (directionInput == 1) {
    			System.out.print("\n");

// all 3 csv files can be chosen/read without changing the code
    			System.out.print("Enter 1 if you are James, 2 if you are Michael and 3 if you are Mary:");
        		int customerSelect = scan.nextInt();
        		if (customerSelect == 1) {
	        		System.out.println("\n");
	    		    System.out.println("Searching for List...");
	    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
		            shop.number1("src/test/Customer.csv");}
        		else if (customerSelect == 2) {
	        		System.out.println("\n");
	    		    System.out.println("Searching for List...");
	    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
		            shop.number1("src/test/Customer2.csv");}
        		else if (customerSelect == 3) {
	        		System.out.println("\n");
	    		    System.out.println("Searching for List...");
	    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
		            shop.number1("src/test/Customer3.csv");}
    		}
    				
//chooses "live customer" route
    		if (directionInput == 2) { 
    			Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
    			shop.manage("buy?");}
    		
    		scan.close();
        	}		
    		
//try and catch for exceptions used throughout the program to minimise " mull pointer exceptions
        catch (Exception e) {
    		Main.main(args);
    	}
        }
    

//once the counter goes over 1 i.e. the method is run once, only the questions are presented, not the full shop introduction
 else {
        	try {
        		Storehouse store = new Storehouse();
        	    Scanner scan = new Scanner(System.in);
        		
        		System.out.print("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
        		int directionInput = scan.nextInt();
        		if (directionInput == 1) {
        			System.out.print("\n");

        		// all 3 csv files can be chosen/read without changing the code
        		    			System.out.print("Enter 1 if you are James, 2 if you are Michael and 3 if you are Mary:");
        		        		int customerSelect = scan.nextInt();
        		        		if (customerSelect == 1) {
        			        		System.out.println("\n");
        			    		    System.out.println("Searching for List...");
        			    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
        				            shop.number1("src/test/Customer.csv");}
        		        		else if (customerSelect == 2) {
        			        		System.out.println("\n");
        			    		    System.out.println("Searching for List...");
        			    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
        				            shop.number1("src/test/Customer2.csv");}
        		        		else if (customerSelect == 3) {
        			        		System.out.println("\n");
        			    		    System.out.println("Searching for List...");
        			    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
        				            shop.number1("src/test/Customer3.csv");}
        		    		}
        				
        		if (directionInput == 2) { 
        			Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
        			shop.manage("buy?");}
        		}		
        	
        	catch (Exception e) {
        		Main.main(args);
        	}   
 }




}//main method

} //Main Class