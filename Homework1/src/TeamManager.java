/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully-documented class named TeamManager which tests the methods of the Team class 
 * and allows the user to manipulate 5 Team objects by performing operations on it.
 */
import java.util.Scanner;
public class TeamManager {
	public static final int MAX_TEAMS = 5;
	public static void main(String[] args) {
		//Initialize five empty teams
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		Team team5 = new Team();
		String option;
		Team current = team1;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to TeamManager!\n\n"
			+ "Team 1 is currently selected.\n");
		
		//do-while loop: keep looping if expected answer "Q" is not received
		do {
			//print the menu
			System.out.print("Please select an option:\n"
				+ "A)  Add Player.\n"
				+ "G)  Get Player stats. \n"
				+ "L)  Get leader in a stat. \n"
				+ "R)  Remove a player. \n"
				+ "P)  Print all players.\n"
				+ "S)  Size of team.\n"
				+ "T)  Select team \n"
				+ "C)  Clone team\n"
				+ "E)  Team equals\n"
				+ "U)  Update stat.\n"
				+ "Q)  Quit.\n\n"
				+ "Select a menu option: ");
			option = input.nextLine();
			option = option.replaceAll(" ", "");
			
			
			//option A: adding player by name, stats and position
			if(option.equalsIgnoreCase("A")) {	
				System.out.print("Enter the player's name: ");
				String name = input.nextLine();	
				System.out.print("Enter the number of hits: ");
				int numHits = Integer.parseInt(input.nextLine());	
				System.out.print("Enter the number of errors: ");
				int numErrors = Integer.parseInt(input.nextLine());	
				System.out.print("Enter the position: ");
				int position = Integer.parseInt(input.nextLine());
				
				//try-catch if no exception then add new player
				try {
					Player p = new Player(name, numHits, numErrors);
					current.addPlayer(p, position);
					System.out.println("Player added: " + p.toString());
						
				}catch(FullTeamException e) {
					System.out.println("Team is full. ");
				}catch(IllegalArgumentException e) {
					System.out.println("Invalid position for adding the new player. ");
				}
			
				
			//option G: get the player's stat by inputting position
			}else if(option.equalsIgnoreCase("G")) {
				System.out.print("Enter the position: ");
				int position = Integer.parseInt(input.nextLine());
				
				//try-catch if no exception then display information of player
				try{
					Player p = current.getPlayer(position);
					System.out.println(p.toString());
				}catch(IllegalArgumentException e) {
					System.out.println("Invalid position. ");
				}	
			
				
			//option L: get the leader of one stat
			}else if(option.equalsIgnoreCase("L")) {
				System.out.print("Enter the stat: ");
				String stat = input.nextLine();
				
				//try-catch if no exception then display the leader of the stat
				try{
					System.out.println("Leader in "+ stat + ": " + current.getLeader(stat));
				}catch(IllegalArgumentException e) {
					System.out.println("No such statistic. ");
				}
			
				
			//option R:remove player by inputting position
			}else if(option.equalsIgnoreCase("R")) {
				System.out.print("Enter the position: ");
				int position = Integer.parseInt(input.nextLine());
				
				//try-catch if no exception then remove the player
				try {
					System.out.println("Player Removed at position " + position);
					System.out.println(current.getPlayer(position).getName() + " has been removed from the team.");
					current.removePlayer(position);
				}catch(IllegalArgumentException e) {
					System.out.print("No player at position " + position + " to remove. ");
				}
			
				
			//option P: print the stats of selected team
			}else if(option.equalsIgnoreCase("P")) {
				System.out.print("Select team index: ");
				int index = Integer.parseInt(input.nextLine());
				
				if(index==1) {
					team1.printAllPlayers();
				}else if(index==2) {
					team2.printAllPlayers();
				}else if(index==3) {
					team3.printAllPlayers();
				}else if(index==4) {
					team4.printAllPlayers();
				}else if(index==5) {
					team5.printAllPlayers();
				}
				
				
			//option S: get the size of current team
			}else if(option.equalsIgnoreCase("S")) {
				System.out.println("There are "+ current.size() +" player(s) in the current Team. ");
			
				
			//option T: switch the current team to others
			}else if(option.equalsIgnoreCase("T")) {
				System.out.print("Enter team index to selected: ");
				int index = Integer.parseInt(input.nextLine());
				
				if(index==1) {
					current = team1;
					System.out.println("Team 1 has been selected. ");
				}else if(index==2) {
					current = team2;
					System.out.println("Team 2 has been selected. ");
				}else if(index==3) {
					current = team3;
					System.out.println("Team 3 has been selected. ");
				}else if(index==4) {
					current = team4;
					System.out.println("Team 4 has been selected. ");
				}else if(index==5) {
					current = team5;
					System.out.println("Team 5 has been selected. ");
				}else {
					System.out.println("Invalid index for team.");
				}
			
				
			//option C: clone one team's stats to another
			}else if(option.equalsIgnoreCase("C")) {
				System.out.print("Select team to clone from: ");
				int index1 = Integer.parseInt(input.nextLine());
				System.out.print("Select team to clone to: ");
				int index2 = Integer.parseInt(input.nextLine());
				Team t1 = null;
				Object t2;
				
				if(index1==1) {
					t1=team1;
				}else if(index1==2) {
					t1=team2;
				}else if(index1==3) {
					t1=team3;
				}else if(index1==4) {
					t1=team4;
				}else if(index1==5) {
					t1=team5;
				}
				
				t2=t1.clone();
				
				if(index2==1) {
					team1=(Team) t2;
				}else if(index2==2) {
					team2=(Team) t2;
				}else if(index2==3) {
					team3=(Team) t2;
				}else if(index2==4) {
					team4=(Team) t2;
				}else if(index2==5) {
					team5=(Team) t2;
				}
				
				System.out.println("Team " + index1 + " has been copied to team " + index2 + ".");
			
				
			//option E: find the equality of two teams
			}else if(option.equalsIgnoreCase("E")) {
				System.out.print("Select first team index: ");
				int index1 = Integer.parseInt(input.nextLine());
				System.out.print("Select second team index: ");
				int index2 = Integer.parseInt(input.nextLine());
				Team t1 = null,t2 = null;
				
				if(index1==1) {
					t1=team1;
				}else if(index1==2) {
					t1=team2;
				}else if(index1==3) {
					t1=team3;
				}else if(index1==4) {
					t1=team4;
				}else if(index1==5) {
					t1=team5;
				}
				
				if(index2==1) {
					t2=team1;
				}else if(index2==2) {
					t2=team2;
				}else if(index2==3) {
					t2=team3;
				}else if(index2==4) {
					t2=team4;
				}else if(index2==5) {
					t2=team5;
				}
				
				if (t1.equals(t2)) {
					System.out.println("These teams are equal.");
				} else {
					System.out.println("These teams are not equal.");
				}
			
				
			//option U: change one player's stats by inputting name, stat and number
			}else if(option.equalsIgnoreCase("U")) {
				System.out.print("Enter the player to update: ");
				String name = input.nextLine();
				System.out.print("Enter stat to update: ");
				String stat = input.nextLine();
				
				//return to previous inputting if expected input not received
				while(!(stat.equalsIgnoreCase("Hits"))&!(stat.equalsIgnoreCase("Errors"))) {
					System.out.println("No such statisic. ");
					System.out.print("Enter stat to update: ");
					stat = input.nextLine();
				}

				System.out.print("Enter the new number of "+ stat +": ");
				int number = Integer.parseInt(input.nextLine());
	
				boolean notFound = true;
				for(int i = 1;i < current.size()+1;i++) {
					if(current.getPlayer(i).getName().equalsIgnoreCase(name)) {
						notFound = false;
						
						//try-catch if no exception then set the new stats of player
						try{
							if(stat.equalsIgnoreCase("Hits")) {
								current.getPlayer(i).setNumHits(number);
								System.out.println("Updated " + name + " hits. ");
							}else if(stat.equalsIgnoreCase("Errors")) {
								current.getPlayer(i).setNumErrors(number);
								System.out.println("Updated " + name + " errors. ");
							}
						}catch(IllegalArgumentException e) {
							System.out.println("Invalid update error. ");
						}
					}
				}
				
				if(notFound) {
					System.out.println("Player not found.");
				}
				
				
			//return to previous question if invalid option is received
			}else if(!option.equalsIgnoreCase("Q")){
				System.out.println("Invalid option. ");
			}
			
			System.out.println("\n");

		}while(!option.equalsIgnoreCase("Q"));
		System.out.println("Program terminating normally...");
		input.close();
	}
}
