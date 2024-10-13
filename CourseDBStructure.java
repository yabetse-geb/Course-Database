/*
 * Class: CMSC204 CRN: 32524
 * Instructor: Professor Kuijt
 * Description: Acts as a course directory, allowing you to add and retrieve courses.
 * Due: 3/26/2023
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: Yabetse Gebrewold
*/
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class CourseDBStructure implements CourseDBStructureInterface{
	private int size;
	private LinkedList<CourseDBElement>[] hashTable;
	
	/** 
	* Constructor for the class
	* @param n- the size
	*/
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int n)
	{
		int num= (int)(n/1.5);
		while(!(isPrime(num) && (num-3)%4==0))
		{
			num++;	
		}
		size=num;
		hashTable= new LinkedList[size];
	}
	
	/** 
	* Constructor for testing purposes
	* @param n- the size
	* @param test the string passed in
	*/
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String test, int size1) {
		size=size1;
		hashTable= new LinkedList[size];
	}
	
	/** 
	* Checks if a number is prime
	* @param num- the number to be checked
	* @return true if prime, false otherwise
	*/
	private boolean isPrime(int num) {
		boolean isPrime=false;
		int numDivisors=0;
		for(int i=2; i<num; i++)
		{
			if(num%i==0)
				numDivisors++;
		}
		if(numDivisors==0)
		{	
			isPrime=true;
		}
		else
		{
			isPrime=false;
		}
		return isPrime;
	}


	/** 
	* Adds courses passed in
	* of the CourseDatabaseElemen object's crn value.
	* If the CourseDatabaseElement already exists, exit quietly
	* If there is a course with the same crn but different data, update that course with the new data
	* @param element the CourseDBElement to be added to CourseDBStructure
	*/
	public void add(CourseDBElement element) {
		boolean found=false;
		boolean sameCRNfound=false;
		int iteratorIndexWithDuplicate=0;
		int hashIndex= Integer.toString(element.getCRN()).hashCode()%size;
		
		if(hashTable[hashIndex]!=null)
		{
			LinkedList<CourseDBElement> temp= hashTable[hashIndex];
			for(int j=0; j<temp.size(); j++)
			{
				if(temp.get(j).compareTo(element)==0)
				{
					found=true;
				}
				if(temp.get(j).getCRN()== element.getCRN())
				{
					sameCRNfound=true;
					iteratorIndexWithDuplicate=j;
				}
			}
		}
		
		if(found==false) 
		{
			if(sameCRNfound) //check for same crn but different other data first, and if found, then update
			{
				hashTable[hashIndex].set(iteratorIndexWithDuplicate, element); //replace
			}
			else
			{
				if(hashTable[hashIndex]==null)
				{
					hashTable[hashIndex]= new LinkedList<CourseDBElement>();
					hashTable[hashIndex].add(element); //add element as the first node
				}
				else //a linked list exists so add it to the existing list
				{
					hashTable[hashIndex].add(element);
				}
			}
		}
	}
	
	/** 
	* Returns the table size
	* @return size- the size of the table
	*/
	public int getTableSize() {
		return size;
	}

	/** 
	* Returns the course with the crn
	* @param crn- the course's crn to be retrieved
	* @return the course with the crn
	* @throws IOException if the course is not found in the hashTable
	*/
	@Override
	public CourseDBElement get(int crn) throws IOException {
		boolean found=false;
		int hashIndex= (Integer.toString(crn).hashCode())%size;
		
		if(hashTable[hashIndex]==null)
			throw new IOException();
		
		LinkedList<CourseDBElement> temp= hashTable[hashIndex];
		for(int j=0; j<temp.size(); j++)
		{
			CourseDBElement theCourse= temp.get(j);
			if(theCourse.getCRN()==crn)
			{
				found=true;
				return theCourse; //return the course with the crn
			}
		}
	
		if(!found)
			throw new IOException();	
		return null;
	}

	/** 
	* Returns an array list with all the data of each course, stored as a string
	* @return an array list of strings with the course data for each course
	*/
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> result= new ArrayList<>();
		for(int i=0; i<size; i++)
		{
			if(hashTable[i]!=null)
			{
				LinkedList<CourseDBElement> temp= hashTable[i];
				for(int j=0; j<temp.size(); j++)
				{
					result.add("\nCourse:" + temp.get(j).getID() + " CRN:" + temp.get(j).getCRN() + " Credits:" + temp.get(j).getCredits() + " Instructor:" + temp.get(j).getInstructor() + " Room:" + temp.get(j).getRoomNum()); 
				}
			}
		}
		return result;
	}		
}
