/*
 * Description: This has the code for the data each course should have
*/
public class CourseDBElement implements Comparable<CourseDBElement>{

	private String courseID;
	private int CRN;
	private int numCredits;
	private String roomNum;
	private String instructor;
	
	/** 
	* Constructor for the class
	* @param ID, CRN, credits, room, instructor- all data for a course
	*/
	public CourseDBElement(String ID, int CRN, int credits, String room, String instructor) {
		courseID=ID;
		this.CRN= CRN;
		numCredits= credits;
		roomNum= room;
		this.instructor= instructor;
	}
	
	/** 
	* Default constructor that gives every field null or 0
	*/
	public CourseDBElement() {
		courseID=null;
		CRN=0;
		numCredits=0;
		roomNum=null;
		instructor=null;
	}

	/** 
	* Compares courses 
	* @param a course to be compared
	* @return 0 if the courses have equal fields, 1 if otherwise
	*/
	@Override
	public int compareTo(CourseDBElement o) {
		if(courseID.equals(o.courseID) && CRN==o.CRN && numCredits==o.numCredits && roomNum.equals(o.roomNum) && instructor.equals(o.instructor))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	/** 
	* returns the course CRN
	* @return crn for the course
	*/
	public int getCRN() {
		return CRN;
	}

	/** 
	* returns the course ID
	* @return courseID for the course
	*/
	public String getID() {
		return courseID;
	}

	/** 
	* returns the course room number
	* @return roomNum for the course
	*/
	public String getRoomNum() {
		return roomNum;
	}
	
	/** 
	* returns the course credits
	* @return numCredits for the course
	*/
	public int getCredits() {
		return numCredits;
	}
	
	/** 
	* returns the course instructor
	* @return instructor for the course
	*/
	public String getInstructor() {
		return instructor;
	}

	/** 
	* allows one to update the crn for a course
	* @param paseInt the updated crn for the course
	*/
	public void setCRN(int parseInt) {
		CRN= parseInt;
	}
	
	/** 
	* to string method for the CourseDBElement class
	* @return result- a string with the data of the course 
	*/
	public String toString() {
		String result="Course:" + courseID + " CRN:" + CRN + " Credits:" + numCredits + " Instructor:" + instructor + " Room:" + roomNum;
		return result;
	}
}
