import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LinkedListTest {

	LinkedList l;
	
	@Before
	public void setup(){
		l = new LinkedList();
	}
	/*
	 * Part 1: implement these methods
	 */
	@Test
	public void testSizeEmpty() {
		// test l.size() for an empty linked list
		assertEquals(0, l.size());
	}

	@Test
	public void testSizeMany() {
		// test l.size() after adding some numbers to the linked list
		l.addAtHead(24);
		l.addAtTail(4);
		l.removeAtHead();
		l.addAtHead(5);
		l.removeAtTail();
		l.addAtHead(78);
		l.addAtTail(27);
		assertEquals(3,l.size());
	}
	
	@Test
	public void testSizeTwice() {
		// test l.size() twice after adding some numbers to the linked list
		l.addAtHead(14);
		l.addAtTail(29);
		l.addAtHead(45);
		l.addAtTail(34);
		l.addAtTail(67);
		l.removeAtHead();
		l.removeAtTail();
		l.removeAtHead();
		assertEquals(2,l.size());
	}

	@Test
	public void testTotalEmpty() {
		assertEquals(0, l.total());
		
	}

	@Test
	public void testTotalMany() {
		// test l.total() after adding some numbers to the linked list
		l.addAtHead(12);
		l.addAtTail(14);
		l.addAtHead(16);
		l.removeAtHead();
		assertEquals(26,l.total());
	}
	
	@Test
	public void testTotalTwice() {
		// test l.total() twice after adding some numbers to the linked list
		l.addAtHead(11);
		l.addAtHead(13);
		l.addAtTail(15);
		l.addAtTail(16);
		l.addAtTail(17);
		l.removeAtHead();
		l.removeAtTail();
		l.removeAtTail();
		assertEquals(26, l.total());
	}
	
	/*
	 * Optional part
	 */
	
//	@Test
//	public void testReverse() {
//		l.addAtHead(5);
//		l.addAtHead(2);
//		l.addAtHead(10);
//		l.reverse();
//		assertEquals(5, l.removeAtHead());
//		assertEquals(2, l.removeAtHead());	
//		assertEquals(10, l.removeAtHead());	
//	}

}
