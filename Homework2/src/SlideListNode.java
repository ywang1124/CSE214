/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a full-documented class named SlideListNode that wraps a Slide object 
 * to allow it to be inserted into a doubly linked-list data structure. 
 * The Slide object reference should be contained in a field called data, 
 * and there should be two SlideListNode references serving as ‘links’ to 
 * the previous and next SlideListNodes in the list
 */
public class SlideListNode {
	private Slide data;
	private SlideListNode next;
	private SlideListNode prev;
	
	/**
	 * Default constructor
	 * 
	 * @param initData
	 * The data to be wrapped by this SlideListNode. This parameter should not be null, 
	 * since we should never have a SlideListNode with null data
	 * 
	 * @throws IllegalArgumentException
	 * Thrown if initData is null
	 */
	public SlideListNode(Slide initData) {
		if(initData == null) {
			throw new IllegalArgumentException();
		}else {
			data = initData;
			next = null;
			prev = null;
		}
	}
	
	/**
	 * Gets the reference to the data member variable of the list node.
	 * 
	 * @return
	 * The reference of the data member variable
	 */
	public Slide getData() {
		return data;
	}
	
	/**
	 * Updates the data member variable with a reference to a new Slide
	 * 
	 * @param newData
	 * Reference to a new Slide object to update the data member variable. 
	 * This parameter should not be null, since we should never have a SlideListNode with null data 
	 * 
	 * @throws IllegalArgumentException
	 * Thrown if newData is null
	 */
	public void setData(Slide newData) {
		if(newData == null) {
			throw new IllegalArgumentException();
		}else {
			data = newData;
		}
	}
	
	/**
	 * Gets the reference to the next member variable of the list node
	 * 
	 * @return
	 * The reference of the next member variable. 
	 * Note that this return value can be null, 
	 * meaning that there is no next SlideListNode in the list
	 */
	public SlideListNode getNext() {
		return next;
	}
	
	/**
	 * Updates the next member variable with a new SlideListNode reference
	 * 
	 * @param newNext
	 * Reference to a new SlideListNode object to update the next member variable. 
	 * This parameter may be null, since it is okay to have no next SlideListNode
	 */
	public void setNext(SlideListNode newNext) {
		next = newNext;
	}
	
	/**
	 * Gets the reference to the prev member variable of the list node
	 * 
	 * @return
	 * The reference of the prev member variable. Note that this return value can be null, 
	 * meaning that there is no previous SlideListNode in the list
	 */
	public SlideListNode getPrev() {
		return prev;
	}
	
	/**
	 * Updates the prev member variable with a new SlideListNode reference
	 * 
	 * @param newPrev
	 * Reference to a new SlideListNode object to update the prev member variable. 
	 * This parameter may be null, since it is okay to have no previous SlideListNode
	 */
	public void setPrev(SlideListNode newPrev) {
		prev = newPrev;
	}
}
