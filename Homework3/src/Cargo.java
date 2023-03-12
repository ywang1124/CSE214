/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw3
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully documented class named Cargo. This class represents a container which holds the ‘cargo’ the program 
 * will place on the ship. It contains the following private member variables: name (String), weight (double) 
 * and strength (CargoStrength).
 * 
 */

public class Cargo {
	private String name;
	private double weight;
	private CargoStrength strength;
	
	/**
	 * Default Constructor
	 * 
	 */
	public Cargo() {
		
	}
	
	/**
	 * Default Constructor
	 * 
	 * @param initName
	 * Non-null name for the cargo item
	 * @param initWeight
	 * The weight for the cargo. The weight should be greater than 0, so an exception should be thrown if initWeight ≤ 0
	 * @param initStrength
	 * Either FRAGILE, MODERATE, or STURDY
	 */
	public Cargo(String initName, double initWeight, CargoStrength initStrength) {
		if(initName==null||initWeight<=0) {
			throw new IllegalArgumentException();
		}else
			name = initName;
			weight = initWeight;
			strength = initStrength;
	}
	
	/**
	 * getter of name
	 * 
	 * @return
	 * the name of cargo item
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter of weight
	 * 
	 * @return
	 * the weight of cargo item
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * getter of strength
	 * 
	 * @return
	 * the strength of cargo item
	 */
	public CargoStrength getStrength() {
		return strength;
	}
}
