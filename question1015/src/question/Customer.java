
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	//Initializing the fields
	public int ID;
	public String name;
	private int age;
	private Operator operator;
	private Bill bill;
	private double moneySpent;
	private int talkingTime;
	private int messageCount;
	private double totalMB;
	
	//The constructor of the Class
	Customer(int ID, String name, int age, Operator operator, double limitingAmount) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.operator = operator;
		Bill bill = new Bill(limitingAmount);
		this.bill = bill;
	}
	
	//Methods
	
	//Method for calculating the talking cost
	void talk(int minute, Customer other) {
		if (this.ID == other.ID) {
			return;
		}
		double cost = this.operator.calculateTalkingCost(minute, other);
		int discountRate = this.operator.getDiscountRate();
		
		if ((this.age < 18) || (this.age > 65)) {
			cost *= (100.0-discountRate)/100.0;
			if (this.bill.check(cost)) {
				this.bill.add(cost);
			} else {
				return;
			}
		} else {
			if (this.bill.check(cost)) {
				this.bill.add(cost);
			} else {
				return;
			}
		}
		
		if (this.operator.ID == other.operator.ID) {
			int thisTS = this.operator.getTalkingService();
			thisTS += (minute*2);
			this.operator.setTalkingService(thisTS);
		} else {
			int thisTS = this.operator.getTalkingService();
			int otherTS = other.operator.getTalkingService();
			thisTS += minute;
			otherTS += minute;
			this.operator.setTalkingService(thisTS);
			other.operator.setTalkingService(otherTS);
		}
		
		int thisTT = this.getTalkingTime();
		int otherTT = other.getTalkingTime();
		thisTT += minute;
		otherTT += minute;
		this.setTalkingTime(thisTT);
		other.setTalkingTime(otherTT);
		
	}
	
	//Method for calculating the message cost
	void message(int quantity, Customer other) {
		if (this.ID == other.ID) {
			return;
		}
		double cost = this.operator.calculateMessageCost(quantity, this, other);
		
		if (this.bill.check(cost)) {
			this.bill.add(cost);
			
			int thisMS = this.operator.getMessageService();
			thisMS += quantity;
			this.operator.setMessageService(thisMS);
			
			int thisMC = this.getMessageCount();
			thisMC += quantity;
			this.setMessageCount(thisMC);
		}	
	}
	
	//Method for calculating the network cost
	void connection(double amount) {
		double cost = this.operator.calculateNetworkCost(amount);
		if (this.bill.check(cost)) {
			this.bill.add(cost);
			
			double thisNS = this.operator.getNetworkService();
			thisNS += amount;
			this.operator.setNetworkService(thisNS);
			
			double thisTM = this.getTotalMB();
			thisTM += amount;
			this.setTotalMB(thisTM);
		}
		
	}
	
	//Getter methods
	public int getAge() {
		return age;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public Bill getBill() {
		return bill;
	}

	public double getMoneySpent() {
		return moneySpent;
	}

	public int getTalkingTime() {
		return talkingTime;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public double getTotalMB() {
		return totalMB;
	}
	
	//Setter methods
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public void setMoneySpent(double moneySpent) {
		this.moneySpent = moneySpent;
	}

	public void setTalkingTime(int talkingTime) {
		this.talkingTime = talkingTime;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public void setTotalMB(double totalMB) {
		this.totalMB = totalMB;
	}

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

