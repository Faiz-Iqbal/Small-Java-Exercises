
public class Reverse {

	/**
	 * Reverse method creates a stack, moves all the elements from the test
	 * array and pushes it into the stack. Then removes it out and puts it back
	 * into the array creating a reverse array thanks to the LiFo property of
	 * stacks.
	 * @param arr
	 */
	public static void reverse(String[] arr){ 
		/*Create a New Stack from the Stack given to us in 
		the previous exercise*/
		Stack SB = new Stack(arr.length);

		/*For loop to iterate through the array and
		 * populate the stack
		 */
		for(int i=0; i<arr.length; i++) {
			SB.push(arr[i]);
		}

		/* For loop to LiFo the data from the stack
		 * and store it in the array in reverse order
		 */
		for(int i=0; i<arr.length; i++) {
			//Convert Object from stack to string
			arr [i] = SB.pop().toString();
		}


	}

}
