
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This class represents heavy containers.
 * 
 * @author Beyza
 */
public class HeavyContainer extends Container {
	
	/**
	 * the fuel consumption by the container per unit weight
	 */
	private final double fuelPerWeight;
	
	/**
	 * <b>Class Constructor</b> 
	 *
	 * @param ID the ID of the object
	 * @param weight weight of the container
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelPerWeight = 3.00;
	}
	
	/**
	 * Method for calculating the fuel consumption of a heavy container per kilometers.<br>
	 * The calculation entails multiplying the weight of the container with its fuel consumption per weight.
	 * @return <b>fuel consumption of the container per km</b>
	 */
	@Override
	public double consumption() {
		return this.getWeight()*this.fuelPerWeight;
	}
	
	/**
	 * Getter method for the consumed fuel per unit weight of the object.
	 * @return <b>the fuel consumption per unit weight of the container</b>
	 */
	@Override
	public double getFuelPerWeight() {
		return fuelPerWeight;
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

