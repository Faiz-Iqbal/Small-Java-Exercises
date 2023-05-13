package sort.parallel;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import sort.sequential.SequentialMergeSort;
import sort.sequential.SortingCommon;
import utils.Benchmark;
/**
 * 
 * @author mohammedfaiziqbal
 *
 */
/*
 * Merge Sort results with thresholding
 * ~~~~~~~~~~~~~~~~~~
 *
 * After parallelisation:
 * - 1 thread
 *   - mergeSort	 no threshold		1 threads	runtime=4453ms	1 steals
 *	 - mergeSort	 threshold=128		1 threads	runtime=4390ms	1 steals
 *	 - mergeSort	 threshold=512		1 threads	runtime=4445ms	1 steals
 *	 - mergeSort	 threshold=2048		1 threads	runtime=4630ms	1 steals
 *	 - mergeSort	 threshold=8192		1 threads	runtime=4359ms	1 steals
 *
 * - 2 threads
 *   - mergeSort	 no threshold		2 threads	runtime=3373ms	2 steals
 *	 - mergeSort	 threshold=128		2 threads	runtime=3586ms	2 steals
 *	 - mergeSort	 threshold=512		2 threads	runtime=3637ms	2 steals
 *	 - mergeSort	 threshold=2048		2 threads	runtime=3613ms	2 steals
 *	 - mergeSort	 threshold=8192		2 threads	runtime=3674ms	2 steals
 *   
 * - 3 threads
 * 	- mergeSort	 	 no threshold		4 threads	runtime=3263ms	4 steals
 *	- mergeSort	 	 threshold=128		4 threads	runtime=3227ms	5 steals
 *	- mergeSort	 	 threshold=512		4 threads	runtime=3157ms	6 steals
 *	- mergeSort	 	 threshold=2048		4 threads	runtime=3111ms	4 steals
 *	- mergeSort	 	 threshold=8192		4 threads	runtime=3177ms	7 steals
 * 
 * - 4 threads
 * 	- mergeSort	 	 no threshold		8 threads	runtime=3291ms	17 steals
 *	- mergeSort	 	 threshold=128		8 threads	runtime=3063ms	10 steals //Shortest
 *	- mergeSort	 	 threshold=512		8 threads	runtime=3094ms	10 steals
 *	- mergeSort	 	 threshold=2048		8 threads	runtime=3229ms	13 steals
 *	- mergeSort	 	 threshold=8192		8 threads	runtime=3108ms	11 steals
 *
 *   <insert more if you have more than 2 CPU cores>
 *
 * Parameters of the shortest runtime:
 * - runtime: 3063ms
 * - how many threads: 8 threads
 * - threshold value:  128
 * 
 * Best parallel speedup: 4453ms/3063ms = 1.4538
 * 
 * Parallelism efficiency:	(1.4538/8)*100 = 18.1725%
 */

public class ParallelMergeSortThreshold extends RecursiveTask<LinkedList<Integer>> {
	LinkedList<Integer> arr;
	int threshold;
	
	/**
	 * This method refactors the code from parallel merge sort to increase the granularity
	 * of parallel tasks, meaning change code so that fewer parallel tasks are created
	 * and so that each task has more work to do.
	 * @param arr Returns the arr which stores the lists
	 * @param threshold to decide whether to execute the sequential case or the parallel
	 * case
	 */
	public ParallelMergeSortThreshold(LinkedList<Integer> arr, int threshold) {
		this.arr = arr;
		this.threshold = threshold;
	}

	@Override
	protected LinkedList<Integer> compute() {
		int length = arr.size();

		// Q2: rewrite the base case condition and body of this if statement,
		// so that you run:
		//
		// sequential merge sort for small inputs (the "base case")
		// by using SequentialMergeSort.mergeSort(..) 
		//
		// or run
		//
		// parallel merge sort in parallel for large inputs (the "recursive" case)
		if (length < 2) {
			//If the length of the input list is smaller than 2, Use the sequential version of merge sort in
			// SequentialMergeSort.mergeSort to sort the input sub-list.
			return SequentialMergeSort.mergeSort(arr);
		}

		else { // parallel case

			/* compute the size of the two sub arrays */
			int halfSize = length / 2;

			/* declare these as `left` and `right` arrays */
			LinkedList<Integer> left = new LinkedList<Integer>();
			LinkedList<Integer> right = new LinkedList<Integer>();

			/* populate the left array with values */
			Iterator<Integer> it = arr.iterator();
			int index = 0;
			while (index < halfSize) {
				left.add(it.next());
				index++;
			}

			/* populate the right array with values */
			index = 0;
			while (index < length - halfSize) {
				right.add(it.next());
				index++;
			}

			// TODO replace this to use the parallel fork/join approach but this
			// time using this ParallelMergeSoftThreshold class to create the two tasks,
			// rather than the ParallelMergeSort class that you used in Q1B. Remember
			// that this time you also need to pass the threshold as the 2nd argument
			// to the constructor.
			//LinkedList<Integer> resultLeft = SequentialMergeSort.mergeSort(left);
			//LinkedList<Integer> resultRight = SequentialMergeSort.mergeSort(right);

			//Instantiate object from ParallelMergeSort class to sort left list + the threshold
			ParallelMergeSortThreshold LSort = new ParallelMergeSortThreshold (left, threshold);
			//Instantiate object from ParallelMergeSort class to sort right list + the threshold
			ParallelMergeSortThreshold  RSort = new ParallelMergeSortThreshold (right, threshold);

			//Forks the task that will sort the left list.
			LSort.fork();

			//Sorts the right list in the current thread, and assigns that value to a
			//variable resultRight of type LinkedList<Integer>
			LinkedList<Integer> resultRight = RSort.compute();
			//Waits for the result from the task forked in Lsort.fork, then assigns that value to a resultLeft
			// of type LinkedList<Integer>
			LinkedList<Integer> resultLeft = LSort.join();

			// Calls the merge method that will create a new list by merging the lists resultLeft and resultRight
			return SequentialMergeSort.merge(resultLeft, resultRight);
		}
	}

	/**
	 * Threshold based parallel merge sort
	 * 
	 * @param numbers     the input list
	 * @param threshold   when to switch from parallel divide-and-conquer to
	 *                    sequential divide-and-conquer
	 * @param parallelism how many threads to use in the ForkJoin workpool
	 * @return the sorted list
	 */
	public static LinkedList<Integer> parallelMergeSortThreshold(LinkedList<Integer> numbers, int threshold,
			int parallelism) {
		ForkJoinPool pool = new ForkJoinPool(parallelism);
		ParallelMergeSortThreshold mergeSortTask = new ParallelMergeSortThreshold(numbers, threshold);
		LinkedList<Integer> result = pool.invoke(mergeSortTask);
		return result;
	}

	/**
	 * Benchmarks threshold based parallel merge sort
	 */
	public static void main(String[] args) {
		/* generates a random list */
		LinkedList<Integer> numbers = SortingCommon.randomList(50000);

		/* gets the number of cores in this computer's CPU */
		int cpuCores = Runtime.getRuntime().availableProcessors();

		/*
		 * 1. prints the runtime for the parallel merge sort from Q1B.
		 * 
		 * 2. prints the runtime for the threshold based parallel merge sort for the
		 * implementation in Q2.
		 */
		for (int threads = 1; threads <= cpuCores; threads *= 2) {
			System.out.print("mergeSort\t no threshold\t\t");
			Benchmark.parallel(new ParallelMergeSort(numbers), threads);
			for (int threshold = 128; threshold <= 8192; threshold *= 4) {
				System.out.print("mergeSort\t threshold=" + threshold + "\t\t");
				Benchmark.parallel(new ParallelMergeSortThreshold(numbers, threshold), threads);
			}
		}
	}

}