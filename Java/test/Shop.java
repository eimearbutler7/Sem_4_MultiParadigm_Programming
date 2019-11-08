package test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import test.ShoppingBasket;
import test.Storehouse;

public class Shop{

protected static Storehouse store;
protected static Scanner reader;
private Storehouse stocks;
protected static int quantityInput;
protected static String productInput;
protected static int directionInput;
protected static double budget;


public Shop(Storehouse store, Scanner reader, int quantityInput) {
    this.store = store;
    this.reader = reader;
    this.quantityInput = quantityInput;
}



public Shop(String filename) {
	// TODO Auto-generated constructor stub
}



public static void direction() {
	try {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
		int directionInput = scan.nextInt();
		if (directionInput == 1) {
			System.out.println("Searching for List...");
			Shop.number1("src/test/Customer.csv");
			
		}
				
		if (directionInput == 2) { 
			Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
			shop.manage("buy?");}
		}		
	
	catch (Exception e) {
	    System.out.print("Sorry not a valid entry!\n");
	    Shop.direction();}
	}



// *A* the first method initialised here - Intro & question to customer




public void manage(String customer) {
   
	
   	ShoppingBasket basket = new ShoppingBasket(quantityInput, stocks, reader);
    System.out.println("What would you like to " + customer);


    

 //   while (true) {
    try {    
    	 System.out.print("Enter the Product Name Here:");
         String productInput = reader.nextLine();
       //  reader.nextLine();  // **** add this to swallow EOL token //https://stackoverflow.com/questions/27141183/scanner-nextline-occasionally-skips-input
         System.out.print("How Many:");
	    int quantityInput = reader.nextInt();
	  //Int quantityInput = reader.nextLine();
	 // int x = quantityInput;
	       
	        	if (productInput.isEmpty()) {
	                	System.out.println("Sorry,not enough information");}
	        	if (quantityInput > 0) { 
	        			if (store.stocks.get(productInput) >= quantityInput) {
	        				for(int i=0;i<quantityInput;i++) { 
	        					store.take(productInput);
	        				}
	        				//	System.out.println(quantityInput);
	        				for(int j=0;j<quantityInput;j++) {
	        					basket.add(productInput, store.price(productInput));
	        				System.out.println("end adding to basket & updating stock");
	        				} }
	        			else if (store.stocks.get(productInput) == 0) {
	        				System.out.println("Sorry, we don't have any more " + productInput+".");
	        				}
	        			
	        			else if (store.stocks.get(productInput) < quantityInput) {
	        				for(int k=0;k<store.stocks.get(productInput);k++)
	        				basket.add(productInput, store.price(productInput));
        					System.out.println("We were not able to complete your full order, but gave you what is left");
	        				System.out.println("We don't have any more " + productInput+".");}
	        	}
	
    	}

//        	Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
//            shop.manage("do, try again?");}}
	        catch (Exception e) {
                System.out.print("Sorry not a valid entry!\n");
                System.out.println("Remember it is case sensitive");      	
            	Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
                shop.manage("do, try again?");}
       
     
	        
        //Principio de encasulapción.

        
        System.out.println("\n");
        System.out.println("----Updated Stock Levels----");
        System.out.println("CokeCan:  " + store.stock("CokeCan"));
        System.out.println("Bread: " + store.stock("Bread"));
        System.out.println("Spagetti: " + store.stock("Spagetti"));
        System.out.println("TomatoSauce: " + store.stock("TomatoSauce"));
        System.out.println("BinBags: " + store.stock("BinBags"));
        System.out.println("\n");
        
        // here, you write the code to add a product to the shopping basket, if the storehouse is not empty
        // and decreases the storehouse stocks
        // do not touch the rest of the code!
    
//        
      	System.out.println("basket price: " + basket.price());
        System.out.println("your purchases are: ");
        	basket.print(); 
        System.out.println(	"at" + store.price(productInput) + "each");
	    
        Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
        shop.manage("do next, buy more?\n");

	}
public static void number1(String filename) {
			
		ArrayList<Object> SList = new ArrayList<>();
		List<String> lines = Collections.emptyList(); 
		try { 
			lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
			//System.out.println(lines);
			
			String title = lines.get(0);
			String[] arr1 = title.split(",");
			String nameInput = arr1[0];
			int budgetInput = Integer.parseInt(arr1[1].trim());
			
			System.out.println();
					    
			System.out.println(nameInput +", this is your submitted ShoppingList from " + filename);
			System.out.println("Your budget is: " + budgetInput + "euro.");
			System.out.println("...\n");

			
			lines.remove(0);
			for (String line : lines) {
				String[] arr = line.split(",");
				String productInput = arr[0];
				int quantityInput = Integer.parseInt(arr[1].trim());
				System.out.printf(productInput +"  " + quantityInput +"\n");
			    SList.add(productInput);
			    SList.add(quantityInput);}
			  //System.out.printf("SList \n");
			    //System.out.println(SList);
 
			
				Scanner scan = new Scanner(System.in);
				System.out.println("-------------------\n");
				System.out.print("Enter 1 to buy these products, \nor 2 if you want to input a different order manually:");
				int directionInput = scan.nextInt();
				if (directionInput == 1) {
					System.out.println("Action to Buy...");
				//	Shop.NEXTACTION(SList);
					
				}
						
				if (directionInput == 2) { 
					Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
					shop.manage("buy?");}}
					
			
			
catch (IOException e) 
{ 
e.printStackTrace(); }}	}
	
	


