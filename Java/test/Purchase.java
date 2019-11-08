package test;

public class Purchase {

  private String product;
  private double amount;
  protected static double unitPrice;

  public Purchase(String product, int amount, double unitPrice) {

      this.product = product;
      this.amount = amount;
      this.unitPrice = unitPrice;

  }

  public double price() {

      //public int price(), which returns the purchase price. This is obtained by raising the unit amount by the unit price.
      //return (int) Math.pow(this.amount, this.unitPrice);
      return amount * unitPrice;

  }

  public void increaseAmount() {
      this.amount = this.amount + 1;
  }

  public String toString() {

      String s = "";

      s = s + this.product + ": " + this.amount;

      return s;
  }

}


