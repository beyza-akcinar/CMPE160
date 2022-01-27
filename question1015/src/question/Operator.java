
package question;

public class Operator {
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	//Initializing the fields
	public int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	private int talkingService;
	private int messageService;
	private double networkService;
	
	//The constructor of the Class
	Operator(int ID, double talkingCharge, double messageCost, double networkCharge, int discountRate) {
		this.ID = ID;
		this.talkingCharge = talkingCharge;
		this.messageCost = messageCost;
		this.networkCharge = networkCharge;
		this.discountRate = discountRate;
	}
	
	//Methods
	
	//Method for calculating the talking cost
	double calculateTalkingCost(int minute, Customer customer) {
		return talkingCharge*minute;
	}
	
	//Method for calculating the message cost
	double calculateMessageCost(int quantity, Customer customer, Customer other) {
		Operator thisOp = customer.getOperator();
		Operator otherOp = other.getOperator();
		
		double charge = thisOp.messageCost;
		
		if (thisOp.ID == otherOp.ID) {
			double discountRate = thisOp.discountRate;
			return charge*quantity*(100.0-discountRate)/100.0;
		} else {
			return charge*quantity;
		}
	}
	
	//Method for calculating network cost
	double calculateNetworkCost(double amount) {
		return amount*this.networkCharge;
	}
	
	//Getter methods
	public double getTalkingCharge() {
		return talkingCharge;
	}
	
	public double getMessageCost() {
		return messageCost;
	}
	
	public double getNetworkCharge() {
		return networkCharge;
	}
	
	public int getDiscountRate() {
		return discountRate;
	}
	
	public int getTalkingService() {
		return talkingService;
	}

	public int getMessageService() {
		return messageService;
	}

	public double getNetworkService() {
		return networkService;
	}

	//Setter methods
	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}
	
	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}
	
	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}
	
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	public void setTalkingService(int talkingService) {
		this.talkingService = talkingService;
	}
	
	public void setMessageService(int messageService) {
		this.messageService = messageService;
	}
	
	public void setNetworkService(double networkService) {
		this.networkService = networkService;
	}
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

