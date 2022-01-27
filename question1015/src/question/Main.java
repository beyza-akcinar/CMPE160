
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {


	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		//Creating Printstream object
		PrintStream outstream1;
		try {
		        outstream1 = new PrintStream(outFile);
		} catch (FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		
		//Reading the file "input_1.txt"
		for (int i=0; i<N; i++) {
			int first = reader.nextInt();
			
			//Creating a new Customer object
			if (first == 1) {
				String name = reader.next();
				int age = reader.nextInt();
				int opID = reader.nextInt();
				double limitingAmount = reader.nextDouble();
				
				Customer customer = new Customer(0, name, age, operators[opID], limitingAmount);
				
				//Assigning the IDs for customers
				for (int j=0; j<C; j++) {
					if (customers[j] == null) {
						customers[j] = customer;
						customer.ID = j;
						break;
					}
				}
				
			} else if (first == 2) {
				//Creating a new Operator object
				double talkingCharge = reader.nextDouble();
				double messageCost = reader.nextDouble();
				double networkCharge = reader.nextDouble();
				int discountRate = reader.nextInt();
				
				Operator operator = new Operator(0, talkingCharge, messageCost, networkCharge, discountRate);
				
				//Assigning the IDs for operators
				for (int j=0; j<O; j++) {
					if (operators[j] == null) {
						operators[j] = operator;
						operator.ID = j;
						break;
					}
				}
				
			} else if (first == 3) {
				//A customer talking to another customer
				int firstID = reader.nextInt();
				int secondID = reader.nextInt();
				int time = reader.nextInt();
				
				customers[firstID].talk(time, customers[secondID]);
				
			} else if (first == 4) {
				//A customer sending message to another customer
				int firstID = reader.nextInt();
				int secondID = reader.nextInt();
				int quantity = reader.nextInt();
				
				customers[firstID].message(quantity, customers[secondID]);
				
			} else if (first == 5) {
				//A customer connecting to the Internet
				int ID = reader.nextInt();
				double amount = reader.nextDouble();
				
				customers[ID].connection(amount);
				
			} else if (first == 6) {
				//A customer paying his/her bills
				int ID = reader.nextInt();
				double amount = reader.nextDouble();
				
				double debt = customers[ID].getBill().getCurrentDebt();
				double money = customers[ID].getMoneySpent();
				
				if (amount >= debt) {
					money += debt;
					customers[ID].setMoneySpent(money);
				} else {
					money += amount;
					customers[ID].setMoneySpent(money);
				}
				
				customers[ID].getBill().pay(amount);		
				
			} else if (first == 7) {
				//A customer changing his/her operator
				int customerID = reader.nextInt();
				int operatorID = reader.nextInt();
				
				customers[customerID].setOperator(operators[operatorID]);
				
			} else if (first == 8) {
				//A customer changing his/her limit
				int ID = reader.nextInt();
				double amount = reader.nextDouble();
				
				customers[ID].getBill().changeTheLimit(amount);
				
			} 
		}	
		
		//Writing the output to the file "output_1.txt"
		
		//Operator stats (Operator <ID of the Operator> : <talking time> <n Of messages> <MBs of usage>)
		for (int i=0; i<O; i++) {
			outstream1.printf("Operator %d : %d %d %.2f\n", operators[i].ID, operators[i].getTalkingService(), operators[i].getMessageService(), operators[i].getNetworkService());
		}
		
		//Customer stats (Customer <ID of the Customer> : <total Money spent> < current debt>)
		for (int i=0; i<C; i++) {
			double currentDebt = customers[i].getBill().getCurrentDebt();
			outstream1.printf("Customer %d : %.2f %.2f\n", customers[i].ID, customers[i].getMoneySpent(), currentDebt);
		}
		
		//The customer with the max talking time
		int maxTalkingTime = -1;
		String customerName = "";
		
		for (int i=0; i<C; i++) {
			int talkingTime = customers[i].getTalkingTime();
			if (talkingTime > maxTalkingTime) {
				maxTalkingTime = talkingTime;
				customerName = customers[i].name;
			}
		}
		
		outstream1.printf("%s : %d\n",customerName, maxTalkingTime);
		
		//The customer with the max message count	
		int maxMessageCount = -1;
		
		for (int i=0; i<C; i++) {
			int messageCount = customers[i].getMessageCount();
			if (messageCount > maxMessageCount) {
				maxMessageCount = messageCount;
				customerName = customers[i].name;
			}
		}
		
		outstream1.printf("%s : %d\n",customerName, maxMessageCount);
		
		//The customer with the max usage of Internet
		double maxTotalMB = -1;

		for (int i=0; i<C; i++) {
			double totalMB = customers[i].getTotalMB();
			if (totalMB > maxTotalMB) {
				maxTotalMB = totalMB;
				customerName = customers[i].name;
			}
		}
		
		outstream1.printf("%s : %.2f",customerName, maxTotalMB);
		
		reader.close();
		outstream1.close();

		//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	} 
}

