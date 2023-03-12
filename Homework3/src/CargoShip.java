/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw3
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully documented class named CargoShip. This class represents a container ship which holds stacks of containers. 
 * It must contain the following private member variables: stacks (CargoStack[]), maxHeight (int), 
 * and maxWeight (double). The CargoShip class must also feature the following public methods: 
 * void pushCargo(Cargo cargo, int stack), Cargo popCargo(int stack), and void findAndPrint(String name).
 * 
 */

import java.util.Stack;
public class CargoShip {
	private Stack<Cargo>[] stacks;//Array of container stacks on the ship
	private int maxHeight;
	private double maxWeight;
	double totalWeight = 0;
	
	/**
	 * Default Constructor
	 * 
	 * @param numStacks
	 * The number of stacks this ship can support. 
	 * This parameter should be used to initialize the stacks array to a fixed size.
	 * @param initMaxHeight
	 * The maximum height of any stack on this ship.
	 * @param initMaxWeight
	 * The maximum weight for all the cargo on the ship.
	 * 
	 * @throws IllegalArgumentException
	 * if either of the parameters are now within the appropriate bounds.
	 */
	public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) {
		if(numStacks <= 0||initMaxHeight <= 0||initMaxWeight <= 0) {
			throw new IllegalArgumentException();
		}else {
			stacks = new Stack[numStacks+1];
			for (int i = 0; i < numStacks+1; i++) {
		        stacks[i] = new Stack<Cargo>();
		    }
			maxHeight = initMaxHeight;
			maxWeight = initMaxWeight;
		}
			
	}
	
	/**
	 * Pushes a cargo container to the indicated stack on the cargo ship
	 * 
	 * @param cargo
	 * The container to place on the stack
	 * @param index
	 * The index of the stack on the ship to push cargo onto
	 * 
	 * @throws FullStackException
	 * If the stack being pushed to is at the max height
	 * @throws ShipOverweightException
	 * If cargo would make the ship exceed maxWeight and sink
	 * @throws CargoStrengthException
	 * If the cargo would be stacked on top of a weaker cargo
	 * @throws IllegalArgumentException
	 * If cargo is null or stack is not in the appropriate bounds.
	 * 
	 */
	public void pushCargo(Cargo cargo, int index) 
	  throws FullStackException, ShipOverweightException, CargoStrengthException{
		if(cargo == null || index < 0 || index > stacks.length-1) {
			throw new IllegalArgumentException();
		}else if(stacks[index].size() >= maxHeight&&index!=0) {
			throw new FullStackException();
		}else if(index!=0 && totalWeight + cargo.getWeight() > maxWeight) {
			throw new ShipOverweightException();
		}else if(stacks[index].size() != 0&&!stacks[index].peek().getStrength().support(cargo.getStrength())) {
			throw new CargoStrengthException();
		}else {
			stacks[index].push(cargo);
			if(index!=0) {
				totalWeight += cargo.getWeight();
			}
		}
	}
	
	/**
	 * Pops a cargo from one of the specified stack.
	 * 
	 * @param index
	 * The index of the stack to remove the cargo from.
	 * 
	 * @return
	 * the cargo that be removed
	 * 
	 * @throws EmptyStackException
	 * If the stack being popped from is empty
	 * @throws IllegalArgumentException
	 * If stack is not in the appropriate bounds
	 */
	public Cargo popCargo(int index) throws EmptyStackException {
	    if (index < 0 || index > stacks.length) {
	        throw new IllegalArgumentException();
	    }else if (stacks[index].isEmpty()) {
	        throw new EmptyStackException();
	    }else {
	    	if(index!=0) {
				totalWeight -= stacks[index].peek().getWeight();
			}
	    	Cargo cargo = stacks[index].pop();
	    	return cargo;
	    }
	}
	
	/**
	 * Finds and prints a formatted table of all the cargo on the ship with a given name. 
	 * If the item could not be found in the stacks, notify the user accordingly
	 * 
	 * @param name
	 * The name of the cargo to find and print.
	 */
	public void findAndPrint(String name) {
		int count = 0;
		double totalWeight = 0;
		
		for (int i = 1; i < stacks.length; i++) {
	        Stack<Cargo> stack = stacks[i];
	        for (int j = 0; j < stack.size(); j++) {
	            Cargo cargo = stack.get(j);
	            if (cargo.getName().equals(name)) {
	            	if(count==0) {
	            		System.out.println(String.format("%-9s%-8s%-9s%-9s", " Stack", "Depth", "Weight", "Strength"));
	            		System.out.println("=======+=======+========+==========");
	            	}
	                System.out.println(String.format("%-7s|%-7s|%-8s|%-9s", 
	                  "   "+i,"   "+(stack.size()-j-1), " "+Math.round(cargo.getWeight()), cargo.getStrength().getWholeName()));
	                count++;
	                totalWeight += cargo.getWeight();
	            }
	        }
	    }
		if(count == 0) {
	        System.out.println("Cargo '"+name+"' could not be found on the ship.");
	    }else {
	    	System.out.println("\nTotal Count: "+count+"\nTotal Weight: "+Math.round(totalWeight));
	    }
	}
	
	/**
	 * clear the specific stack
	 * 
	 * @param index
	 * The index of the stack to be cleared
	 * 
	 */
	public void clear(int index) {
	    while (!stacks[index].isEmpty()) {
	    	stacks[index].pop();
	    }
	}
	
	/**
	 * peek the top cargo on the specific stack
	 * 
	 * @param index
	 * the index of the stack to be peeked
	 * 
	 * @return
	 * the top cargo on the specific stack
	 * 
	 * @throws EmptyStackException
	 * If the stack being popped from is empty
	 * @throws IllegalArgumentException
	 * If stack is not in the appropriate bounds
	 */
	public Cargo peekCargo(int index) throws EmptyStackException {
	    if (index < 0 || index > stacks.length) {
	        throw new IllegalArgumentException();
	    } else if (stacks[index].isEmpty()) {
	        throw new EmptyStackException();
	    } else {
	        return stacks[index].peek();
	    }
	}
	
	//test method
	public void remove(String name) {
		//clear dock
	    stacks[0].clear();
	    System.out.println("Cleared the dock. \n"+ toString());

	    int step = 1;
	    for (int i = 1; i < 4; i++) {
	        Stack<Cargo> stack = stacks[i];
	        int j = 0;
	        while(j < stack.size()) {
	        	Cargo cargo = stack.get(j);
	            if(cargo.getName().equals(name)) {
	            	System.out.println("Step "+step+": Moved cargo from stack "+i+" to dock.");
	            	stacks[i].pop();
	            	stacks[0].push(cargo);
	                step++;
	                System.out.println(toString());
	            }else {
	            	j++;
	            }
	        }
	    }
	}
	
	/**
	 * Gets the String representation of this stack object
	 * 
	 * @return
	 * the string representation of stack
	 */
	public String toString() {
		String data = "";
		for(int i = 1;i < stacks.length; i++) {
			data += "Stack " + i + ": ";
			for(int j = 0;j < stacks[i].size();j++) {
				if(j != 0) {
					data +=  ", ";
				}
				data += stacks[i].get(j).getStrength();
			}
			data += "\n";
		}
		data += "Dock: ";
		for(int i = 0;i < stacks[0].size();i++) {
			if(i != 0) {
				data +=  ", ";
			}
			data += stacks[0].get(i).getStrength();
		}
		data += "\n\nTotal Weight = " + Math.round(totalWeight) + " / " + Math.round(maxWeight) + "\n";
		return data;
		
	}	
	
}
