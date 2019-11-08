package test;

import java.util.ArrayList;

public class Purchase {

  private String product;
  private int amount;
  protected static double unitPrice;
  private ArrayList<Purchase> SList ;

  public Purchase(String product, int amount, double unitPrice, ArrayList<Purchase> SList) {

      this.product = product;
      this.amount = amount;
      this.unitPrice = unitPrice;
      this.SList = SList;
  }

  public double price() {

      //public int price(), which returns the purchase price. This is obtained by raising the unit amount by the unit price.
      //return (int) Math.pow(this.amount, this.unitPrice);
      return amount * unitPrice;

  }

  public int increaseAmount() {
      this.amount = this.amount + 1;
      return this.amount;
     
  }

  public String toString() {

      String s = "";

      s = s + this.product + ": " + this.amount;

      return s;
  }

}


