/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a full-documented class named Team that stores all Player objects 
 * that belong to a particular team
 */

public class Team {
	public static final int MAX_PLAYER = 40;
	public Player[] players; //array holding the players in one team
	public int size = 0;
	
	/**
	 * Construct an instance of the Team class with no Player objects in it
	 * 
	 */
	public Team() {
		players = new Player[MAX_PLAYER];
	}
	
	/**
	 * Generate a clone of this Team
	 * 
	 * @return teamCopy
	 * return the copy of team
	 * 
	 */
	public Object clone(){
		Team teamCopy = new Team();
		for(int i = 0; i < size(); i++) {
			Player playerCopy = new Player(players[i].getName(),players[i].getNumHits(),players[i].getNumErrors());
			teamCopy.addPlayer(playerCopy, i+1);
		}
		return teamCopy;	
	}
	
	/**
	 * Compare this Team to another object for equality
	 * 
	 * @param o
	 * object to be compared
	 * 
	 * @return 
	 * true if two player are equal, false if two players are not equal
	 */
	public boolean equals(Object o) {
		if(o instanceof Team) {
			Team t = (Team)o;
			for(int i = 0; i < size()-1; i++) {
				if((players[i].equals(t.players[i]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determines the number of Players currently in this Team
	 * 
	 * @return
	 * return the number of players in the team
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Adds a Player to the team at the indicated position in the lineup
	 * 
	 * @param p
	 * the player to be added
	 * @param position
	 * the position of the player to be added
	 * 
	 * @throws FullTeamException
	 * when team is full
	 * @throws IllegalArgumentException
	 * when the position is invalid
	 *  
	 */
	public void addPlayer(Player p,int position) {
		if(size() == MAX_PLAYER) {
			throw new FullTeamException();
		}else if(position < 1||position > 40||position > size()+1) {
			throw new IllegalArgumentException();
		}else {
			for(int i = size();i > position-1;i--) {
				players[i] = players[i-1];
			}
			players[position-1]=p;
			size++;
		}
	}
	
	/**
	 * Removes a Player from the team at the indicated position in the lineup
	 * 
	 * @param position
	 * the position of player to be removed
	 * 
	 * @throws IllegalArgumentException
	 * when the position is invalid
	 * 
	 */
	public void removePlayer(int position) {
		if(position < 1||position > 40||position > size()) {
			throw new IllegalArgumentException();
		}else {
			for(int i = position - 1;i < size(); i++) {
				players[i] = players[i+1];
			}
		}
		size--;
	}
	
	/**
	 * Returns a reference to a player in the lineup at the indicated position
	 * 
	 * @param position
	 * the position of player to be shown
	 * 
	 * @return
	 * return the player of the position
	 * 
	 * @throws IllegalArgumentException
	 * when the position is invalid
	 * 
	 */
	public Player getPlayer(int position) {
		if(position < 1||position > 40||position > size()) {
			throw new IllegalArgumentException();
		}else {
			return players[position-1];
		}
	}
	
	/**
	 * Return the Player with the best value in the given statistic ("hits" or "errors")
	 * 
	 * @param stat
	 * the statistic to be shown
	 * 
	 * @return
	 * return the position of player
	 * 
	 * @throws IllegalArgumentException
	 * when the statistic is not "hits" or "errors"
	 * 
	 */
	public Player getLeader(String stat) {
		int position = 0;
		if(stat.equalsIgnoreCase("Hits")) {
			int max = players[0].getNumHits();
			for(int i = 0;i < size(); i++) {
				if(players[i].getNumHits() > max) {
					max = players[i].getNumHits();
					position = i;
				}
			}
		}else if(stat.equalsIgnoreCase("Errors")) {
			int min = players[0].getNumErrors();
			for(int i = 0;i < size(); i++) {
				if(players[i].getNumErrors() < min) {
					min = players[i].getNumErrors();
					position = i;
				}
			}
		}else {
			throw new IllegalArgumentException();
		}
		return players[position];
	}
	
	/**
	 * Prints a neatly formatted table of each Player in the Team on its own line 
	 * with its position number as shown in the sample output
	 *
	 */
	public void printAllPlayers() {
		System.out.println(toString());
	}
	
	/**
	 * Gets the String representation of this Team object, 
	 * which is a neatly formatted table of each Player in the Team on its own line 
	 * with its position number as shown in the sample output
	 * 
	 * @return
	 * the string representation of player
	 * 
	 */
	public String toString() {
		String title = String.format("%-10s%-20s%-10s%-10s", "Players#", "Name", "Hits", "Errors");
		String a = "-----------------------------------------------";
		String data = "";
		for(int i=0;i<size();i++) {
			data += String.format("%-10d%-20s%-10d%-10d", i+1, players[i].getName(), 
					players[i].getNumHits(), players[i].getNumErrors()) + "\n";
		}
		return title + "\n" + a + "\n"+ data;
		
	}	

}
