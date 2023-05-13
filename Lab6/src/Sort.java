/**
 * 
 * @author mohammedfaiziqbal
 *
 */
public class Sort {

	/*
	 * Part 4: complete method
	 */
	/**
	 * Sort method uses Priority queues to sort an array in order.
	 * @param arr Array that will be inserted in the priority queue
	 */
	public static void sort(int[] arr) {
		// Instantiate a new priority queue object whose size is the length of the
		// array 
		PriorityQueue queue = new PriorityQueue(arr.length);

		// For loop to iterate through the array and populate the queue
		for (int i = 0; i < arr.length; i++) {
			// Inserts value in arr[i] into the priority queue
			queue.insert(arr[i]);
		}
		
		// For loop iterates through the array and removes the minimum value
		// in each iteration and adds it to the array again, resulting in an ordered list.
		// This sorting is done "in place" so there is no need to create a new array.
		for (int i = 0; i < arr.length; i++) {
			// Remove minimum value from queue and assign it to index i in array
			arr[i] = queue.removeMin();
		}
	}

	public static void main(String[] args) {
		int[] arr = { 53, 3, 5, 2, 4, 67 };
		Sort.sort(arr);
		// should be printed in order
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr[3]);
		System.out.println(arr[4]);
		System.out.println(arr[5]);
	}

}
