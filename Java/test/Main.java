package test;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
    	System.out.println("** Welcome to OutsideTheBox.com: for an unconventional approach to showing you understand the concepts **\n");

    	//First method is used to add items to the Storehouse 
    	System.out.println("----Items for Sale----");
    	Storehouse store = new Storehouse();
        store.addProduct("CokeCan", 0.53, 100);
        store.addProduct("Bread", 1.79, 20);
        store.addProduct("Spagetti", 1.5, 55);
        store.addProduct("TomatoSauce", 2.3, 8);
        store.addProduct("BinBags", 3.95, 0);
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
        
        
    	//and ask what the customer wants to buy 
        Shop shop = new Shop(store, new Scanner(System.in), 1);  //*A* initialises interaction with customer, intro & question
        shop.manage("buy?");
    	
        
                           
//         System.out.println("we take a CokeCan " + store.take("CokeCan"));
//         System.out.println("we take Bread " + store.take("Bread"));
//         System.out.println("we take Spagetti " + store.take("Spagetti"));
//         System.out.println("stocks:");
//         System.out.println("CokeCan :  " + store.stock("CokeCan"));
//         System.out.println("Bread: " + store.stock("Bread"));
        
        
         //Storehouse store = new Storehouse();
//         store.addProduct("milk", 3, 10);
//         store.addProduct("coffee", 5, 6);
//         store.addProduct("buttermilk", 2, 20);
//         store.addProduct("jogurt", 2, 20);

//         System.out.println("products:");
//         for (String product : store.products()) {
//         System.out.println(product);
//         }
         

//         Purchase purchase = new Purchase("Bread", 4, 1.79);
//         System.out.println( "the total price of a purchase containing four breads is " + purchase.price() );
//         System.out.println( purchase );
//         purchase.increaseAmount();
//         System.out.println( purchase );
//         
         shop.manage("do next, buy more?");
        
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
        

    }



}

