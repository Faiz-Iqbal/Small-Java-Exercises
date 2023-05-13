public class LQueue {
	
	private class Node{
		Object element;
		Node next;
		
		public Node(Object e, Node n){
			element = e;
			next = n;
		}
		
		public Node(Object e){
			element = e;
			next = null;
		}
	}
	
	private Node head;
	private Node tail;
	private int size;
	
	public LQueue(){
		head = null;
		tail = null;	
	}
	
	/*
	 * Part 3: complete the following methods
	 */
	
	/**
	 * Checks if the queue is empty and retuen true or false
	 * @return
	 */
	// Part 3: complete	
	public boolean isEmpty(){
		//Returns true if size == 0.
		return (size==0);
	}
	
	// Part 3: complete
	/**
	 * Returns the size of the queue.
	 * @return
	 */
	public int size(){
		//Returns size when called
		return size;
	}
	
	// Part 3: complete
	/**
	 * Enqueue method allows adding elements to the end the queue. This
	 * method checks if the head node is null and points both head and tail
	 * to new node. 
	 * 
	 * @param o
	 */
	public void enqueue(Object o){
		Node newNode = new Node(o);
		
		if(head==null) { //Checks if head is null
		// Sets head and tail to both point at newly initialized node
			head = newNode; 
			tail = newNode;
		}else {
			//Points last node reference to the new node
			tail.next = newNode;
			//Reference the new nodes next to null since its the last
			// node in the queue
			newNode.next = null;
			//Points tail to the new node created
			tail = newNode;
		}
		//Increments Size
		size+=1;

	}
	
	/**
	 * Dequeue removes elements from the queue with exception handlers to
	 * prevent popping from an empty queue.
	 * @return
	 * @throws QueueException
	 */
	// Part 3: complete	
	public Object dequeue() throws QueueException{
		//Exception handler to throw an exception if the queue is empty
		if(head==null) {
			throw new QueueException("Can't Dequeue from an empty queue");
		}
		//Create new variable of type object
		Object temp = new Object();
		//Stores head element in temp
		temp = head.element;
		
		if(head==tail) {
			//Sets head and tail to null since the queue is empty
			head = tail = null;
		}else {
			//Sets head reference to the next node to isolate the previous
			// node from the list which is removed by java's garbage collector
			head = head.next;
		}
		//Decrements size
		size--;
		return temp;
	}
	
	// Part 3: complete
	/**
	 * Front method returns the element at the front of the queue.
	 * @return
	 * @throws QueueException
	 */
	public Object front() throws QueueException{
		//Throws exception if queue is empty since there are no elements
		// in front
		if(isEmpty()) {
			throw new QueueException("No front value for an empty queue");
		}else {
			return head.element;
		}
			
	}
	
}
