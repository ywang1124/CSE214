/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw3
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a simple Enum named CargoStrength, which lists the strength values a cargo container may have
 * 
 * FRAGLIE containers can only support other FRAGILE containers.
 * MODERATE containers can support other MODERATE containers, as well as FRAGILE containers.
 * STURDY containers can support all other containers.
 * The number of containers above any given containers does not matter. 
 * The only restrictions are the three rules.
 * 
 */
public enum CargoStrength {
	FRAGILE, MODERATE, STURDY;
	
	/**
	 * test two cargos if the top cargo strength support the input cargo strength
	 * 
	 * @param input
	 * the cargo strength of a cargo
	 * 
	 * @return
	 * return true if cargo strength support another cargo strength
	 */
	public boolean support(CargoStrength input) {
        switch (this) {
            case FRAGILE:
                return input == FRAGILE;
            case MODERATE:
                return input == FRAGILE || input == MODERATE;
            case STURDY:
                return true;
            default:
                throw new IllegalArgumentException("Invalid strength");
        }
    }
	
	/**
	 * getter of whole name of strength
	 * 
	 * @return
	 * whole name of strength
	 */
	public String getWholeName() {
		return name();
	}
	
	/**
	 * Gets the first letter of strength
	 * 
	 * @return
	 * first letter of strength
	 */
	public String toString() {
        return name().substring(0, 1);
    }
}

