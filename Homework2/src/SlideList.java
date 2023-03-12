/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully-documented class named SlideList which implements a double linked-list data structure. 
 * The SlideList should maintain a list of Slides 
 * by chaining a series of SlideListNodes between a head and a tail reference. 
 * In addition, a cursor should be provided to allow a user to traverse the list, 
 * selecting individual SlideListNodes to allow for insertion, deletion, and manipulation of the Slides 
 * Lastly, the class should provide methods to query information about the list, 
 * such as it’s size, the total duration of all Slides in the list, 
 * and the total bullet points of all Slides in the list.
 */
public class SlideList {
	private SlideListNode head;
	private SlideListNode tail;
	private SlideListNode cursor;
	public int size = 0;
	public double duration = 0;
	public int numBullets = 0;
	
	/**
	 * Default constructor which initializes this object to an empty list of Slides
	 */
	public SlideList() {
		head = null;
		tail = null;
		cursor = null;
	}
	
	/**
	 * Returns the total number of Slides in the slideshow
	 * 
	 * @return
	 * The count of all Slides in the slideshow
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns the total duration of the slideshow
	 * 
	 * @return
	 * The sum of all individual Slide durations in the slideshow
	 */
	public double getDuration() {
		return duration;
	}
	
	public void editCursorDuration(double newDuration) {
		duration -= (cursor.getData().getDuration() - newDuration);
	}
	
	/**
	 * Returns the total number of bullet points in the slideshow.
	 * 
	 * @return
	 * The sum of all bullet points of all individual Slides in the slideshow
	 */
	public int getNumBullets() {
		return numBullets;
	}
	
	/**
	 * Updates the new number of bullets
	 * 
	 * @param newNumBullets
	 * the new number of bullets updated to the slide
	 */
	public void setNumBullets(int newNumBullets) {
		numBullets = newNumBullets;
	}
	
	/**
	 * Gets the reference to the Slide wrapped by the SlideListNode 
	 * currently referenced by cursor
	 * 
	 * @return
	 * The reference of the Slide wrapped by the SlideListNode currently referenced by cursor.
	 * If the cursor is null, then this method should return null as well
	 */
	public Slide getCursorSlide() {
		if(cursor == null) {
			return null;
		}else {
			return cursor.getData();
		}
	}
	
	/**
	 * Returns the cursor to the start of the list.
	 * 
	 * If head is not null, the cursor now references the first SlideListNode in this list.
     * If head is null, the cursor is set to null as well (there are no Slides in this list).
	 */
	public void resetCursorToHead() {
		if(head == null) {
			cursor = null;
		}else {
			cursor = head;
		}
	}
	
	/**
	 * Moves the cursor to select the next SlideListNode in the list.
	 * 
	 * @throws EndOfListException
	 * If the cursor is at the tail of the list
	 */
	public void cursorForward() {
		if(cursor == tail) {
			throw new EndOfListException();
		}else {
			cursor = cursor.getNext();
		}
	}
	
	/**
	 * Moves the cursor to select the previous SlideListNode in the list
	 * 
	 * @throws EndOfListException
	 * If the cursor is at the head of the list
	 */
	public void cursorBackward() {
		if(cursor == head) {
			throw new EndOfListException();
		}else {
			cursor = cursor.getPrev();
		}
	}
	
	/**
	 * Inserts the indicated Slide before the cursor
	 * 
	 * @param newSlide
	 * The Slide object to be wrapped and inserted into the list before the cursor
	 * 
	 * @throws IllegalArgumentException
	 * if newSlide is null
	 */
	public void insertBeforeCursor(Slide newSlide) {
		SlideListNode newNode = new SlideListNode(newSlide);
		if(newSlide == null) {
			throw new IllegalArgumentException();
		}else {
			if(cursor == null) {
				head = newNode;
				tail = newNode;
				cursor = newNode;
			}else {
				if(cursor == head) {
					newNode.setNext(head);
					head.setPrev(newNode);
					head = newNode;
				}else {
					cursor.getPrev().setNext(newNode);
					newNode.setNext(cursor);
					newNode.setPrev(cursor.getPrev());
					cursor.setPrev(newNode);
				}
				cursor = newNode;
			}
			size++;
			duration += newSlide.getDuration();
			numBullets += newSlide.getNumBullets();
		}
	}
	
	/**
	 * Inserts the indicated Slide after the tail of the list.
	 * 
	 * @param newSlide
	 * The Slide object to be wrapped and inserted into the list after the tail of the list.
	 * 
	 * @throws IllegalArgumentException
	 * Thrown if newSlide is null
	 */
	public void appendToTail(Slide newSlide) {
		SlideListNode newNode = new SlideListNode(newSlide);
		if(newSlide == null) {
			throw new IllegalArgumentException();
		}else {
			if(tail == null) {
				head = newNode;
				tail = newNode;
				cursor = newNode;
			}else {
				tail.setNext(newNode);
				newNode.setPrev(tail);
				tail = newNode;
			}
			size++;
			duration += newSlide.getDuration();
			numBullets += newSlide.getNumBullets();
		}
		
	}
	
	/**
	 * Removes the SlideListNode referenced by cursor and returns the Slide inside
	 * 
	 * @return
	 * The reference to the Slide contained within the SlideListNode 
	 * which was just removed from the list
	 * 
	 * @throws EndOfListException
	 * Thrown if cursor is null
	 */
	public Slide removeCursor() {
		SlideListNode temp = cursor;
		if(cursor == null) {
			throw new EndOfListException();
		}else {
			duration -= cursor.getData().getDuration();
			numBullets -= cursor.getData().getNumBullets();
			
			if(cursor == head && cursor == tail) {
				head = null;
				tail = null;
				cursor = null;
			}else if(cursor == head) {
				head = cursor.getNext();
				cursor = head;
			}else if(cursor == tail){
				tail = cursor.getPrev();
				cursor = tail;
			}else {
				cursor.getPrev().setNext(cursor.getNext());
				cursor.getNext().setPrev(cursor.getPrev());
				cursor = cursor.getPrev();
			}
		}
		size--;
		return temp.getData();
	}
	
	/**
	 * Gets the String representation of this SlideList object
	 * 
	 * @return
	 * the string representation of SlideList
	 */
	public String toString() {
		SlideListNode newNode = head;
		String title = "Slideshow Summary: \n";
		String a = "==============================================\n";
		String b = String.format("%-10s%-15s%-10s%-10s", "  Slide", "Title", "Duration", "Bullets") + "\n";
		String c = "----------------------------------------------\n";
		String total = "Total: "+ size + " slide(s), " + String.format("%.1f", duration) + " minute(s), " + numBullets + " bullet(s)\n";
		String data = "";
		for (int i = 1; i < size + 1; i++) {
			if (newNode == cursor) { 
				data = data + ("-> ");
			}else {
				data = data + ("   ");
			}
			Slide slide = newNode.getData();
			data = data + String.format("%-7d%-15s%-10.1f%-10d", i, slide.getTitle(), 
				slide.getDuration(), slide.getNumBullets()) + "\n";
			newNode = newNode.getNext();
		}
		return title + a + b + c + data + a + total + a;
		
	}	
	
}
