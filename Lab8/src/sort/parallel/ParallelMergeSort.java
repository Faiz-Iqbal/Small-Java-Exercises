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
 * Merge Sort results
 * ~~~~~~~~~~~~~~~~~~
 * 
 * Before parallelisation: 
 * - 1 threads	runtime=3312ms	1 steals
 * - 2 threads	runtime=3205ms	1 steals
 * - 4 threads	runtime=3295ms	1 steals
 * - 8 threads	runtime=3185ms	1 steals
 * 
 * After parallelisation: 
 * - 1 threads	runtime=3263ms	1 steals 
 * - 2 threads	runtime=2441ms	2 steals
 * - 4 threads	runtime=2285ms	4 steals
 * - 8 threads	runtime=2256ms	18 steals
 */

public class ParallelMergeSort extends RecursiveTask<LinkedList<Integer>> {
	LinkedList<Integer> arr;
	/**
	 * Parallelise merge sort the lists with the goal of achieving shorter runtimes. 
	 * @param arr Returns the arr which stores the lists
	 */
	public ParallelMergeSort(LinkedList<Integer> arr) {
		this.arr = arr;
	}

	@Override
	protected LinkedList<Integer> compute() {
		int length = arr.size();
		// base case
		if (length < 2) {
			return arr;
		}
		// step case
		else {
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

			/*
			 * Q1A: profile this sequential implementation.
			 * 
			 * Q1B: replace this with a parallel fork/join approach.
			 */
			//LinkedList<Integer> resultLeft = SequentialMergeSort.mergeSort(left);
			//LinkedList<Integer> resultRight = SequentialMergeSort.mergeSort(right);

			//Instantiate object from ParallelMergeSort class to sort left list
			ParallelMergeSort LSort = new ParallelMergeSort(left);
			//Instantiate object from ParallelMergeSort class to sort right list
			ParallelMergeSort RSort = new ParallelMergeSort(right);
			
			//Forks the task that will sort the left list. 
			LSort.fork();

			//Sorts the right list in the current thread, and assigns that value to a
			//variable resultRight of type LinkedList<Integer>
			LinkedList<Integer> resultRight = RSort.compute();
			//Waits for the result from the task forked in Lsort.fork, then assigns that value to a resultLeft
			// of type LinkedList<Integer>
			LinkedList<Integer> resultLeft = LSort.join();

			// Calls the merge method that will create a new list by merging the lists resultLeft and resultRight
			return merge(resultLeft, resultRight);
		}

	}

	// to assist mergeSort
	public static LinkedList<Integer> merge(LinkedList<Integer> left, LinkedList<Integer> right) {
		int lindex = 0;
		int rindex = 0;
		LinkedList<Integer> mergedList = new LinkedList<Integer>();

		/*
		 * step 1: compare elementwise left and right lists and write the smaller of the
		 * two values into the mergedList list
		 */
		while (lindex < left.size() && rindex < right.size()) {
			if (left.get(lindex) <= right.get(rindex)) {
				mergedList.add(left.get(lindex));
				lindex++;
			} else {
				mergedList.add(right.get(rindex));
				rindex++;
			}
		}

		// step 2: write any remaining values in the left list into the mergeList list
		while (lindex < left.size()) {
			mergedList.add(left.get(lindex));
			lindex++;
		}

		// step 3: write any remaining values in the right list into the mergeList list
		while (rindex < right.size()) {
			mergedList.add(right.get(rindex));
			rindex++;
		}
		return mergedList;
	}

	/**
	 * Parallel merge sort
	 * 
	 * @param numbers     the list of numbers to be sorted
	 * @param parallelism how many CPU cores to use
	 * @return the sorted list
	 */
	public static LinkedList<Integer> parallelMergeSort(LinkedList<Integer> numbers, int parallelism) {
		ForkJoinPool pool = new ForkJoinPool(parallelism);
		ParallelMergeSort mergeSortTask = new ParallelMergeSort(numbers);
		LinkedList<Integer> result = pool.invoke(mergeSortTask);
		return result;
	}

	/**
	 * Benchmarks parallel merge sort
	 */
	public static void main(String[] args) {
		/* generates a random list */
		LinkedList<Integer> numbers = SortingCommon.randomList(50000);

		/* gets the number of cores in this computer's CPU */
		int cpuCores = Runtime.getRuntime().availableProcessors();

		/*
		 * benchmarks the parallel merge sort for Q2 on 1, 2, 4, .. CPU cores
		 */
		for (int threads = 1; threads <= cpuCores; threads *= 2) {
			Benchmark.parallel(new ParallelMergeSort(numbers), threads);
		}
	}

}