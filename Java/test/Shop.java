package test;

//this is the main page of my assignment containing the bulk of the coding

import java.util.Scanner;
import test.ShoppingBasket;
import test.Storehouse;

import java.io.IOException;                       
import java.nio.charset.StandardCharsets;         
import java.nio.file.Files;                     
import java.nio.file.Paths;                     
import java.util.ArrayList;                     
import java.util.Collections;                   
import java.util.List;                          


public class Shop{

//private, protected and public variables created (commentary on the differences is in my report) 
protected Storehouse store;
protected Scanner reader;
private Storehouse stocks;
protected static int quantityInput;	
protected static String productInput;

protected static int directionInput;   
public static double budgetInputList = 0;

//connection between this class and other classes is established here with this data
//being made available to other "extended" or subclasses within the program. 
public Shop(Storehouse store, Scanner reader, int quantityInput) {
    this.store = store;
    this.reader = reader;
    this.quantityInput = quantityInput;
}



// the "Live Customer" method is introduced here 
public void manage(String customer) {
//create shopping basket 
	ShoppingBasket basket = new ShoppingBasket(0, stocks, reader);
    System.out.println("What would you like to " + customer);

//generate options for user input for product selection        
    	 System.out.print("Select the number of the product below:\n");
    	 System.out.print("1) Coke Can\n");
    	 System.out.print("2) Bread\n");
    	 System.out.print("3) Spagetti\n");
    	 System.out.print("4) Tomato Sauce\n");
    	 System.out.print("5) Bin Bags\n");
      	 int productInput1 = reader.nextInt();
      	
      	 String productInput=" ";
         if (productInput1 == 1) {productInput = "CokeCan"; }
         if (productInput1 == 2) {productInput = "Bread";}
         if (productInput1 == 3) {productInput = "Spagetti";}
         if (productInput1 == 4) {productInput = "TomatoSauce";}
         if (productInput1 == 5) {productInput = "BinBags"; }
                
//generate integer input for product quantity        
         System.out.print("How Many:");
	     int quantityInput = reader.nextInt();
	    
	      
//interrogate the information received in to see if it is valid - within budget, the shop has stock etc. 
	     
	    int x = store.stock(productInput);

	    try { 
	        	if (productInput.isEmpty()) {
	                	System.out.println("Sorry,not enough information");}
    			if (store.stocks.get(productInput) == 0) {
					System.out.println("Sorry, we don't have any more " + productInput+".");
				}
	        	if (quantityInput > 0) { 
	        			if (store.stocks.get(productInput) >= quantityInput) {
	        				for(int i=0;i<quantityInput;i++) { 
	        					store.take(productInput);
        				}
	        				for(int j=0;j<quantityInput+1;j++) {
	        					basket.add(productInput, store.price(productInput));
	        	//product added to basket & stock updated here
        				} }
        			
	        			else if (store.stocks.get(productInput) < quantityInput) {
	        				for(int k=0;k<x;k++)
	        					store.take(productInput);
	        				for(int k=0;k<x+1;k++)
		        				basket.add(productInput, store.price(productInput));
	        				System.out.println("We were not able to complete your full order, but gave you what is left");
	        			}
	        	}
	        	
	    }
            catch (Exception e) {
                System.out.print("Sorry not a valid entry!\n");
//trigger program to revert to main menu    	
            	Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
                shop.manage("do, try again?");}
 	        
//print updated stock levels for the purpose of the assignment
	    System.out.println("\n");
        System.out.println("----Updated Stock Levels----");
        System.out.println("CokeCan:  " + store.stock("CokeCan"));
        System.out.println("Bread: " + store.stock("Bread"));
        System.out.println("Spagetti: " + store.stock("Spagetti"));
        System.out.println("TomatoSauce: " + store.stock("TomatoSauce"));
        System.out.println("BinBags: " + store.stock("BinBags"));
        System.out.println("\n");
              
//confirm what the purchases are 
      	System.out.println("your purchases are: ");
    	basket.print();
    	System.out.println("Purchase price: " + basket.price());
    	System.out.println("\n");
 

//revert to the main menu
    	String[] args = new String[] {"Go Again"};
        Main.main(args);
}


//This method implements the reading of the .csv files and associated actions
public void number1(String filename) {
	
//create arrays to populate further on
	ArrayList<String> SList = new ArrayList<>();
	List<String> lines = Collections.emptyList(); 

//read in .csv file		
	try { 
		lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
		
		String title = lines.get(0);
		String[] arr1 = title.split(",");
		String nameInput = arr1[0];
		int budgetInput = Integer.parseInt(arr1[1].trim());
		Shop.budgetInputList = (budgetInputList + budgetInput) ;
		
		System.out.println(); 
	    System.out.println("*********************************");
	    System.out.println(nameInput +", this is your submitted ShoppingList from " + filename);
	    System.out.println("\n");
		   
//skip line 1 which contains the budget and name only
	    lines.remove(0);
//for the other lines, read their data and store as product name and quantity
	    for (String line : lines) {
			String[] arr = line.split(",");
			String LproductInput = arr[0];
			String LquantityInput = arr[1];
			
			for (int i = 0; i < arr.length; i++) {
				SList.add(arr[i]);}}
				System.out.println(SList);
				System.out.println("\n");
				System.out.println("Your budget is: " + budgetInput + " euro.");
				
				System.out.println("*********************************\n");
	
   ///////////////////////////////////

//pose question to the user once the file is read in...				
			Scanner scan = new Scanner(System.in);
			System.out.println("\n");
			System.out.print("Enter 1 to buy these products, \nor 2 to go back to the main menu and input a different order manually:");
			System.out.println("\n");
			int directionInput = scan.nextInt();
			if (directionInput == 1) {
				 System.out.print("Working on your order......\n");
				 Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
//if 1 is chosen, this activates another method where the sale will be processed 
				 shop.BuySList(SList);
			}
//if 2 is chosen, user directed back tot he main menu
			else if (directionInput == 2) {
				String[] args = new String[] {"Go Again"};
                Main.main(args);
			}}
//exceptions trigger main menu		        
				catch (Exception e) {
	                System.out.print("Sorry not a valid entry!\n");
    	
	                String[] args = new String[] {"Go Again"};
	                Main.main(args);

}}


//this method processes the sales fromt he csv files
public void BuySList(ArrayList<String> SListarray) {

	ShoppingBasket basket = new ShoppingBasket(quantityInput, stocks, reader);
	
// in order to process, I first broke the array down into separate data points
	String A = SListarray.get(0);
	String B = SListarray.get(2);
	String C = SListarray.get(4);
	String D = SListarray.get(6);
	String E = SListarray.get(8);
	
	int F = Integer.parseInt(SListarray.get(1).trim());
	int G = Integer.parseInt(SListarray.get(3).trim());
	int H = Integer.parseInt(SListarray.get(5).trim());
	int I = Integer.parseInt(SListarray.get(7).trim());
	int J = Integer.parseInt(SListarray.get(9).trim());

//and fed the data points into another method to update the shoppinglist and make stock adjustments	
	Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  
    shop.BuySList2(A, F);
	shop.BuySList2(B, G);
	shop.BuySList2(C, H);
	shop.BuySList2(D, I);
	shop.BuySList2(E, J);

//once the shopping list is updated, the totals are calculated
	int Acount = 0;
  	int Bcount = 0;
  	int Ccount = 0;
  	int Dcount = 0;
  	int Ecount = 0;
  	
  	for (String item : ShoppingBasket.ShopBasket) {
  	    if (item == A){
  	    	Acount ++;
  	    }
  	    else if (item == B){
  	    	Bcount ++;
  	    }
  	    else if (item == C){
  	    	Ccount ++;
  	    }
  	    else if (item == D){
  	  	    	Dcount ++;
  	    }
  	    else if (item == E){
  	    	Ecount ++;
  	    }
  	}
  	
//the total cost of the order can be found
 	double cost = (Acount * store.price(A))+(Bcount * store.price(B))+(Ccount * store.price(C))+(Dcount * store.price(D))+(Ecount * store.price(E));

//the cost vs the customer budget is considered	
if (budgetInputList < cost) {
	System.out.println("Sorry you dont have enough money to purchase all these items. Please reassess and start again");
    String[] args = new String[] {"Go Again"};
    Main.main(args);
}

//if it passes this challenge, the updated stock levels and shopping list is printed. 
else {

	
	System.out.println("\n");
	System.out.println("----Updated Stock Levels----");
	System.out.println("CokeCan:  " + store.stock("CokeCan"));
	System.out.println("Bread: " + store.stock("Bread"));
	System.out.println("Spagetti: " + store.stock("Spagetti"));
	System.out.println("TomatoSauce: " + store.stock("TomatoSauce"));
	System.out.println("BinBags: " + store.stock("BinBags"));
	System.out.println("\n");
	
  	
  	System.out.println("----- Your Shopping Basket------\n");
  	System.out.format("%2s%12s%14s", "Product", "Quantity", "Unitprice(€)\n"); //https://stackoverflow.com/questions/2745206/output-in-a-table-format-in-javas-system-out
  	System.out.format("%2s%10d%11s%3s", A, Acount,store.price(A),"\n");
  	System.out.format("%2s%11d%12s%3s", B, Bcount,store.price(B),"\n");
  	System.out.format("%2s%8d%12s%3s", C, Ccount,store.price(C),"\n");
  	System.out.format("%2s%5d%11s%3s", D, Dcount,store.price(D),"\n");
  	System.out.format("%2s%8d%13s%3s", E, Ecount,store.price(E),"\n");
  	System.out.println(" ");

//total costs etc. are rounded to 2 decimal places
  	System.out.println("Total:  €" + Math.round((cost) * 100d) / 100d);
  	double remain = ( Math.round((budgetInputList-cost) * 100d) / 100d);
	System.out.println("Your remaining budget is: €" + Math.round((remain)* 100d) / 100d);
	System.out.println("The store cash is now: €" + Math.round((Main.shopCash + cost)* 100d) / 100d);
	
  	
	System.out.println(" ");
  	
//////////////////////////////////////////////////
	
//The option to buy more is given and additional purchases are added to totals purchases. 	
//ideally I would have redirected to the "live Customer" method (manage) however this was causing an null pointer exception error I could not resolve.

//repeated code	
 	
 	 System.out.print("Select the number of the product below if you wish to buy more :\n");
	 System.out.print("1) Coke Can\n");
	 System.out.print("2) Bread\n");
	 System.out.print("3) Spagetti\n");
	 System.out.print("4) Tomato Sauce\n");
	 System.out.print("5) Bin Bags\n");
  	 int productInput1 = reader.nextInt();
  	
  	 String productInput=" ";
     if (productInput1 == 1) {productInput = "CokeCan"; }
     if (productInput1 == 2) {productInput = "Bread";}
     if (productInput1 == 3) {productInput = "Spagetti";}
     if (productInput1 == 4) {productInput = "TomatoSauce";}
     if (productInput1 == 5) {productInput = "BinBags"; }
            
      System.out.print("How Many:");
      int quantityInput = reader.nextInt();

   int x = store.stock(productInput);

	  if (store.price(productInput)*quantityInput < remain) { 
		try { 

		if (productInput.isEmpty()) {
               	System.out.println("Sorry,not enough information");}
		if (store.stocks.get(productInput) == 0) {
				System.out.println("Sorry, we don't have any more " + productInput+".");
			}
       	if (quantityInput > 0) { 
       			if (store.stocks.get(productInput) >= quantityInput) {
       				for(int i=0;i<quantityInput;i++) { 
       					store.take(productInput);
   				}
   			
       				for(int j=0;j<quantityInput+1;j++) {
       					basket.add(productInput, store.price(productInput));
       					//System.out.println("end adding to basket & updating stock");
   				} }
   			
       			else if (store.stocks.get(productInput) < quantityInput) {
       				for(int k=0;k<x;k++)
       					store.take(productInput);
       				for(int k=0;k<x+1;k++)
	        				basket.add(productInput, store.price(productInput));
       				System.out.println("We were not able to complete your full order, but gave you what is left");
       			}

       	}
	  } catch (Exception e) {
       	System.out.print("Sorry not a valid entry!\n");
       	shop.manage("do, try again?");}
          
  
  
	   System.out.println("\n");
	   System.out.println("----Updated Stock Levels----");
	   System.out.println("CokeCan:  " + store.stock("CokeCan"));
	   System.out.println("Bread: " + store.stock("Bread"));
	   System.out.println("Spagetti: " + store.stock("Spagetti"));
	   System.out.println("TomatoSauce: " + store.stock("TomatoSauce"));
	   System.out.println("BinBags: " + store.stock("BinBags"));
	   System.out.println("\n");
   
   
	 	System.out.println("Your additional purchases are: ");
		basket.print();
		System.out.println("Price: " + basket.price());
		System.out.println("\n");
		System.out.println("Updated total:  €" + Math.round(((cost + (store.price(productInput)*quantityInput)) * 100d) / 100d));
		System.out.println("Your remaining budget is: €" + Math.round(((remain - (store.price(productInput)*quantityInput))) * 100d) / 100d);
		System.out.println("The store cash is now: €" + (Main.shopCash + (cost + (store.price(productInput)*quantityInput))));
		System.out.println("\n");
	
		String[] args = new String[] {"Start Again"};
		Main.main(args);

	
}	else {
	  
		System.out.println("You dont have enough money for this transaction. Start again.");

		String[] args = new String[] {"Start Again"};
		Main.main(args);
	}   
	  

}}

//method to process .csv orders
public void BuySList2(String LproductInput, int LquantityInput) {
		
	ShoppingBasket basket = new ShoppingBasket(quantityInput, stocks, reader);
	 
	try {
//challenge to make sure stock is available			
		if (store.stocks.get(LproductInput) >= LquantityInput) {
			for(int i=0;i<LquantityInput;i++) { 
				store.take(LproductInput);
			}
			
			for(int j=0;j<LquantityInput;j++) {
				basket.add(LproductInput, store.price(LproductInput));
			} }
		
		else if (store.stocks.get(LproductInput) == 0) {
			System.out.println("Sorry, we don't have any more " + LproductInput+".");
		}
			        			
		else if (store.stocks.get(LproductInput) < LquantityInput) {
			for(int k=0;k<270;k++) {
				basket.add(LproductInput, store.price(LproductInput));
				store.take(LproductInput);
				}
		
				System.out.println("We were not able to complete your full order, but gave you what is left");
				System.out.println("We have run out of " + LproductInput+".");
				
				}
//       	}
	
	}
		
	  catch (Exception e) {
           System.out.print("Sorry this failed!\n");
            String[] args = new String[] {"Go Again"};
            Main.main(args);
	  }
	  
	

} //method end

} //class body end


