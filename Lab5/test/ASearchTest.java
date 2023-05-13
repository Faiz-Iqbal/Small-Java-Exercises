import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ASearchTest {

	ASearch as;
	/*
	 * Part 1: complete unit tests
	 */
	@Before
	public void setup(){
		as = new ASearch();
		as.addEntry(new Entry("Andrew",111));
		as.addEntry(new Entry("Ewen",321));
		as.addEntry(new Entry("Peter",123));
		as.addEntry(new Entry("Roger",222));
		
	}
	
	@Test
	public void testLinearSearchOK() {
		// test the linear search method for someone who's in the collection
		assertEquals(111, as.linearSearch("Andrew"));
		assertEquals(123, as.linearSearch("Peter"));
		assertEquals(222, as.linearSearch("Roger"));
		assertEquals(321, as.linearSearch("Ewen"));
	}

	@Test
	public void testLinearSearchFail() {
		// test the linear search method for someone who's not in the collection
		assertEquals(-1, as.linearSearch("Faiz"));
	}
	
	@Test
	public void testBinarySearchOK() {
		// test the binary search method for someone who's in the collection
		assertEquals(111, as.binarySearch("Andrew"));
		assertEquals(123, as.binarySearch("Peter"));
		assertEquals(222, as.binarySearch("Roger"));
		assertEquals(321, as.binarySearch("Ewen"));
	}
	
	@Test
	public void testBinarySearchFail() {
		// test the binary search method for someone who's not in the collection
		assertEquals(-1, as.binarySearch("Faiz"));
	}

}
