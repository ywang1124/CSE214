/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw3
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully-documented class named ShipLoader which allows a user to create an instance of the CargoShip class, 
 * and also provides an interface for a user to manipulate the ship by creating, adding, and moving Cargo objects. 
 * In addition to the CargoShip, the ShipLoader utilizes another CargoStack called dock, 
 * which is the loading/unloading stack for Cargo being moved to/from the ship
 * 
 */

import java.util.Scanner;
import java.util.Stack;
public class ShipLoader {
	public static void main(String[] args) {
		String option;
		Scanner input = new Scanner(System.in);
		 
		System.out.println("Welcome to ShipLoader!\n");
		System.out.println("Cargo Ship Parameters");
		System.out.println("--------------------------------------------------");
		 
		System.out.print("Number of stacks: ");
		int numStack = Integer.parseInt(input.nextLine());
		System.out.print("Maximum height of stacks: ");
		int maxHeight = Integer.parseInt(input.nextLine());
		System.out.print("Maximum total cargo weight: ");
		double maxWeight = Double.parseDouble(input.nextLine());
		
		CargoShip ship = new CargoShip(numStack, maxHeight, maxWeight);
		Stack<Cargo>[] stacks = new Stack[numStack];
		for (int i = 0; i < numStack; i++) {
		    stacks[i] = new Stack<Cargo>();
		}
		
		System.out.println("\nCargo ship created.\n"
		  + "Pulling ship in to dock...\n"
	      + "Cargo ship ready to be loaded.");
		 
		do {
			System.out.print("\nPlease select an option:\n"
			  + "  C) Create new cargo\n"
			  + "  L) Load cargo from dock\n"
			  + "  U) Unload cargo from ship\n"
		      + "  M) Move cargo between stacks\n"
			  + "  K) Clear dock\n"
			  + "  P) Print ship stacks\n"
			  + "  S) Search for cargo\n"
		      + "  Q) Quit\n\n"
			  + "Select a menu option: ");
			option = input.nextLine();
			option = option.replaceAll(" ", "");
			
			//option c: Create new cargo
			if(option.equalsIgnoreCase("C")) {
				try {
					System.out.print("Enter the name of the cargo: ");
					String name = input.nextLine();
					System.out.print("Enter the weight of the cargo: ");
					double weight = Double.parseDouble(input.nextLine());
					System.out.print("Enter the container strength (F/M/S): ");
					String strength = input.nextLine();
					Cargo cargo = null;
					
					if(strength.equalsIgnoreCase("F")) {
						cargo = new Cargo(name, weight, CargoStrength.FRAGILE);	
					}else if(strength.equalsIgnoreCase("M")) {
						cargo = new Cargo(name, weight, CargoStrength.MODERATE);			
					}else if(strength.equalsIgnoreCase("S")) {
						cargo = new Cargo(name, weight, CargoStrength.STURDY);						
					}
					
					ship.pushCargo(cargo, 0);
					System.out.println("\nCargo '"+cargo.getName()+"' pushed onto the dock.\n");
					System.out.println(ship.toString());
					
				}catch(IllegalArgumentException e) {
					System.out.print("\nOperation failed! illegal input.\n");
				}catch(FullStackException e) {
					System.out.print("\nOperation failed! Cargo stack is at maximum height.\n");
				}catch(ShipOverweightException e) {
					System.out.print("\nOperation failed! Cargo would put ship overweight.\n");
				}catch(CargoStrengthException e) {
					System.out.print("\nOperation failed! Cargo at top of stack cannot support weight.\n");
				}
			
			//option L: Load cargo from dock <stackIndex>
			}else if(option.equalsIgnoreCase("L")) {
				try {
					System.out.print("Select the load destination stack index: ");
					int index = Integer.parseInt(input.nextLine());
					
					Cargo cargo = ship.peekCargo(0);
					ship.pushCargo(cargo, index);
					ship.popCargo(0);
					System.out.println("\nCargo '"+cargo.getName()+"' moved from dock to stack " + index + ".\n");
					System.out.println(ship.toString());
				}catch(IllegalArgumentException e) {
					System.out.print("\nOperation failed! illegal input.\n");
				}catch(FullStackException e) {
					System.out.print("\nOperation failed! Cargo stack is at maximum height.\n");
				}catch(ShipOverweightException e) {
					System.out.print("\nOperation failed! Cargo would put ship overweight.\n");
				}catch(CargoStrengthException e) {
					System.out.print("\nOperation failed! Cargo at top of stack cannot support weight.\n");
				}catch(EmptyStackException e) {
					System.out.print("\nOperation failed! Empty.\n");
				}
			
			//option u:unload cargo from input stack
			}else if(option.equalsIgnoreCase("U")) {
				try {
					System.out.print("Select the unload source stack index: ");
					int index = Integer.parseInt(input.nextLine());
					
					Cargo cargo = ship.popCargo(index);
					ship.pushCargo(cargo, 0);
					System.out.println("\nCargo '"+cargo.getName()+"' moved from stack " + index + " to dock.\n");
					System.out.println(ship.toString());
				}catch(IllegalArgumentException e) {
					System.out.print("\nOperation failed! illegal input.\n");
				}catch(FullStackException e) {
					System.out.print("\nOperation failed! Cargo stack is at maximum height.\n");
				}catch(ShipOverweightException e) {
					System.out.print("\nOperation failed! Cargo would put ship overweight.\n");
				}catch(CargoStrengthException e) {
					System.out.print("\nOperation failed! Cargo at top of stack cannot support weight.\n");
				}catch(EmptyStackException e) {
					System.out.print("\nOperation failed! Empty.\n");
				}
		
			//option m: move cargo between stacks 
			}else if(option.equalsIgnoreCase("M")) {
				try {
					System.out.print("Source stack index: ");
					int source = Integer.parseInt(input.nextLine());
					System.out.print("Destination stack index: ");
					int destination = Integer.parseInt(input.nextLine());
					
					Cargo cargo = ship.peekCargo(source);
					ship.pushCargo(cargo, destination);
					ship.popCargo(source);
					System.out.println("Cargo '"+cargo.getName()+"' moved from stack " + source + " to stack " + destination + ".");
					System.out.println(ship.toString());
				}catch(IllegalArgumentException e) {
					System.out.print("\nOperation failed! illegal input.\n");
				}catch(FullStackException e) {
					System.out.print("\nOperation failed! Cargo stack is at maximum height.\n");
				}catch(ShipOverweightException e) {
					System.out.print("\nOperation failed! Cargo would put ship overweight.\n");
				}catch(CargoStrengthException e) {
					System.out.print("\nOperation failed! Cargo at top of stack cannot support weight.\n");
				}catch(EmptyStackException e) {
					System.out.print("\nOperation failed! Empty.\n");
				}
			
			//option K: clear dock
			}else if(option.equalsIgnoreCase("K")) {
				ship.clear(0);
				System.out.print("\nDock cleared. \n");
				System.out.println("\n" + ship.toString());
			
			//option P: print skip stacks
			}else if(option.equalsIgnoreCase("P")) {
				System.out.println("\n" + ship.toString());
			
			//option S: Search for cargo <name>
			}else if(option.equalsIgnoreCase("S")) {
				System.out.print("Enter the name of the cargo: ");
				String name = input.nextLine();
				System.out.print("\nCargo '"+name+"' found at the following locations: \n");
				ship.findAndPrint(name);
			
			//option R: remove cargo with specific name
			}else if(option.equalsIgnoreCase("R")) {
				System.out.print("What item would you like to remove? ");
				String name = input.nextLine();
				ship.clear(0);
				System.out.print("\nDock cleared. \n");	
				System.out.println("Removing cargo '" + name + "'.");
				
			//if the option is invalid	
			}else if(!option.equalsIgnoreCase("Q")) {
				System.out.println("\nInvalid option.\n");
			}
		
		//option Q: quit
		}while(!option.equalsIgnoreCase("Q")) ;
			System.out.println("\nProgram terminating normally...");
			input.close(); 
	 }
	
}
