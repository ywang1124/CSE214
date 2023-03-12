/**
 * @author Yuang Wang
 * 113427897
 * yuang.wang@stonybrook.edu
 * hw1
 * CSE214
 * Recitation R02 
 * TA: Jamieson Barkume/Steven Secreti  
 * 
 * a fully-documented class named Slide which contains the title (String), 
 * the array of bullet points (String[]), and the duration of the slide in minutes (double). 
 * Each slide should be limited to a maximum of 5 bullet points, 
 * which should be reflected in a static constant named MAX_BULLETS. 
 * Provide getter and setter methods for all member variables, 
 * with the getter/setter method for bullets taking an additional index parameter
 * 
 */
public class Slide {
	public static final int MAX_BULLETS = 5;
	private String title;
	private String[] bullets;
	private double duration;
	
	/**
	 * a default constructor to create new empty slide
	 */
	public Slide() {
		title = null;
		bullets = new String[MAX_BULLETS+1];
		duration = 0.0;
	}
	
	/**
	 * the getter of title
	 * 
	 * @return
	 * the title of slide
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * the setter of title
	 * 
	 * @param newTitle
	 * the new title of slide
	 * 
	 * @throws IllegalArgumentException
	 * when new title is null
	 */
	public void setTitle(String newTitle) {
		if(newTitle == null||newTitle.isEmpty()) {
			throw new IllegalArgumentException();
		}else {
			title = newTitle;
		}
	}
	
	/**
	 * the getter of duration
	 * 
	 * @return
	 * the duration of slide
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * the setter of duration
	 * 
	 * @param newDuration
	 * the new duration of slide
	 * 
	 * @throws IllegalArgumentException
	 * when new duration is less or equal to 0
	 */
	public void setDuration(double newDuration) {
		if(newDuration <= 0) {
			throw new IllegalArgumentException();
		}else {
			duration = newDuration;
		}
	}
	
	/**
	 * a getter of number of bullets counts the non-null element in bullets array
	 * 
	 * @return
	 * number of bullets of slide
	 */
	public int getNumBullets() {
		int num = 0;
		for(int i = 0;i < bullets.length;i++) {
			if(bullets[i] != null) {
				num++;
			}
		}
		return num;
	}
	
	/**
	 * a getter of the bullet point at input index
	 * 
	 * @param i
	 * The index to retrieve from the array
	 * 
	 * @return
	 * The String representing the bullet point at the given index 
	 * (may be null, meaning there is no bullet point at this index)
	 * 
	 * @throws IllegalArgumentException
	 * Thrown if i is not between 1 and MAX_BULLETS
	 */
	public String getBullet(int i) {
		if(i < 1||i > MAX_BULLETS) {
			throw new IllegalArgumentException();
		}else {
			return bullets[i-1];
		}
	}
	
	/**
	 * Sets bullet as the i'th bullet point for bullets. If bullet is null, 
	 * this method erases the bullet point at index i and 
	 * pushes all bullet points greater than i back one index
	 * 
	 * @param bullet
	 * The String to place as the ith bullet point in bullets. This parameter may be null, 
	 * indicating that the bullet at index i is to be erased from the slide
	 *
	 * @param i
	 * The index to place bullet in the array
	 * 
	 * @throws IllegalArgumentException
	 *  if i is not between 1 and MAX_BULLETS
	 */
	public void setBullet(String bullet, int i) {
		if(i < 1||i > MAX_BULLETS ) {
			throw new IllegalArgumentException();
		}else if(bullet == null){
			for (int n = i; n < MAX_BULLETS + 1; n++) {
			    bullets[n-1] = bullets[n];
			}
		}else {
			bullets[i-1] = bullet;
		}
	}
	
	/**
	 * Gets the String representation of this slide object
	 * 
	 * @return
	 * the string representation of slide
	 */
	public String toString() {
		String a = "==============================================\n";
		String data = "";
		int num = 1;
		for(int i = 1;i < MAX_BULLETS + 1;i++) {
			if(getBullet(i)==null) {
				continue;
			}
			data += num + ". " + getBullet(i) + "\n";
			num++;
		}
		return a + title + "\n" + a + data + a;
		
	}	

}
