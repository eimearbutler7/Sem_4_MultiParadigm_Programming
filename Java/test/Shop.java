package test;

/// CLASS Shop



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

protected Storehouse store;
protected Scanner reader;
private Storehouse stocks;
protected static int quantityInput;
protected static String productInput;

protected static int directionInput;   //
//protected static double budget;        //
public static double budgetInputList = 0;

public Shop(Storehouse store, Scanner reader, int quantityInput) {
    this.store = store;
    this.reader = reader;
    this.quantityInput = quantityInput;
}



// *A* the first method initialised here - Intro & question to customer
public void manage(String customer) {
    ShoppingBasket basket = new ShoppingBasket(0, stocks, reader);
    System.out.println("What would you like to " + customer);

        
    	 System.out.print("Enter the Product Name Here:");
         String productInput = reader.nextLine();
         
         System.out.print("How Many:");
	    int quantityInput = reader.nextInt();
	    

	
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
        				//	System.out.println(quantityInput);
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
	        	
	    }
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
		

      	System.out.println("your purchases are: ");
    	basket.print();
    	System.out.println("Purchase price: " + basket.price());
    	System.out.println("\n");
 


    	String[] args = new String[] {"Go Again"};
        Main.main(args);
}


public void number1(String filename) {
	
	ArrayList<String> SList = new ArrayList<>();
	List<String> lines = Collections.emptyList(); 
	try { 
		lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8); 
		//System.out.println(lines);
		
		String title = lines.get(0);
		String[] arr1 = title.split(",");
		String nameInput = arr1[0];
		int budgetInput = Integer.parseInt(arr1[1].trim());
		Shop.budgetInputList = (budgetInputList + budgetInput) ;
		
		System.out.println();
				    
	    System.out.println("*********************************");
	    System.out.println(nameInput +", this is your submitted ShoppingList from " + filename);
	    System.out.println("\n");
		   

		
		lines.remove(0);
		for (String line : lines) {
			String[] arr = line.split(",");
			String LproductInput = arr[0];
			String LquantityInput = arr[1];
			//System.out.printf(LproductInput +"  " + LquantityInput +"\n");
		    //SList.append(LproductInput);
		 //   SList.add(arr[i])
			
			for (int i = 0; i < arr.length; i++) {
				SList.add(arr[i]);}}
				System.out.println(SList);
				System.out.println("\n");
				System.out.println("Your budget is: " + budgetInput + " euro.");
				
				System.out.println("*********************************\n");
	
   ///////////////////////////////////
				
			Scanner scan = new Scanner(System.in);
			System.out.println("\n");
			System.out.print("Enter 1 to buy these products, \nor 2 to go back to the main menu and input a different order manually:");
			System.out.println("\n");
			int directionInput = scan.nextInt();
			if (directionInput == 1) {
				 System.out.print("Working on your order......\n");
				 
				 Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
	             shop.BuySList(SList);
			}
			else if (directionInput == 2) {
				String[] args = new String[] {"Go Again"};
                Main.main(args);
			}}
		        
				catch (Exception e) {
	                System.out.print("Sorry not a valid entry!\n");
	                System.out.println("Remember it is case sensitive");      	
	                String[] args = new String[] {"Go Again"};
	                Main.main(args);

}}

public void BuySList(ArrayList<String> SListarray) {

	ShoppingBasket basket = new ShoppingBasket(quantityInput, stocks, reader);
	
	
//	System.out.print("BuySList");
//	System.out.print(SListarray);
//	System.out.println("\n");
	String A = SListarray.get(0);
	String B = SListarray.get(2);
	String C = SListarray.get(4);
	String D = SListarray.get(6);
	String E = SListarray.get(8);
//	System.out.println(A);
	int F = Integer.parseInt(SListarray.get(1).trim());
	int G = Integer.parseInt(SListarray.get(3).trim());
	int H = Integer.parseInt(SListarray.get(5).trim());
	int I = Integer.parseInt(SListarray.get(7).trim());
	int J = Integer.parseInt(SListarray.get(9).trim());
//	System.out.println(F);
	
	Shop shop = new Shop(store, new Scanner(System.in), quantityInput);  //*A* initialises interaction with customer, intro & question
    shop.BuySList2(A, F);
	shop.BuySList2(B, G);
	shop.BuySList2(C, H);
	shop.BuySList2(D, I);
	shop.BuySList2(E, J);

	////////////////////////////
	
	 System.out.print(" Order Complete\n");


	///////////////////////////	
		
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

 
  	System.out.println("\n");
  	//basket.print(); 
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
  	
  	System.out.println("----- Your Shopping Basket------\n");
  	System.out.format("%2s%12s%14s", "Product", "Quantity", "Unitprice(€)\n"); //https://stackoverflow.com/questions/2745206/output-in-a-table-format-in-javas-system-out
  	System.out.format("%2s%10d%11s%3s", A, Acount,store.price(A),"\n");
  	System.out.format("%2s%11d%12s%3s", B, Bcount,store.price(B),"\n");
  	System.out.format("%2s%8d%12s%3s", C, Ccount,store.price(C),"\n");
  	System.out.format("%2s%5d%11s%3s", D, Dcount,store.price(D),"\n");
  	System.out.format("%2s%8d%13s%3s", E, Ecount,store.price(E),"\n");
  	double cost = (Acount * store.price(A))+(Bcount * store.price(B))+(Ccount * store.price(C))+(Dcount * store.price(D))+(Ecount * store.price(E));
  	System.out.println(" ");

  	System.out.println("Total:  €" + (cost));
  	double remain = ( Math.round((budgetInputList-cost) * 100d) / 100d);
	System.out.println("Your remaining budget is: €" + remain);
	System.out.println("The store cash is now: €" + (Main.shopCash + cost));
	
  	
	System.out.println(" ");
  	
//  	System.out.println(	"Would you like to buy more?");
//		String[] args = new String[] {"Go Again"};
//    	Main.main(args);

//////////////////////////////////////////////////
	
	
  	System.out.print("Enter the Product Name Here if you wish to buy more (Case Sensitive):");
    String productInput = reader.nextLine();
    
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
       	System.out.println("Remember it is case sensitive");      	
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
		System.out.println("Updated total:  €" + (cost + (store.price(productInput)*quantityInput)));
		System.out.println("Your remaining budget is: €" + Math.round(((remain - (store.price(productInput)*quantityInput))) * 100d) / 100d);
		System.out.println("The store cash is now: €" + (Main.shopCash + Math.round(((cost + (store.price(productInput)*quantityInput)))) * 100d) / 100d);
		System.out.println("\n");
	
		String[] args = new String[] {"Start Again"};
		Main.main(args);

	
}	else {
	  
		System.out.println("You dont have enough money for this transaction. Start again.");

		String[] args = new String[] {"Start Again"};
		Main.main(args);
	}    
}


public void BuySList2(String LproductInput, int LquantityInput) {
		
	ShoppingBasket basket = new ShoppingBasket(quantityInput, stocks, reader);
	// System.out.print(store.stocks.get(LproductInput));
	System.out.print(budgetInputList > (LquantityInput * store.price(LproductInput)));	
	 
	try {
		
	if (budgetInputList > (LquantityInput * store.price(LproductInput))){
	
		if (store.stocks.get(LproductInput) >= LquantityInput) {
			//TEST System.out.println("no1");
			for(int i=0;i<LquantityInput;i++) { 
				store.take(LproductInput);
			}
			
			for(int j=0;j<LquantityInput;j++) {
				//TEST System.out.println("no2");
				basket.add(LproductInput, store.price(LproductInput));
  				//TEST System.out.println("end adding to basket & updating stock");
			} }
		
		else if (store.stocks.get(LproductInput) == 0) {
			//TEST System.out.println("no3");
			System.out.println("Sorry, we don't have any more " + LproductInput+".");
		}
			        			
		else if (store.stocks.get(LproductInput) < LquantityInput) {
			//TEST 	System.out.println("no4");
			for(int k=0;k<store.stocks.get(LproductInput);k++)
				basket.add(LproductInput, store.price(LproductInput));
				System.out.println("We were not able to complete your full order, but gave you what is left");
				System.out.println("We don't have any more " + LproductInput+".");}
       	}
	else {
		System.out.println("Sorry you dont have enough money to purchase all these items. Please reassess and start again");
        String[] args = new String[] {"Go Again"};
        Main.main(args);
		}
	}
		
	  catch (Exception e) {
           System.out.print("Sorry this failed!\n");
            System.out.println("Remember it is case sensitive");      	
            String[] args = new String[] {"Go Again"};
            Main.main(args);
	  }
	  
	

} //method end

} //class body end


