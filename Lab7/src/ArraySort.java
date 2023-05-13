import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author mohammedfaiziqbal
 *
 */
public class ArraySort {

	/**
	 * Insertion sort of an array
	 * 
	 * @param arr the array to be sorted in-place
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int cur = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > cur) {
				arr[j + 1] = arr[j--];
				arr[j + 1] = cur;
			}
		}
	}

	/**
	 * Insertion sort of an array
	 * 
	 * This is Question 3
	 * 
	 * @param arr the array to be sorted in-place
	 */
	public static void bubbleSort(int[] arr) {
		// Step 1: Initialize a boolean variable swaps
		boolean swaps;

		do {
			// Set swaps to false
			swaps = false;

			// step through the array from beginning to end (minus the last element)
			for (int i = 0; i < arr.length - 1; i++) {
				// for each step i, if arr[i+1] is smaller than arr[i]
				if (arr[i + 1] < arr[i]) {
					// swap the values of arr[i+1] and arr[i]
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;

					// set swaps to true
					swaps = true;
				}
			}

		} while (swaps == true);
	}

	// Part 5 : complete implementation
	/**
	 * This method will make use of quick sort to take an array list S and sort it in order.
	 * This will be done by dividing the arraylist into 3 sublists which stores a pivot value,
	 * values greater than the pivot and values smaller than the pivot, then it will recursively
	 * sort the two lists (Greater, Smaller) and finally use a final arraylist to add them all
	 * in order
	 * @param S Unsorted arraylist provided
	 * @return Returns sorted array list
	 */
	public static ArrayList<Integer> quickSort(ArrayList<Integer> S) {
		/*
		 * If the size of (input) S is less than or equal to one, then S is sorted so
		 * base case (Arraylist S) is returned
		 */
		if (S.size() <= 1) {
			return S;
		}
		// Stores int value of the size of the arraylist in the beginning
		int size = S.size();
		// Last element of the arraylist chosen as pivot
		int pivotValue = S.get(size - 1);

		// Divide S into 3 sub-lists
		// L which should store elements of S that are less than the pivot
		ArrayList<Integer> L = new ArrayList<Integer>();
		// E which should store elements of S that equal to the pivot
		ArrayList<Integer> E = new ArrayList<Integer>();
		// G which should store elements of S that are greater than the pivot
		ArrayList<Integer> G = new ArrayList<Integer>();

		// While S is not empty
		while (!S.isEmpty()) {
			// get and delete the first element and add it to one of L, E and G,
			// according to how it compares with the pivot
			int num = S.remove(0);
			// If num is greater than the pivot value, then add number to arraylist G
			if (num > pivotValue) {
				G.add(num);
				// If numb is equal to the pivot value, add to arraylist E
			} else if (num == pivotValue) {
				E.add(num);
				// Last case where num is less than pivotValue then add the num to arraylist L
			} else if (num < pivotValue) {
				L.add(num);
			}
		}

		// Recursively perform quick sort on L and G and assign those sorted lists into
		// newArrayLists sortedL andsortedG.
		ArrayList<Integer> sortedL = quickSort(L);
		ArrayList<Integer> sortedG = quickSort(G);
		ArrayList<Integer> LEG = new ArrayList<Integer>();

		// Combine all three sorted ArrayLists
		LEG.addAll(sortedL); // elements of the sorted version of L
		LEG.addAll(E); // elements of E (which don’t need sorting since they all have
		// the same value as the pivot)
		LEG.addAll(sortedG); // elements of the sorted version of G
		
		//Returns sorted array list
		return LEG;
	}

	/**
	 * predicate to check if array is sorted
	 * 
	 * @param arr the array to be checked
	 * @return true if the array is sorted, false otherwise
	 */
	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++)
			if (arr[i] > arr[i + 1])
				return false;
		return true;
	}

	/**
	 * predicate to check if arrayList is sorted. Useful for checking
	 * ArrayList<Integer> lists returned from Quick Sort.
	 * 
	 * @param arr the array to be checked
	 * @return true is the aray is sorted, flalse otherwise
	 */
	public static boolean isSorted(ArrayList<Integer> arr) {
		Iterator i = arr.iterator();
		int val;
		if (i.hasNext())
			val = (int) i.next();
		else
			return true;
		while (i.hasNext()) {
			int nv = (int) i.next();
			if (val > nv)
				return false;
			val = nv;
		}
		return true;
	}

	/**
	 * Helper printing methods for testing
	 * 
	 * @param arr the array to print
	 */
	private static void printIntArray(int[] arr) {
		System.out.print("[ ");
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" ]");
	}

	private static void printIntArrayList(ArrayList<Integer> arr) {
		System.out.print("[ ");
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
		System.out.println(" ]");
	}

	public static void main(String[] args) {
		// testing part1
		int[] arr1 = { 5, 4, 3, 2, 1 };
		bubbleSort(arr1);
		printIntArray(arr1);

		System.out.println("");
		System.out.println("Test 1 successful");
		System.out.println("");

		// testing part2
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		arr2.add(3);
		arr2.add(1);
		arr2.add(6);
		arr2.add(5);
		ArrayList<Integer> arr2_sorted = quickSort(arr2);
		printIntArrayList(arr2_sorted);
		// {5,4,3,5,1};

		System.out.println("");
		System.out.println("Test 2 successful");
		System.out.println("");

	}

}
