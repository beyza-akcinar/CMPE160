
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import interfaces.IShip;
import main.Main;
import containers.*;
import ports.Port;

/**
 * This class represents ships which can load containers currently stored in ports, unload containers to ports, sail from port to port. 
 * This actions can happen only if weight and container capacities of the ship are not exceeded, and there is enough fuel to cover the distance between the ports.
 * 
 * @author Beyza
 */
public class Ship implements IShip {
	
	/**
	 * the ID of the object
	 */
	private int ID;
	/**
	 * a boolean value used for changing mutability of a field
	 */
	private boolean isMutable;
	/**
	 * the remaining amount of fuel of the ship
	 */
	private double fuel;
	/**
	 * the port which the ship is currently in
	 */
	private Port currentPort;
	/**
	 * the total weight capacity of the ship
	 */
	private int totalWeightCapacity;
	/**
	 * the current weight on the ship
	 */
	private int currentWeight;
	/**
	 * the maximum number of containers that the ship can carry
	 */
	private int maxNumberOfAllContainers;
	/**
	 * the maximum number of heavy containers that the ship can carry
	 */
	private int maxNumberOfHeavyContainers;
	/**
	 * the maximum number of refrigerated containers that the ship can carry
	 */
	private int maxNumberOfRefrigeratedContainers;
	/**
	 * the maximum number of liquid containers that the ship can carry
	 */
	private int maxNumberOfLiquidContainers;
	/**
	 * the fuel consumption of the ship per kilometers
	 */
	private double fuelConsumptionPerKM;
	/**
	 * an ArrayList containing all the containers currently in the ship
	 */
	private ArrayList<Container> currentContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the heavy containers currently in the ship
	 */
	private ArrayList<Container> heavyContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the liquid containers currently in the ship
	 */
	private ArrayList<Container> liquidContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the refrigerated containers currently in the ship
	 */
	private ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the basic containers currently in the ship
	 */
	private ArrayList<Container> basicContainers = new ArrayList<Container>();
	
	/**
	 * <b>Class Constructor</b> 
	 *
	 * @param ID the ID of the object
	 * @param p the current port of the ship
	 * @param totalWeightCapacity the total weight capacity of the ship
	 * @param maxNumberOfAllContainers the maximum number of containers that the ship can carry
	 * @param maxNumberOfHeavyContainers the maximum number of heavy containers that the ship can carry
	 * @param maxNumberOfRefrigeratedContainers the maximum number of refrigerated containers that the ship can carry
	 * @param maxNumberOfLiquidContainers the maximum number of liquid containers that the ship can carry
	 * @param fuelConsumptionPerKM  the fuel consumption of the ship per kilometers
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		p.getCurrent().add(this);
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		this.fuel = 0.0;
		this.isMutable = true;
	}
	
	/**
	 * Method for changing the current port of the ship.<br>
	 * This method works only if the current fuel in the ship is enough for covering the distance between the ports.
	 * @param p the next port of the ship
	 */
	public boolean sailTo(Port p) {
		if (this.currentPort.equals(p)) {
			this.currentPort.outgoingShip(this);
			return true;
		}
		double neededFuel = 0.0;
		double distance = this.currentPort.getDistance(p);
		for (Container c : this.currentContainers) {
			neededFuel += c.consumption()*distance;
		}
		neededFuel += this.fuelConsumptionPerKM*distance;
		if (this.fuel >= neededFuel) {
			p.incomingShip(this);
			this.currentPort.outgoingShip(this);
			this.currentPort.getCurrent().remove(this);
			this.currentPort = p;
			this.fuel -= neededFuel;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Method for refueling the ship.
	 * @param newFuel the fuel to be added to the current amount of fuel
	 */
	public void reFuel(double newFuel) {
		this.fuel += newFuel;
	}
	
	/**
	 * Method for loading a specified container to the ship.<br>
	 * In order for the loading to happen, this method checks for the following:<br>
	 * 1. The container and the ship should currently be in the same port.<br>
	 * 2. The limitations of the ship for the total weight and the maximum number of specified containers should not be exceeded.
	 * @param cont the container to load from the ship
	 * @return <b>true</b> if the loading is successful, <b>false</b> if it is not
	 */
	public boolean load(Container cont) {
		if(this.getCurrentPort().getContainers().contains(cont)) {
			if (this.getMaxNumberOfAllContainers() < 1 + this.currentContainers.size()) {
				return false;
			}
			if (this.totalWeightCapacity < cont.getWeight() + this.currentWeight) {
				return false;
			}	
			if (cont instanceof HeavyContainer) {
				if (this.getMaxNumberOfHeavyContainers() < 1 + this.heavyContainers.size()) {
					return false;
				}
			}
			if (cont instanceof LiquidContainer) {
				if (this.getMaxNumberOfLiquidContainers() < 1 + this.liquidContainers.size()) {
					return false;
				}
			} else if (cont instanceof RefrigeratedContainer) {
				if (this.getMaxNumberOfRefrigeratedContainers() < 1 + this.refrigeratedContainers.size()) {
					return false;
				}
			}
		} else {
			return false;
		} 
		this.currentContainers.add(cont);
		this.currentPort.getContainers().remove(cont);
		
		if (cont instanceof BasicContainer) {
			this.currentPort.getBasicContainers().remove(cont);
		} else if (cont instanceof LiquidContainer) {
			this.currentPort.getLiquidContainers().remove(cont);
			this.currentPort.getHeavyContainers().remove(cont);
		} else if (cont instanceof RefrigeratedContainer) {
			this.currentPort.getRefrigeratedContainers().remove(cont);
			this.currentPort.getHeavyContainers().remove(cont);
		} else {
			this.currentPort.getHeavyContainers().remove(cont); 
		}	
		
		this.currentWeight += cont.getWeight();
		if (cont instanceof BasicContainer) {
			this.basicContainers.add(cont);
		} else if (cont instanceof LiquidContainer) {
			this.liquidContainers.add(cont);
			this.heavyContainers.add(cont); 
		} else if (cont instanceof RefrigeratedContainer) {
			this.refrigeratedContainers.add(cont);
			this.heavyContainers.add(cont); 
		} else {
			this.heavyContainers.add(cont); 
		}	
		return true;
	}
	
	/**
	 * Method for unloading a specified container from the ship after checking whether it is currently in the ship.
	 * @param cont the container to unload from the ship
	 * @return <b>true</b> if the unloading is successful, <b>false</b> if it is not
	 */
	public boolean unLoad(Container cont) {
		if(this.getCurrentContainers().contains(cont)) {
			this.currentContainers.remove(cont);
			this.currentPort.getContainers().add(cont);
			
			if (cont instanceof BasicContainer) {
				this.currentPort.getBasicContainers().add(cont);
			} else if (cont instanceof LiquidContainer) {
				this.currentPort.getLiquidContainers().add(cont);
				this.currentPort.getHeavyContainers().add(cont);
			} else if (cont instanceof RefrigeratedContainer) {
				this.currentPort.getRefrigeratedContainers().add(cont);
				this.currentPort.getHeavyContainers().add(cont);
			} else {
				this.currentPort.getHeavyContainers().add(cont);
			}
			
			this.currentWeight -= cont.getWeight();
			if (cont instanceof BasicContainer) {
				this.basicContainers.remove(cont);
			} else if (cont instanceof LiquidContainer) {
				this.liquidContainers.remove(cont);
				this.heavyContainers.remove(cont);
			} else if (cont instanceof RefrigeratedContainer) {
				this.refrigeratedContainers.remove(cont);
				this.heavyContainers.remove(cont);
			} else {
				this.heavyContainers.remove(cont);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Method used for changing the value of the boolean field <b>isMutable</b> to false.<br>
	 * This enables making the ID field of the object immutable, since its setter method works only if the value of isMutable is true.
	 */
	public void makeImmutable() {
		this.isMutable = false;
	}
	
	/**
	 * Method for sorting a Container ArrayList by the ID of the containers.
	 * @param c the ArrayList to be sorted
	 * @return <b>sorted container ArrayList</b>
	 */
	public ArrayList<Container> containerIDsort(ArrayList<Container> c) {
		int N = c.size();
		ArrayList<Container> sortedContainers = new ArrayList<Container>(c);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				if (sortedContainers.get(j).getID() > sortedContainers.get(j+1).getID()) {
					Container temp = sortedContainers.get(j);
					sortedContainers.set(j, sortedContainers.get(j+1));
					sortedContainers.set(j+1, temp);
				}
			}
		}
		return sortedContainers;
	}
	
	/**
	 * Getter method for the ID of the ship.
	 * @return <b>the ID of the object</b>
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Getter method for the amount of remaining fuel of the ship.
	 * @return <b>the amount of remaining fuel of the ship</b>
	 */
	public double getFuel() {
		return fuel;
	}

	/**
	 * Getter method for the the port which the ship is currently in.
	 * @return <b>the current port of the ship</b>
	 */
	public Port getCurrentPort() {
		return currentPort;
	}

	/**
	 * Getter method for the total weight capacity of the ship.
	 * @return <b>the total weight capacity of the ship</b>
	 */
	public int getTotalWeightCapacity() {
		return totalWeightCapacity;
	}

	/**
	 * Getter method for the current weight of the ship.
	 * @return <b>the current weight of the ship</b>
	 */
	public int getCurrentWeight() {
		return currentWeight;
	}

	/**
	 * Getter method for the maximum number of containers that the ship can carry.
	 * @return <b>the maximum number of containers</b>
	 */
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}

	/**
	 * Getter method for the maximum number of heavy containers that the ship can carry.
	 * @return <b>the maximum number of heavy containers</b>
	 */
	public int getMaxNumberOfHeavyContainers() {
		return maxNumberOfHeavyContainers;
	}

	/**
	 * Getter method for the maximum number of refrigerated containers that the ship can carry.
	 * @return <b>the maximum number of refrigerated containers</b>
	 */
	public int getMaxNumberOfRefrigeratedContainers() {
		return maxNumberOfRefrigeratedContainers;
	}

	/**
	 * Getter method for the maximum number of liquid containers that the ship can carry.
	 * @return <b>the maximum number of liquid containers</b>
	 */
	public int getMaxNumberOfLiquidContainers() {
		return maxNumberOfLiquidContainers;
	}

	/**
	 * Getter method for the fuel consumption the ship per kilometers.
	 * @return <b> fuel consumption of the ship per km</b>
	 */
	public double getFuelConsumptionPerKM() {
		return fuelConsumptionPerKM;
	}
	
	/**
	 * Getter method for the currentContainers ArrayList sorted by the ID of the objects.
	 * @return <b>currentContainers ArrayList</b>
	 */
	public ArrayList<Container> getCurrentContainers() {
		int N = currentContainers.size();
		ArrayList<Container> sortedCurrentContainers = new ArrayList<Container>(currentContainers);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				if (sortedCurrentContainers.get(j).getID() > sortedCurrentContainers.get(j+1).getID()) {
					Container temp = sortedCurrentContainers.get(j);
					sortedCurrentContainers.set(j, sortedCurrentContainers.get(j+1));
					sortedCurrentContainers.set(j+1, temp);
				}
			}
		}
		return sortedCurrentContainers;
	}
	
	/**
	 * Getter method for the currentContainers ArrayList.
	 * @return <b>currentContainers ArrayList</b>
	 */
	public ArrayList<Container> getContainers() {
		return currentContainers;
	}

	/**
	 * Getter method for the refrigeratedContainers ArrayList.
	 * @return <b>refrigeratedContainers ArrayList</b>
	 */
	public ArrayList<Container> getRefrigeratedContainers() {
		return refrigeratedContainers;
	}

	/**
	 * Getter method for the heavyContainers ArrayList.
	 * @return <b>heavyContainers ArrayList</b>
	 */
	public ArrayList<Container> getHeavyContainers() {
		return heavyContainers;
	}

	/**
	 * Getter method for the liquidContainers ArrayList.
	 * @return <b>liquidContainers ArrayList</b>
	 */
	public ArrayList<Container> getLiquidContainers() {
		return liquidContainers;
	}

	/**
	 * Getter method for the basicContainers ArrayList.
	 * @return <b>basicContainers ArrayList</b>
	 */
	public ArrayList<Container> getBasicContainers() {
		return basicContainers;
	}

	/**
	 * Setter method for the <b>ID</b> of the object.<br>
	 * Unlike usual setter methods, it only works when the value of the imMutable is true.
	 * @param ID the ID of the object
	 */
	public void setID(int ID) {
		if (isMutable) {
			this.ID = ID;
		}
	}

	/**
	 * Setter method for the amount of <b>fuel</b> that the ship has.
	 * @param fuel the amount of fuel
	 */
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	/**
	 * Setter method for the current <b>weight</b> of the ship.
	 * @param currentWeight weight
	 */
	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	/**
	 * Setter method for the <b>port</b> that the ship is resting currently.
	 * @param currentPort the port
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

