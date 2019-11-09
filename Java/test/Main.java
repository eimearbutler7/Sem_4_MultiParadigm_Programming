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

public class Main {
	 public static int counter = 0; 	 //Source: https://stackoverflow.com/questions/20256541/how-to-define-global-variable-outside-class
	 public static double shopCash = 0;  

public static void main(String[] args) {

if (counter == 0) {    //use counter to skip the introduction when calling the Main method after the first time
		Main.counter++; 
		
    	
		System.out.println("** Welcome to ADifferentApproach.com **\n");

    	//Firstly items are added to the Storehouse 
	
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
				//double SpriceInput2 = Double.parseDouble(arr3[1].trim());
				String SquantityInput = arr3[2];
				//int SquantityInput2 = Integer.parseInt(arr3[2].trim());
				 
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

        System.out.println("----Items for Sale----");
    	for (String product : store.products()) {
         System.out.printf(product +"  " + store.price(product)+"\n");
        }

        
        System.out.println("\n");
      //  System.out.println(String.valueOf(store.products()));
        
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
    		
    		System.out.print("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
    		int directionInput = scan.nextInt();
    		if (directionInput == 1) {
    		    System.out.println("\n");
    		    System.out.println("Searching for List...");
    			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
	            shop.number1("src/test/Customer.csv");
    			
    		}
    				
    		if (directionInput == 2) { 
    			Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
    			shop.manage("buy?");}
    		
    		scan.close();
        	}		
    		
    	catch (Exception e) {
    		Main.main(args);
    	}
        }
        
 else {
        	try {
        		Storehouse store = new Storehouse();
        	    Scanner scan = new Scanner(System.in);
        		
        		System.out.print("Enter 1 if you want to collect pre-submitted shopping list \nor 2 if you want to input your order now:");
        		int directionInput = scan.nextInt();
        		if (directionInput == 1) {
        			System.out.println("Searching for List...");
        			Shop shop = new Shop(store, new Scanner(System.in), 0);  //*A* initialises interaction with customer, intro & question
    	            shop.number1("src/test/Customer.csv");
        			
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
          

        


        
        
