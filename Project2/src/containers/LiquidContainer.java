
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
 * This class represents liquid containers, a type of heavy container. 
 * 
 * @author Beyza
 */
public class LiquidContainer extends HeavyContainer {
	
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
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelPerWeight = 4.00;
	}
	
	/**
	 * Method for calculating the fuel consumption of a liquid container per kilometers.<br>
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

