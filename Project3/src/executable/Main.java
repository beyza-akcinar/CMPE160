package executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import elements.*;

/**
 * This is the Main class of the project. 
 * @author Beyza
 *
 */
public class Main {
	
	public static Random myRandom;
	
	/**
	 * This is the main method for the project. Input/Output operations are handled here.
	 * @param args command line parameters
	 * @throws FileNotFoundException in some circumstance
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		final ArrayList<Trader> traders = new ArrayList<Trader>();
		final ArrayList<String> invalid = new ArrayList<String>();
		
		int seed = in.nextInt();
		myRandom = new Random(seed);
		
		int fee = in.nextInt();
		Market market = new Market(fee);
		
		int C = in.nextInt();
		int D = in.nextInt();
		
		for (int i=0; i<C; i++) {
			double dollars = in.nextDouble();
			double coins = in.nextDouble();
			Trader trader = new Trader(dollars, coins);
			trader.setMarket(market);
			traders.add(trader);
			trader.setId(i);
			trader.makeImmutable();
		}
		
		for (int i=0; i<D; i++) {
			String event = in.next();
			
			if (event.equals("10")) {
				int traderID = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				if (traders.get(traderID).getWallet().getDollars() >= amount*price) {
					BuyingOrder BO = new BuyingOrder(traderID, amount, price);
					traders.get(traderID).getWallet().blockDollars(amount*price);
					market.giveBuyOrder(BO);
				} else {
					invalid.add(event);
				}
				
			} else if (event.equals("11")) {
				int traderID = in.nextInt();
				double amount = in.nextDouble();
				if (market.getSellingOrders().size() == 0) {
					invalid.add(event);
					continue;
				}
				double currentPrice = market.getSellingOrders().peek().getPrice();
				if (traders.get(traderID).getWallet().getDollars() >= amount*currentPrice) {
					BuyingOrder BO = new BuyingOrder(traderID, amount, currentPrice);
					traders.get(traderID).getWallet().blockDollars(amount*currentPrice);
					market.giveBuyOrder(BO);
				} else {
					invalid.add(event);
				}
				
			} else if (event.equals("20")) {
				int traderID = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				if (traders.get(traderID).getWallet().getCoins() >= amount) {
					SellingOrder SO = new SellingOrder(traderID, amount, price);
					traders.get(traderID).getWallet().blockCoins(amount);
					market.giveSellOrder(SO);
				} else {
					invalid.add(event);
				}
				
			} else if (event.equals("21")) {
				int traderID = in.nextInt();
				double amount = in.nextDouble();
				if (market.getBuyingOrders().size() == 0) {
					invalid.add(event);
					continue;
				}
				double currentPrice = market.getBuyingOrders().peek().getPrice();
				if (traders.get(traderID).getWallet().getCoins() >= amount) {
					SellingOrder SO = new SellingOrder(traderID, amount, currentPrice);
					traders.get(traderID).getWallet().blockCoins(amount);
					market.giveSellOrder(SO);
				} else {
					invalid.add(event);
				}
				
			} else if (event.equals("3")) {
				int traderID = in.nextInt();
				double amount = in.nextDouble();
				traders.get(traderID).getWallet().depositDollars(amount);
				
			} else if (event.equals("4")) {
				int traderID = in.nextInt();
				double amount = in.nextDouble();
				boolean b = traders.get(traderID).getWallet().withdrawDollars(amount);
				if (!b) {
					invalid.add(event);
				}
				
			} else if (event.equals("5")) {
				int traderID = in.nextInt();
				Trader t = traders.get(traderID);
				out.printf("Trader %d: %.5f$ %.5fPQ\n", traderID, t.getWallet().getAllDollars(), t.getWallet().getAllCoins());
				
			} else if (event.equals("777")) {
				for (int j=0; j<traders.size(); j++) {
					traders.get(j).giveReward();
				}
				
			} else if (event.equals("666")) {
				double price = in.nextDouble();
				market.makeOpenMarketOperation(price, traders);
				
			} else if (event.equals("500")) {
				out.print("Current market size: " );
				double totalDollars = BuyingOrder.getMarketDollars(market.getBuyingOrders()); 
				double totalPQ = SellingOrder.getMarketPQ(market.getSellingOrders());
				out.printf("%.5f %.5f\n", totalDollars, totalPQ);
				
				
			} else if (event.equals("501")) {
				out.print("Number of successful transactions: " + market.getTransactions().size() + "\n");
				
			} else if (event.equals("502")) {
				out.print("Number of invalid queries: "+ invalid.size() + "\n");
				
			} else if (event.equals("505")) {
				double averagePrice = Order.getAveragePrice(market.getSellingOrders(), market.getBuyingOrders());
				if (market.getBuyingOrders().size() > 0 && market.getSellingOrders().size() > 0) {
					out.printf("Current prices: %.5f %.5f %.5f\n", market.getBuyingOrders().peek().getPrice(), market.getSellingOrders().peek().getPrice(), averagePrice);
				} else if (market.getBuyingOrders().size() == 0 && market.getSellingOrders().size() > 0) {
					out.printf("Current prices: 0.00000 %.5f %.5f\n", market.getSellingOrders().peek().getPrice(), averagePrice);
				} else if (market.getSellingOrders().size() == 0 && market.getBuyingOrders().size() > 0) {
					out.printf("Current prices: %.5f 0.00000 %.5f\n", market.getBuyingOrders().peek().getPrice(), averagePrice);
				} else {
					out.printf("Current prices: 0.00000 0.00000 %.5f\n", averagePrice);
				}
				
			} else if (event.equals("555")) {
				for (Trader t : traders) {
					out.printf("Trader %d: %.5f$ %.5fPQ\n", t.getId(), t.getWallet().getAllDollars(), t.getWallet().getAllCoins());
				}
				
			}
			
		market.checkTransactions(traders);
		}
		
		in.close();
		out.close();
		
	}

}

