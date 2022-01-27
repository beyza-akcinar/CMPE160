
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This abstract class represents containers which can be stored in ports, loaded to ships from ports, and unloaded from ships to ports.
 * 
 * @author Beyza
 */
public abstract class Container {
	
	/**
	 * the ID of the container
	 */
	private int ID;
	/**
	 * a boolean value used for changing mutability of a field
	 */
	private boolean isMutable;
	/**
	 * the weight of the container
	 */
	private final int weight;
	/**
	 * fuel consumption by the container per unit weight
	 */
	private double fuelPerWeight;
	
	/**
	 * <b>Class Constructor</b> 
	 *
	 * @param ID the ID of the object
	 * @param weight the weight of the container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
		this.isMutable = true;
	}
	
	/**
	 * Method for comparing 2 Container objects in terms of their types, IDs, and weights.
	 * 
	 * @param other the second Container object
	 * @return <b>true</b> if they are equal, <b>false</b> if they are not  
	 */
	public boolean equals(Container other) {
		if (!this.getClass().equals(other.getClass())) {
			return false;
		}
		if (other instanceof Container) {
			Container c = (Container) other;
			return this.ID == c.ID && this.weight == c.weight;
		} else {
			return false;
		}
	}
	
	/**
	 * Method for calculating the fuel consumption of a container per kilometers.<br>
	 * The calculation entails multiplying the weight of the container with its fuel consumption per weight.
	 * @return <b>fuel consumption of the container per km</b>
	 */
	public double consumption() {
		return weight*this.fuelPerWeight;
	}
	
	/**
	 * Method used for changing the value of the boolean field <b>isMutable</b> to false.<br>
	 * This disables mutability of the ID field of the object, since its setter method works only if the value of isMutable is true.
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
	 * Getter method for the weight of the object.
	 * @return <b>the weight of the object</b>
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Getter method for the consumed fuel per unit weight of the object.
	 * @return <b>the fuel consumption per unit weight</b>
	 */
	public double getFuelPerWeight() {
		return fuelPerWeight;
	}

	/**
	 * Setter method for the ID field of the object.<br>
	 * Unlike usual setter methods, it only works when the value of the imMutable is true.
	 * @param ID <b>the ID of the object</b>
	 */
	public void setID(int ID) {
		if (isMutable) {
			this.ID = ID;
		}
	}
	
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

