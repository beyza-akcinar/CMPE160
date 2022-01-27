package elements;

/**
 * This class represents market transactions consisting of one buying order and one selling order.
 * @author Beyza
 *
 */
public class Transaction {
	
	private SellingOrder sellingOrder;
	private BuyingOrder buyingOrder;
	
	/**
	 * @param sellingOrder selling order of transaction
	 * @param buyingOrder buying order of transaction
	 */
	public Transaction(SellingOrder sellingOrder, BuyingOrder buyingOrder) {
		this.sellingOrder = sellingOrder;
		this.buyingOrder = buyingOrder;
	}

	/**
	 * Getter method for the selling order of the transaction.
	 * @return selling order of transaction
	 */
	public SellingOrder getSellingOrder() {
		return sellingOrder;
	}

	/**
	 * Getter method for the buying order of the transaction.
	 * @return buying order of transaction
	 */
	public BuyingOrder getBuyingOrder() {
		return buyingOrder;
	}
	
}
