/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 *
 * a fully-documented class named Player which contains the player’s name (String), number of hits (int), 
 * and number of errors (int), provides accessor and mutator methods for 
 * each variable as well as a default constructor. 
 * For the mutator of the hits and errors variables, 
 * throws an exception if the new value is less than 0. 
 * also provides a toString() method that returns 
 * a printable representation of the player and his statistics (hits and errors).
 * 
 */
public class Player {
	private String name;
	private int numHits;
	private int numErrors;
	
	/**
	 * this is a Constructor used to create a new player
	 * 
	 * @param newPlayer
	 * the name of player
	 * @param newHit
	 * the number of hits
	 * @param newError
	 * the number of errors
	 * 
	 */
	public Player(String newPlayer, int newHit, int newError) {
		name = newPlayer;
		numHits = newHit;
		numErrors = newError;
	}
	
	/**
	 * the getter of name
	 * 
	 * @return
	 * the name of player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * the setter of name
	 * 
	 * @param newPlayer
	 * the name of new player
	 */
	public void setName(String newPlayer) {
		name = newPlayer;
	}
	
	/**
	 * the getter of number of hits
	 * 
	 * @return 
	 * the number of hits
	 * 
	 */
	public int getNumHits() {
		return numHits;
	}
	
	/**
	 * the setter of number of hits
	 * 
	 * @param newHit
	 * the number of hits of new player
	 * 
	 * @throws IllegalArgumentException
	 * when the number of hits is negative
	 * 
	 */
	public void setNumHits(int newHit) throws IllegalArgumentException {
		if (newHit<0) {
			throw new IllegalArgumentException();
		}else {
			numHits = newHit;
		}
	}
	
	/**
	 * the getter of number of errors
	 * 
	 * @return
	 * the number of errors of player
	 * 
	 */
	public int getNumErrors() {
		return numErrors;
	}
	
	/**
	 * the setter of number of errors
	 * 
	 * @param newError
	 * the number of errors of new player
	 * 
	 * @throws
	 * when the number of errors is negative
	 * 
	 */
	public void setNumErrors(int newError) {
		if(newError<0) {
			throw new IllegalArgumentException();
		}else {
			numErrors = newError;
		}
	}
	
	/**
	 * compare player to another for equality
	 * 
	 * @param 
	 * object to be compared
	 * 
	 * @return
	 * return true if two object are equal as players, return false if two object are
	 * not equal
	 * 
	 */
	public boolean equals(Object o) {
		if(o instanceof Player && getName().equalsIgnoreCase(((Player) o).getName()) 
			&& getNumHits() == ((Player) o).getNumHits()
			&& getNumErrors() == ((Player) o).getNumErrors()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Gets the String representation of this player object
	 * 
	 * @return
	 * the string representation of player
	 */
	public String toString() {
		return name + " - " + numHits + " hits, " + numErrors + " errors";
	}
}
