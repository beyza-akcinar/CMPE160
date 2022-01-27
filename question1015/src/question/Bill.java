
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

	//Initializing the fields
	private double limitingAmount;
	private double currentDebt;
	
	//The constructor of the Class
	Bill(double limitingAmount) {
		this.limitingAmount = limitingAmount;
		this.currentDebt = 0;
	}
	
	//Methods
	
	//Method for checking if the limit is exceeded
	boolean check(double amount) {
		if (this.currentDebt + amount <= this.limitingAmount) {
			return true;
		} else {
			return false;
		}
	}
	
	//Method for adding costs to current debt
	void add(double amount) {
		if (check(amount)) {
			this.currentDebt += amount;
		}
	}
	
	//Method for paying debts
	void pay(double amount) {
		if (amount >= this.currentDebt) {
			this.setCurrentDebt(0);
		} else {
			this.setCurrentDebt(this.currentDebt - amount);
		}
	}
	
	//Method for changing the limit
	void changeTheLimit(double amount) {
		if (amount >= this.currentDebt) {
			this.setLimitingAmount(amount);
		}
	}
	
	//Getter methods
	public double getLimitingAmount() {
		return limitingAmount;
	}
	
	public double getCurrentDebt() {
		return currentDebt;
	}
	
	//Setter methods
	public void setLimitingAmount(double limitingAmount) {
		this.limitingAmount = limitingAmount;
	}
	
	public void setCurrentDebt(double currentDebt) {
		this.currentDebt = currentDebt;
	}
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

