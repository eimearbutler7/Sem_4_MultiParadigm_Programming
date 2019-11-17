package test;


public class Purchase {
//source: //https://github.com/vivianamarquez-2013/Object-Oriented-Programming-with-Java/blob/master/II-8-12-OnlineShop

  private String product;
  private int amount;
  protected static double unitPrice;

  public Purchase(String product, int amount, double unitPrice) {

      this.product = product;
      this.amount = amount;
      this.unitPrice = unitPrice;
  }

  //method to establish total price of products
  public double price() {

      return amount * unitPrice;

  }

  //method to increase th estock levels
  public void increaseAmount() {
      this.amount = this.amount + 1;
      
     
  }

  //method to format printing of product listing
  public String toString() {

      String s = "";

      s = s + this.product + ": " + this.amount;

      return s;
  }

}


