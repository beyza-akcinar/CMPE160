package elements;

import java.util.PriorityQueue;

/**
 * This class represents orders which can be given by traders. In this projects, buying and selling orders are possible.
 * @author Beyza
 *
 */
public class Order {
	
	protected double amount;
	protected double price;
	protected int traderID;
	protected Market market;
	
	/**
	 * @param traderID the ID of the trader
	 * @param amount amount of coin to be sold
	 * @param price selling price of a coin in terms of dollars
	 */
	public Order(int traderID, double amount, double price) {
		this.traderID = traderID;
		this.amount = amount;
		this.price = price;
	}
	
	/**
	 * Method for calculating the average price in the market.
	 * @param SO the selling order PriorityQueue
	 * @param BO the buying order PriorityQueue
	 * @return the average price in the market
	 */
	public static double getAveragePrice(PriorityQueue<SellingOrder> SO, PriorityQueue<BuyingOrder> BO) {
		if (SO.size() == 0 && BO.size() == 0) {
			return 0;
		} else if (SO.size() == 0 && BO.peek().getPrice() > 0) {
			return BO.peek().getPrice();
		} else if (BO.size() == 0 && SO.peek().getPrice() > 0) {
			return SO.peek().getPrice();
		} else if (BO.size() > 0 && SO.size() > 0) {
			if (SO.peek().getPrice() > 0 && BO.peek().getPrice() > 0) {
				return (SO.peek().getPrice() + BO.peek().getPrice())/2;
			}
		}
		return 0;
	}

	/**
	 * Getter method for the amount of PQoins that the trader wants to sell/buy.
	 * @return amount of PQoins
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Getter method for the requested price of PQoins that the trader wants to sell/buy.
	 * @return requested price of PQoins
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Getter method for the ID of the trader making the selling/buying order.
	 * @return the ID of the trader
	 */
	public int getTraderID() {
		return traderID;
	}
	
}

