package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * This class represents a market of 2 virtual assets, dollars and PQoin. 
 * @author Beyza
 *
 */
public class Market {
	
	private PriorityQueue<SellingOrder> sellingOrders; 
	private PriorityQueue<BuyingOrder> buyingOrders; 
	private ArrayList<Transaction> transactions; 
	private int fee;
	
	/**
	 * @param fee market fee
	 */
	public Market(int fee) {
		this.buyingOrders = new PriorityQueue<BuyingOrder>();
		this.sellingOrders = new PriorityQueue<SellingOrder>();
		this.transactions = new ArrayList<Transaction>();
		this.fee = fee;
	}
	
	/**
	 * Method for adding a new selling order to the sellingOrders PriorityQueue.
	 * @param order selling order to be added
	 */
	public void giveSellOrder(SellingOrder order) {
		this.sellingOrders.add(order);
	}
	
	/**
	 * Method for adding a new buying order to the sellingOrders PriorityQueue.
	 * @param order buying order to be added
	 */
	public void giveBuyOrder(BuyingOrder order) {
		this.buyingOrders.add(order);
	} 
	
	/**
	 * Method for making open market operations in order to change the price level in the market.
	 * @param price the desired price
	 * @param traderList ArrayList containing all traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traderList) {
		if (sellingOrders.size() == 0 && buyingOrders.size() == 0) {
			return;
		}
		if (buyingOrders.size() > 0) {
			if (buyingOrders.peek().getPrice() >= price) {
				while(true) {
					double p = buyingOrders.peek().getPrice();
					double a = buyingOrders.peek().getAmount();
					SellingOrder SO = new SellingOrder(0, a, p);
					sellingOrders.add(SO);
					
					checkTransactions(traderList);
					if(buyingOrders.size() == 0 || buyingOrders.peek().getPrice() < price) {
						break;
					}
				}
			} else if (sellingOrders.size() > 0) {
				if (sellingOrders.peek().getPrice() <= price) {
					while(true) {
						double p = sellingOrders.peek().getPrice();
						double a = sellingOrders.peek().getAmount();
						BuyingOrder BO = new BuyingOrder(0, a, p);
						buyingOrders.add(BO);
				
						checkTransactions(traderList);
						if(sellingOrders.size() == 0 || sellingOrders.peek().getPrice() > price) {
							break;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Method for checking whether a new transaction is possible in the current market situation. After checking, possible transactions are made.
	 * @param traders ArrayList containing all traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		while (buyingOrders.size() > 0 && sellingOrders.size() > 0) {
			BuyingOrder BO = buyingOrders.poll();
			SellingOrder SO = sellingOrders.poll();
			Trader S = traders.get(SO.traderID);
			Trader B = traders.get(BO.traderID);
			int fee = S.getMarketFee();
			if (BO.price == SO.price) {
				if (BO.amount <= SO.amount) {
					S.getWallet().depositDollars(BO.amount*SO.price*(1-(fee/1000.0)));
					S.getWallet().unblockCoins(SO.amount, BO.amount);
					B.getWallet().unblockDollars(BO.amount*BO.price, BO.amount*SO.price);
					B.getWallet().depositCoins(BO.amount);
					Transaction T = new Transaction(SO, BO);
					transactions.add(T);
					if (BO.amount < SO.amount) {
						SellingOrder S1 = new SellingOrder(SO.traderID, SO.amount-BO.amount, SO.price);
						sellingOrders.add(S1);
					}
				} else if (BO.getAmount() > SO.getAmount()) {
					S.getWallet().depositDollars(SO.amount*SO.price*(1-(fee/1000.0)));
					S.getWallet().unblockCoins(SO.amount, SO.amount);
					B.getWallet().unblockDollars(BO.amount*BO.price, SO.amount*SO.price);
					B.getWallet().depositCoins(SO.amount);
					BuyingOrder B1 = new BuyingOrder(BO.traderID, BO.amount-SO.amount, BO.price);
					buyingOrders.add(B1);
					Transaction T = new Transaction(SO, BO);
					transactions.add(T);
				}
			} else if (BO.getPrice() > SO.getPrice()) {
				if (BO.amount <= SO.amount) {
					S.getWallet().depositDollars(BO.amount*SO.price*(1-(fee/1000.0)));
					S.getWallet().unblockCoins(SO.amount, BO.amount);
					B.getWallet().unblockDollars(BO.amount*BO.price, BO.amount*BO.price);
					B.getWallet().depositDollars((BO.price-SO.price)*BO.amount);
					B.getWallet().depositCoins(BO.amount);
					if (BO.amount < SO.amount) {	
						SellingOrder S1 = new SellingOrder(SO.traderID, SO.amount-BO.amount, SO.price);
						sellingOrders.add(S1);
					}
					Transaction T = new Transaction(SO, BO);
					transactions.add(T);
				} else if (BO.amount > SO.amount) {
					S.getWallet().depositDollars(SO.amount*SO.price*(1-(fee/1000.0)));
					S.getWallet().unblockCoins(SO.amount, SO.amount);
					B.getWallet().unblockDollars(BO.amount*BO.price, SO.amount*BO.price);
					B.getWallet().depositDollars((BO.price-SO.price)*SO.amount);
					B.getWallet().depositCoins(SO.amount);
					BuyingOrder B1 = new BuyingOrder(BO.traderID, BO.amount-SO.amount, BO.price);
					buyingOrders.add(B1);
					Transaction T = new Transaction(SO, BO);
					transactions.add(T);		
				}
			}
			else {
				buyingOrders.add(BO);
				sellingOrders.add(SO);
				break;				
			}		
		}
	}
	
	/**
	 * Getter method for the selling order PriorityQueue.
	 * @return selling order PriorityQueue
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}
	
	/**
	 * Getter method for the buying order PriorityQueue.
	 * @return buying order PriorityQueue
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}
	
	/**
	 * Getter method for the transactions ArrayList.
	 * @return transactions ArrayList
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	/**
	 * Getter method for the market fee.
	 * @return fee
	 */
	public int getFee() {
		return this.fee;
	}

}
