/**
 * 
 * @author mohammedfaiziqbal
 *
 */

public class ASearch {


	private Entry[] catalogue;
	private int current;

	/*
	 * Assume 10 entries
	 */
	public ASearch(){
		catalogue = new Entry[10];
		current = 0;
	}

	/*
	 * Ignores adding if full (should really be handled by exception...)
	 */
	public void addEntry(Entry e){
		if(current < 10){
			catalogue[current++] = e;
		}
	}

	/*
	 * Part 2: complete implementation
	 */
	/**
	 * This method starts at the beginning of the array and compares with name till a 
	 * match is found. If a match is found then it will return the number corresponding
	 * to the name. If the name does not exist in the array it will return -1
	 * @param name Name being searched for ex Andrew
	 * @return
	 */
	public int linearSearch(String name){
		//Runs the if function for the number of items in catalogue
		for(int i=0; i<current; i++) { //!!! Question to ask prof, why cant we use catalogue.length instead of current
			//Compares the name in index i of array catalogue with name
			if((catalogue[i].getName())==name) {
				// Returns the number corresponding to the name
				return catalogue[i].getNumber();
			}
		}
		//Returns -1 if the name does not exist in array
		return -1;
	}

	/*
	 * Part 4: complete implementation
	 */
	/**
	 * Binary search starts in the middle and checks if the name stored is the same as
	 * the one being searched for, if true then the number of the person is returned. If
	 * not then the name in the middle is called and compared with the name being searched
	 * for by using compareTo, this function compares the strings by Unicode value of each 
	 * character in the strings. If the returned value is greater than 0 then the bottom 
	 * half of the array is searched, or else if its less than 0 then the top half is
	 * searched. 
	 * @param first The first index of array we should search in
	 * @param last  The last index of array we should search in
	 * @param name	The name being searched
	 * @return
	 */
	private int binarySearch(int first,int last,String name){
		//Failure case
		if(first>last) { 
			return -1;
		//Success Case
		}else {
			//Finds the middle index by taking the first and last index and dividing it 
			//by 2
			int middle = (first+last)/2;
			//Gets the name stored in the middle index
			String middleName = catalogue[middle].getName();
			
			//Compares unicode values of the name in middle of catalogue and the name
			//being searched for
			if(catalogue[middle].getName().compareTo(name) == 0) {
				//If the name in the middle is the name being searched then the number
				//corresponding to that name is returned
				return catalogue[middle].getNumber();

			}else if(catalogue[middle].getName().compareTo(name) > 0) {
				//Recursive method being used to search the bottom half of the array
				return binarySearch(first, middle-1, name);

			}else if(catalogue[middle].getName().compareTo(name) < 0) {
				//Recursive method being used to search the top half of the array
				return binarySearch(middle+1, last, name);
			}
		}
		return -1;
	}

	// helper method exposed to the programmer
	public int binarySearch(String name){
		return binarySearch(0,current-1,name);
	}
}
