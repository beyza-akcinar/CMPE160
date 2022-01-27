
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import java.util.ArrayList;
import interfaces.IPort;
import ships.Ship;
import containers.*;

/**
 * This class represents ports. 
 * Ports can store containers unloaded from ships.
 * 
 * @author Beyza
 */
public class Port implements IPort {
	
	/**
	 * the ID of the object
	 */
	private int ID;
	/**
	 * a boolean value used for changing mutability of a field
	 */
	private boolean isMutable;
	/**
	 * the x coordinate of the object
	 */
	private final double X;
	/**
	 * the y coordinate of the object
	 */
	private final double Y;
	/**
	 * an ArrayList containing all containers 
	 */
	private ArrayList<Container> containers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the heavy containers currently in the port
	 */
	private ArrayList<Container> heavyContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the liquid containers currently in the port
	 */
	private ArrayList<Container> liquidContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the refrigerated containers currently in the port
	 */
	private ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the basic containers currently in the port
	 */
	private ArrayList<Container> basicContainers = new ArrayList<Container>();
	/**
	 * an ArrayList containing all the ships having previously been to the port
	 */
	private ArrayList<Ship> history = new ArrayList<Ship>();
	/**
	 * an ArrayList containing all the ships currently in the port
	 */
	private ArrayList<Ship> current = new ArrayList<Ship>();
	
	/**
	 * <b>Class Constructor</b> 
	 *
	 * @param ID the ID of the object
	 * @param X the x coordinate of the object
	 * @param Y the y coordinate of the object
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
		isMutable = true;
	}
	
	/**
	 * Method for adding the ships which are currently arriving to the port to the current ArrayList.<br>
	 * This method does not do anything if the Ship has already been added to the current ArrayList.
	 * @param s the Ship arriving the port
	 */
	public void incomingShip(Ship s) {
		if (!current.contains(s)) {
			current.add(s);
		}
	}
	
	/**
	 * Method for adding the ships which have previously been to the port to the history ArrayList.<br>
	 * This method does not do anything if the Ship has already been added to the history ArrayList.
	 * @param s the Ship leaving the port
	 */
	public void outgoingShip(Ship s) {
		if (!history.contains(s)) {
			history.add(s);
		}
	}
	
	/**
	 * Method for calculating the distance between 2 Port objects by using their x and y coordinates.
	 * @param other the second Port
	 * @return <b>the distance calculated</b>
	 */
	public double getDistance(Port other) {
		double dx = this.X - other.X;
		double dy = this.Y - other.Y;
		return Math.pow(Math.pow(dx, 2) + Math.pow(dy, 2), 0.5);
	}
	
	/**
	 * Method used for changing the value of the boolean field <b>isMutable</b> to false.<br>
	 * This enables making the ID field of the object immutable, since its setter method works only if the value of isMutable is true.
	 */
	public void makeImmutable() {
		this.isMutable = false;
	}


	/**
	 * Getter method for the ID of the object.
	 * @return <b>the ID of the object</b>
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Getter method for the X coordinate of the object.
	 * @return <b>the x coordinate of the object</b>
	 */
	public double getX() {
		return X;
	}

	/**
	 * Getter method for the Y coordinate of the object.
	 * @return <b>the y coordinate of the object</b>
	 */
	public double getY() {
		return Y;
	}

	/**
	 * Getter method for the containers ArrayList.
	 * @return <b>containers ArrayList</b>
	 */
	public ArrayList<Container> getContainers() {
		return containers;
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
	 * Method for sorting a Ship ArrayList by the ID of the ships.
	 * @param s the ArrayList to be sorted
	 * @return <b>sorted ship ArrayList</b>
	 */
	public ArrayList<Ship> shipIDsort(ArrayList<Ship> s) {
		int N = s.size();
		ArrayList<Ship> sortedShips = new ArrayList<Ship>(s);

		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				if (sortedShips.get(j).getID() > sortedShips.get(j+1).getID()) {
					Ship temp = sortedShips.get(j);
					sortedShips.set(j, sortedShips.get(j+1));
					sortedShips.set(j+1, temp);
				}
			}
		}
		return sortedShips;
	}

	/**
	 * Getter method for the history ArrayList.
	 * @return <b>history ArrayList</b>
	 */
	public ArrayList<Ship> getHistory() {
		return history;
	}

	/**
	 * Getter method for the current ArrayList.
	 * @return <b>current ArrayList</b>
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
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
	 * Setter method for the ID field of the object.<br>
	 * Unlike usual setter methods, it only works when the value of the imMutable is true.
	 * @param ID the ID of the object
	 */
	public void setID(int ID) {
		if (isMutable) {
			this.ID = ID;
		}
	}

}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

