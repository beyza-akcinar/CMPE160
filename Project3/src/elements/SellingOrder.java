package elements;

import java.util.PriorityQueue;

/**
 * This class represents selling orders given by traders.
 * @author Beyza
 *
 */
public class SellingOrder extends Order implements Comparable<SellingOrder>{
	
	/**
	 * @param traderID the ID of the trader
	 * @param amount amount of coin to be sold
	 * @param price selling price of a coin in terms of dollars
	 */
	public SellingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * Method for comparing two SellingOrder objects in terms of their priorities. With this method, we can store SellingOrder objects in a PriorityQueue object.
	 * @param e the second selling order
	 * @return <b>1</b> if the first one is of higher priority, <b>-1</b> if the second one is of higher priority, <b>0</b> if they have the same priority
	 */
	@Override
	public int compareTo(SellingOrder e) {
		if (this.price == e.price) {
			if (this.amount == e.amount) {
				return this.traderID - e.traderID;
			} else {
				return (int) (e.amount - this.amount);
			}
		} else {
			return (int) (this.price - e.price);
		}
	}
	
	/**
	 * Method for calculating the total amount of PQoins currently in the market.
	 * @param SO the selling order PriorityQueue
	 * @return total amount of PQoins in the market
	 */
	public static double getMarketPQ(PriorityQueue<SellingOrder> SO) {
		double total = 0;
		for(SellingOrder S : SO) {
			total += S.amount;
		}
		return total;
	}

	/**
	 * Getter method for the amount of PQoins that the trader wants to sell.
	 * @return amount of PQoins
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Getter method for the requested price of PQoins that the trader wants to sell.
	 * @return requested price of PQoins
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter method for the ID of the trader making the selling order.
	 * @return the ID of the trader
	 */
	public int getTraderID() {
		return traderID;
	}

}
