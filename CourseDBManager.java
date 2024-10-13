/*
 * Description: This manages data with a CourseDBStructure object that acts like a course directory.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface{
	
	private CourseDBStructure courseDirectory= new CourseDBStructure(20); //make a course directory with a default size of 20 passed in
	
	/** 
	* adds a course given the data
	*/
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element= new CourseDBElement(id, crn, credits, roomNum, instructor);
		courseDirectory.add(element);
	}

	/** 
	* returns the course with the associated crn
	* @return a CourseDBElement object with the same crn
	*/
	@Override
	public CourseDBElement get(int crn){
		try {
			return courseDirectory.get(crn);
		} catch (IOException e) {
			e.getMessage();
		}
		return null;
	}

	/** 
	* reads the contents of a file and adds all courses with their data
	* @param input- the file to be read
	*/
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner readFile= new Scanner(input);
		while(readFile.hasNextLine())
		{
			String id=readFile.next();
			int crn=readFile.nextInt();
			int cred= readFile.nextInt();
			String roomNum= readFile.next();
			String teacher=readFile.next();
			
			readFile.nextLine(); //go to the next line
			this.add(id, crn, cred, roomNum, teacher);
		}
		readFile.close();
	}

	/** 
	* Returns an array list with all the data of each course, stored as a string
	* @return an array list of strings with the course data for each course
	*/
	@Override
	public ArrayList<String> showAll() {
		return courseDirectory.showAll();
	}

}
