package elements;

import executable.Main;

/**
 * This class represents traders making buying/selling decisions in a market of 2 virtual assets.
 * @author Beyza
 *
 */
public class Trader {
	
	private int id;
	private Wallet wallet;
	public static int numberOfUsers = 0;
	private boolean isMutable;
	private Market market;
	
	/**
	 * @param dollars initial amount of dollars of the trader
	 * @param coins initial amount of PQoins of the trader
	 */
	public Trader(double dollars, double coins) {
		Wallet w  = new Wallet(dollars, coins);
		this.wallet = w;
		this.isMutable = true;
		numberOfUsers++;
	}
	
	/**
	 * I have not used this method.
	 * @param amount -
	 * @param price -
	 * @param market -
	 * @return 0
	 */
	public int sell(double amount, double price, Market market) {
		return 0;
	}
	
	/**
	 * I have not used this method.
	 * @param amount -
	 * @param price -
	 * @param market -
	 * @return 0
	 */
	public int buy(double amount, double price, Market market) {
		return 0;
	}
	
	/**
	 * Method for giving random rewards to traders.
	 */
	public void giveReward() {
		double amount = Main.myRandom.nextDouble()*10;
		this.getWallet().depositCoins(amount);
	}
	
	/**
	 * Method for printing the status of the trader to the output file.
	 */
	public void printStatus() {
		return;
	}
	/**
	 * Method used for changing the value of the boolean value isMutable to false.
	 */
	public void makeImmutable() {
		this.isMutable = false;
	}

	/**
	 * Getter method for the ID of the trader.
	 * @return the ID of the trader
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter method for the wallet of the trader.
	 * @return the wallet of the trader
	 */
	public Wallet getWallet() {
		return wallet;
	}
	
	/**
	 * Getter method for the fee of the market in which the trader is buying/selling assets.
	 * @return market fee
	 */
	public int getMarketFee() {
		return this.market.getFee();
	}

	/**
	 * Setter method for the ID of the trader. Unlike other setter methods, this method only works if the value of the boolean field isMutable is true.
	 * @param id the ID of the trader
	 */
	public void setId(int id) {
		if (isMutable) {
			this.id = id;
		}
	}
	
	/**
	 * Setter method for the market in which the trader is buying/selling assets.
	 * @param market market
	 */
	public void setMarket(Market market) {
		this.market = market;
	}
	
}
