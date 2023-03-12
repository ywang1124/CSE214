/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully-documented class named PresentationManager which creates 
 * an instance of the SlideList class and provides an interface for a user to manipulate the list 
 * by adding, removing, and editing slides
 */
import java.util.Scanner;

public class PresentationManager {
	public static void main(String[] args) {
		String option;
		SlideList list = new SlideList();
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nWelcome to PresentationManager! \n");
		
		do {
			System.out.print("Please select an option:\n"
				+ "  F) Move cursor forward\n"
				+ "  B) Move cursor backward\n"
				+ "  D) Display cursor slide\n"
				+ "  E) Edit cursor slide\n"
				+ "  P) Print presentation summary\n"
				+ "  A) Append new slide to tail\n"
				+ "  I) Insert new slide before cursor\n"
				+ "  R) Remove slide at cursor\n"
				+ "  H) Reset cursor to head\n"
				+ "  Q) Quit\n\n"
				+ "Select a menu option: ");
			option = input.nextLine();
			option = option.replaceAll(" ", "");
			
			//option A: append new slide to tail
			if(option.equalsIgnoreCase("A")) {
				Slide newSlide = new Slide();
				
				System.out.print("\nEnter the slide title: ");
				String title = input.nextLine();
				try {
					newSlide.setTitle(title);
				}catch(IllegalArgumentException e) {
					System.out.println("\nInvalid title\n");
					continue;
				}
				
				System.out.print("Enter the slide duration: ");
				double duration = Double.parseDouble(input.nextLine());
				try {
					newSlide.setDuration(duration);
				}catch(IllegalArgumentException e) {
					System.out.println("\nInvalid duration\n");
					continue;
				}
				
				int number = 1;	
				String answer = null;
				
				//while loop that keep asking more bullets until user inputs no 
				//or reach the max bullets
				while(true) {
					System.out.print("Bullet "+ number + ": ");
					String bullet = input.nextLine();
					newSlide.setBullet(bullet,number);
					number++;
					if(number==6) {
			        	System.out.println("No more bullets allowed. Slide is full.");
			        	break;
			        }
					System.out.print("Add another bullet point? (y/n) ");
					answer = input.nextLine();
					if(!(answer.equalsIgnoreCase("y"))&!(answer.equalsIgnoreCase("n"))) {
						System.out.println("\nInvalid option\n");
						break;
					}else if (answer.equalsIgnoreCase("n")) {
			            break;
					}
				}		
				if(!(answer.equalsIgnoreCase("y"))&!(answer.equalsIgnoreCase("n"))) {
					continue;
				}
				
				list.appendToTail(newSlide);
				System.out.println("\nSlide \""+ title +"\" added to presentation.\n");
			
			
			//option P: print presentation summary
			}else if(option.equalsIgnoreCase("P")) {
				System.out.println("\n"+list.toString());
				
			
			//option F: move cursor forward
			}else if(option.equalsIgnoreCase("F")) {
				try {
					list.cursorForward();
					System.out.println("\nThe cursor moved forward to slide\""+ 
						list.getCursorSlide().getTitle() + "\".\n");
				} catch (EndOfListException e) {
					System.out.println("\nEnd of list cannot move forward\n");
				}
		
			//option I:insert new slide before cursor	
			}else if(option.equalsIgnoreCase("I")) {
				Slide newSlide = new Slide();
				
				System.out.print("\nEnter the slide title: ");
				String title = input.nextLine();
				try {
					newSlide.setTitle(title);
				}catch(IllegalArgumentException e) {
					System.out.println("\nInvalid title\n");
					continue;
				}
				
				System.out.print("Enter the slide duration: ");
				double duration = Double.parseDouble(input.nextLine());
				try {
					newSlide.setDuration(duration);
				}catch(IllegalArgumentException e) {
					System.out.println("\nInvalid duration\n");
					continue;
				}
				
				int number = 1;	
				String answer = null;
				
				//while loop that keep asking more bullets until input no
				//or reach the max bullets
				while(true) {
					System.out.print("Bullet "+ number + ": ");
					String bullet = input.nextLine();
					newSlide.setBullet(bullet,number);
					number++;
					if(number==6) {
			        	System.out.println("No more bullets allowed. Slide is full.");
			        	break;
			        }
					System.out.print("Add another bullet point? (y/n) ");
					answer = input.nextLine();
					if(!(answer.equalsIgnoreCase("y"))&!(answer.equalsIgnoreCase("n"))) {
						System.out.println("\nInvalid option\n");
						break;
					}else if (answer.equalsIgnoreCase("n")) {
			            break;
					}
				}
				if(!(answer.equalsIgnoreCase("y"))&!(answer.equalsIgnoreCase("n"))) {
					continue;
				}
				
				
				list.insertBeforeCursor(newSlide);
				System.out.println("\nSlide \""+ title +"\" added to presentation.\n");

		
			
			//option B: move cursor backward
			}else if(option.equalsIgnoreCase("B")) {
				try {
					list.cursorBackward();
					System.out.println("\nThe cursor moved backward to slide \""+ 
						list.getCursorSlide().getTitle() + "\".\n");
				} catch (EndOfListException e) {
					System.out.println("\nEnd of list cannot move backward \n");
				}
			
			
			//option D: display cursor slide
			}else if(option.equalsIgnoreCase("D")) {
				//print empty if no slides in the list
				if(list.getCursorSlide() == null) {
					System.out.println("\nEmpty slideshow \n");
					continue;
				}
				System.out.println("\n" + list.getCursorSlide().toString());
			
			
			//option E: edit cursor slide
			}else if(option.equalsIgnoreCase("E")) {
				//print empty if no slides in the list
				if(list.getCursorSlide() == null) {
					System.out.println("\nEmpty slideshow \n");
					continue;
				}
				
				System.out.print("\nEdit title, duration, or bullets? (t/d/b) ");
				String answer = input.nextLine();
				if(!(answer.equalsIgnoreCase("t"))&!(answer.equalsIgnoreCase("b"))
					&!(answer.equalsIgnoreCase("d"))) {
					System.out.println("\nInvalid option \n");
					continue;
				}
				
				//option b: edit bullets
				if(answer.equalsIgnoreCase("b")) {
					try {
						System.out.print("Bullet index: ");
						int index = Integer.parseInt(input.nextLine());
						list.getCursorSlide().getBullet(index);
						
						System.out.print("Delete or edit? (d/e) ");
						String move = input.nextLine();
						if(!(move.equalsIgnoreCase("d"))&!(move.equalsIgnoreCase("e"))) {
							System.out.println("\nInvalid option\n");
							continue;
						}
						
						//option e and d: edit or delete the bullet
						if(move.equalsIgnoreCase("e")) {
							System.out.print("Bullet " + index + ": ");
							String bullet = input.nextLine();
							list.getCursorSlide().setBullet(bullet, index);
							System.out.println("\nBullet "+ index +" has been edited.\n");
						}else if(move.equalsIgnoreCase("d")) {
							list.getCursorSlide().setBullet(null, index);
							list.setNumBullets(list.getNumBullets()-1);
							System.out.println("\nBullet "+ index +" has been deleted.\n");
						}
					}catch(IllegalArgumentException e) {
						System.out.println("\nInvalid index\n");
					}
				
				//option d: edit duration
				}else if(answer.equalsIgnoreCase("d")) {
					try {
						System.out.print("New duration: ");
						double duration = Double.parseDouble(input.nextLine());
						list.editCursorDuration(duration);
						list.getCursorSlide().setDuration(duration);
						System.out.println("\nDuration has been edited. \n");
					}catch(IllegalArgumentException e) {
						System.out.println("\nInvalid duration\n");
					}
				
				//option t: edit title
				}else if(answer.equalsIgnoreCase("t")) {
					try {
						System.out.print("New title: ");
						String title = input.nextLine();
						list.getCursorSlide().setTitle(title);
						System.out.println("\nTitle has been edited.\n");
					}catch(IllegalArgumentException e) {
						System.out.println("\nInvalid title\n");
					}
				}
		
			
			//option R: remove slide at cursor
			}else if(option.equalsIgnoreCase("R")) {
				if(list.getCursorSlide() == null) {
					System.out.println("\nEmpty slideshow\n");
					continue;
				}
				try {
					System.out.println("\nSlide\"" + 
					list.getCursorSlide().getTitle() + "\" has been removed\n");
					list.removeCursor();
				}catch(EndOfListException e) {
					System.out.println("\nNothing to removed.\n");
				}
			
			
			//option H: reset cursor to head
			}else if(option.equalsIgnoreCase("H")) {
				if(list.getCursorSlide() == null) {
					System.out.println("\nEmpty slideshow\n");
					continue;
				}
				list.resetCursorToHead();
				System.out.println("\nCursor has been reset to the head\n");
				
			//invalid option
			}else if(!option.equalsIgnoreCase("Q")) {
				System.out.println("\nInvalid option\n");
			}
		
		//option Q: quit
		}while(!option.equalsIgnoreCase("Q")) ;
		System.out.println("\nProgram terminating normally...");
		input.close();
	}
}
