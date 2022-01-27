
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import ports.Port;
import ships.Ship;
import containers.*;

/**
 * This class includes the main method of the Ports and Ships project.
 * 
 * @author Beyza
 */
public class Main {
	/**
	 * This is the main method for the project.
	 * @param args command line parameters
	 * @throws FileNotFoundException in some circumstance
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		/**
		 * An ArrayList containing all the Container objects created during simulation.
		 */
		final ArrayList<Container> allContainers = new ArrayList<Container>();
		/**
		 * An ArrayList containing all the Port objects created during simulation.
		 */
		final ArrayList<Port> allPorts = new ArrayList<Port>();
		/**
		 * An ArrayList containing all the Ship objects created during simulation.
		 */
		final ArrayList<Ship> allShips = new ArrayList<Ship>();
		
		// input operations
		
		int N = in.nextInt();
		
		for (int i=0; i<N; i++) {
			String action = in.next();
			
			if (action.equals("1")) {
				int portID = in.nextInt();
				int weight = in.nextInt();
				if (in.hasNext("L")) {
					in.next();
					LiquidContainer newLC = new LiquidContainer(0, weight);
					allContainers.add(newLC);
					newLC.setID(allContainers.indexOf(newLC));
					allPorts.get(portID).getContainers().add(newLC);
					allPorts.get(portID).getLiquidContainers().add(newLC);
					allPorts.get(portID).getHeavyContainers().add(newLC);
					newLC.makeImmutable();
						
				} else if (in.hasNext("R")) {
					in.next();
					RefrigeratedContainer newRC = new RefrigeratedContainer(0, weight);
					allContainers.add(newRC);
					newRC.setID(allContainers.indexOf(newRC));
					allPorts.get(portID).getContainers().add(newRC);
					allPorts.get(portID).getRefrigeratedContainers().add(newRC);
					allPorts.get(portID).getHeavyContainers().add(newRC);
					newRC.makeImmutable();
				
				} else {
					if (weight <= 3000) {
						BasicContainer newBC = new BasicContainer(0, weight);
						allContainers.add(newBC);
						newBC.setID(allContainers.indexOf(newBC));
						allPorts.get(portID).getContainers().add(newBC);
						allPorts.get(portID).getBasicContainers().add(newBC);
						newBC.makeImmutable();
						
					} else if (weight > 3000) {
						HeavyContainer newHC = new HeavyContainer(0, weight);
						allContainers.add(newHC);
						newHC.setID(allContainers.indexOf(newHC));
						allPorts.get(portID).getContainers().add(newHC);
						allPorts.get(portID).getHeavyContainers().add(newHC);
						newHC.makeImmutable();
					}
				}
				
			} else if (action.equals("2")) {
				int portID = in.nextInt();
				int maxWeight = in.nextInt();
				int maxAC = in.nextInt();
				int maxHC = in.nextInt();
				int maxRC= in.nextInt();
				int maxLC = in.nextInt();
				double fuelPerKM = in.nextDouble();
				Ship newShip = new Ship(0, allPorts.get(portID), maxWeight, maxAC, maxHC, maxRC, maxLC, fuelPerKM);
				allShips.add(newShip);
				newShip.setID(allShips.indexOf(newShip));
				newShip.makeImmutable();
				
			} else if (action.equals("3")) {
				double X = in.nextDouble();
				double Y = in.nextDouble();
				Port newPort = new Port(0, X, Y);
				allPorts.add(newPort);
				newPort.setID(allPorts.indexOf(newPort));
				newPort.makeImmutable();
				
			} else if (action.equals("4")) {
				int shipID = in.nextInt();
				int containerID = in.nextInt();
				allShips.get(shipID).load(allContainers.get(containerID));
			
			} else if (action.equals("5")) {
				int shipID = in.nextInt();
				int containerID = in.nextInt();
				allShips.get(shipID).unLoad(allContainers.get(containerID));
				
			} else if (action.equals("6")) {
				int shipID = in.nextInt();
				int portID = in.nextInt();
				allShips.get(shipID).sailTo(allPorts.get(portID));
				
			} else if (action.equals("7")) {
				int shipID = in.nextInt();
				double newFuel = in.nextDouble();
				allShips.get(shipID).reFuel(newFuel);	
			}
		}
		
		// output operations
		
		for (Port p : allPorts) {
			out.printf("Port " + p.getID() + ": (%.2f, %.2f)\n", p.getX(), p.getY());
		
			if (p.getBasicContainers().size() != 0) {
				out.print("  BasicContainer:");
				for (Container c : p.containerIDsort(p.getBasicContainers())) {
					out.print(" " + c.getID());
				}
				out.print("\n");
			}
			p.getHeavyContainers().removeAll(p.getLiquidContainers());
			p.getHeavyContainers().removeAll(p.getRefrigeratedContainers());
			if (p.getHeavyContainers().size() != 0) {
				out.print("  HeavyContainer:");
				for (Container c : p.containerIDsort(p.getHeavyContainers())) {
						out.print(" " + c.getID());
				}
				out.print("\n");
			}
			if (p.getRefrigeratedContainers().size() != 0) {
				out.print("  RefrigeratedContainer:");
				for (Container c : p.containerIDsort(p.getRefrigeratedContainers())) {
					out.print(" " + c.getID());
				}
				out.print("\n");
			}
			if (p.getLiquidContainers().size() != 0) {
				out.print("  LiquidContainer:");
				for (Container c : p.containerIDsort(p.getLiquidContainers())) {
					out.print(" " + c.getID());
				}
				out.print("\n");
			}
			
			for (Ship s : p.shipIDsort(p.getCurrent())) {
				out.printf("  Ship " + s.getID() + ": %.2f\n",s.getFuel());
				
				if (s.getBasicContainers().size() != 0) {
					out.print("    BasicContainer:");
					for (Container c : s.containerIDsort(s.getBasicContainers())) {
						out.print(" " + c.getID());
					}
					out.print("\n");
				}
				s.getHeavyContainers().removeAll(s.getLiquidContainers());
				s.getHeavyContainers().removeAll(s.getRefrigeratedContainers());
				if (s.getHeavyContainers().size() != 0) {
					out.print("    HeavyContainer:");
					for (Container c : s.containerIDsort(s.getHeavyContainers())) {
						out.print(" " + c.getID());
					}
					out.print("\n");
				}
				if (s.getRefrigeratedContainers().size() != 0) {
					out.print("    RefrigeratedContainer:");
					for (Container c : s.containerIDsort(s.getRefrigeratedContainers())) {
						out.print(" " + c.getID());
					}
					out.print("\n");
				}
				if (s.getLiquidContainers().size() != 0) {
					out.print("    LiquidContainer:");
					for (Container c : s.containerIDsort(s.getLiquidContainers())) {
						out.print(" " + c.getID());
					}
					out.print("\n");
				}
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

