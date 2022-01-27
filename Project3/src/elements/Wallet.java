package elements;

/**
 * This class represents wallets that can keep dollars and PQoins of traders.
 * @author Beyza
 *
 */
public class Wallet {
	
	private double dollars;
	private double coins;
	private double blockedDollars;
	private double blockedCoins;
	
	/**
	 * 
	 * @param dollars the dollars to be keps inside the wallet
	 * @param coins the PQoins to be kept inside the wallet
	 */
	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
	}
	
	/**
	 * Method for withdrawing dollars from the wallet.
	 * @param amount the amount of dollars to be withdrawn from the wallet
	 * @return <b>true</b> if successful, <b>false</b> if it is not
	 */
	public boolean withdrawDollars(double amount) {
		if (this.dollars >= amount) {
			this.dollars -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method for blocking the usage of PQoins for other transactions when they enter the market.
	 * @param amount amount of PQoins to be blocked
	 */
	public void blockCoins(double amount) {
		this.blockedCoins += amount;
		this.coins -= amount;
	}
	
	/**
	 * Method for blocking the usage of dollars for other transactions when they enter the market.
	 * @param amount amount of dollars to be blocked
	 */
	public void blockDollars(double amount) {
		this.blockedDollars += amount;
		this.dollars -= amount;
	}
	
	/**
	 * Method for unblocking the usage of PQoins for other transactions when they exit the market.
	 * @param amount amount of PQoins blocked before
	 * @param coin amount of PQoins to be unblocked
	 */
	public void unblockCoins(double amount, double coin) {
		this.blockedCoins -= coin;
	}
	
	/**
	 * Method for blocking the usage of dollars for other transactions when they exit the market.
	 * @param amount amount of dollars blocked before
	 * @param price amount of dollars to be unblocked
	 */
	public void unblockDollars(double amount, double price) {
		this.blockedDollars -= price;
	}
	
	/**
	 * Method for adding dollars to the wallet.
	 * @param amount amount of dollars to be added
	 */
	public void depositDollars(double amount) {
		this.dollars += amount;
	}
	
	/**
	 * Method for adding PQoins to the wallet.
	 * @param amount amount of PQoins to be added
	 */
	public void depositCoins(double amount) {
		this.coins += amount;
	}
	
	/**
	 * Getter method for total amount of dollars in the wallet (including blocked dollars).
	 * @return total amount of dollars
	 */
	public double getAllDollars() {
		return this.dollars + this.blockedDollars;
	}

	/**
	 * Getter method for total amount of PQoins in the wallet (including blocked PQoins).
	 * @return total amount of PQoins
	 */
	public double getAllCoins() {
		return this.coins + this.blockedCoins;
	}

	/**
	 * Getter method for amount of dollars in the wallet (excluding blocked dollars).
	 * @return amount of dollars
	 */
	public double getDollars() {
		return dollars;
	}

	/**
	 * Getter method for amount of PQoins in the wallet (excluding blocked PQoins).
	 * @return amount of PQoins
	 */
	public double getCoins() {
		return coins;
	}

	/**
	 * Getter method for amount of blocked dollars in the wallet.
	 * @return amount blocked of dollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}

	/**
	 * Getter method for amount of blocked PQoins in the wallet.
	 * @return amount blocked of PQoins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	
}
