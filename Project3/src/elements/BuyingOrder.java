package elements;

import java.util.PriorityQueue;

/**
 * This class represents buying orders given by traders.
 * @author Beyza
 *
 */
public class BuyingOrder extends Order implements Comparable<BuyingOrder>{
	
	/**
	 * @param traderID the ID of the trader
	 * @param amount amount of coin to be sold
	 * @param price selling price of a coin in terms of dollars
	 */
	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}
	
	/**
	 * Method for comparing two BuyingOrder objects in terms of their priorities. With this method, we can store BuyingOrder objects in a PriorityQueue object.
	 * @param e the second buying order
	 * @return <b>1</b> if the first one is of higher priority, <b>-1</b> if the second one is of higher priority, <b>0</b> if they have the same priority
	 */
	@Override
	public int compareTo(BuyingOrder e) {
		if (this.price == e.price) {
			if (this.amount == e.amount) {
				return this.traderID - e.traderID;
			} else {
				return (int) (e.amount - this.amount);
			}
		} else {
			return (int) (e.price - this.price);
		}
	}
	
	/**
	 * Method for calculating the total amount of dollars currently in the market.
	 * @param BO the buying order PriorityQueue
	 * @return total amount of dollars in the market
	 */
	public static double getMarketDollars(PriorityQueue<BuyingOrder> BO) {
		double total = 0;
		for(BuyingOrder B : BO) {
			total += B.price*B.amount;
		}
		return total;
	}

	/**
	 * Getter method for the amount of PQoins that the trader wants to buy.
	 * @return amount of PQoins
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Getter method for the requested price of PQoins that the trader wants to buy.
	 * @return requested price of PQoins
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter method for the ID of the trader making the buying order.
	 * @return the ID of the trader
	 */
	public int getTraderID() {
		return traderID;
	}
	
}
