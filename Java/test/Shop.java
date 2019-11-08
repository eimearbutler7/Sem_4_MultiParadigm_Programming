package test;

/// CLASS Shop



import java.util.Scanner;
import test.ShoppingBasket;
import test.Storehouse;

public class Shop{

protected Storehouse store;
protected Scanner reader;
private Storehouse stocks;
protected static int quantityInput;
protected static String productInput;

public Shop(Storehouse store, Scanner reader, int quantityInput) {
    this.store = store;
    this.reader = reader;
    this.quantityInput = quantityInput;
}


// *A* the first method initialised here - Intro & question to customer
public void manage(String customer) {
    ShoppingBasket basket = new ShoppingBasket(0, stocks, reader);
    System.out.println("What would you like to " + customer);


    

 //   while (true) {
    try {    
    	 System.out.print("Enter the Product Name Here:");
         String productInput = reader.nextLine();
         
         System.out.print("How Many:");
	    int quantityInput = reader.nextInt();
	  //Int quantityInput = reader.nextLine();
	 // int x = quantityInput;
	       
	        	if (productInput.isEmpty()) {
	                	System.out.println("Sorry,not enough information");}
	        	if (quantityInput > 0) { 
	        			if (store.stocks.get(productInput) >= quantityInput) {
	        				for(int i=0;i<quantityInput;i++) { 
	        					store.take(productInput);}
	        				//	System.out.println(quantityInput);
	        				for(int j=0;j<quantityInput+1;j++)
	        					basket.add(productInput, store.price(productInput));
	        					System.out.println("end adding to basket & updating stock");
	        				} } 
	        			else {
	        				System.out.println("We don't have any more " + productInput+".");
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
	    }
}

