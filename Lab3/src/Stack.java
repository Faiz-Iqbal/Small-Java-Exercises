class StackException extends RuntimeException{    
	public StackException(String err) {
		super(err);
	}
}
/**
 * 
 * @author mohammedfaiziqbal
 *
 */

//Commit test eclipse  

public class Stack {

	private class Node{
		int element;
		Node next;

		public Node(int e, Node n){
			element = e;
			next = n;
		}

		public int getValue() {
			return element;
		}

		public Node getNext() {
			return next;
		}
	}

	// this is a reference to the head node of the linked list
	private Node top;

	// keep track of the number of elements in the stack
	private int size;

	public Stack(){
		top = null;
		size = 0;
	}

	public boolean isEmpty(){
		return top == null;
	}

	public int size(){
		return size; 
	}

	// part 3: complete
	/** Adds a new element to the stack
	 * The push method is used to add new items to the linked list stack,
	 * the implementation involves initiating the node which writes to
	 * element (stores the value) and next (stores the next nodes reference)
	 * @param o the integer to add to the top of the stack
	 */
	public void push(int o){
		//Create a new Node named top
		top = new Node (o,top);
		//Increment the variable size
		size++;
	}

	// part 3: complete
	/** Removes an element from the top of the stack
	 * Pop method is used to remove elements out of the linked list stack.
	 * the implementation works by using the isEmpty() method to check whether
	 * the size is 0, if true it will throw an exception if the pop method is called
	 * otherwise it will change the pointer and pop the element.
	 * @return the integer that was at the top of the stack
	 * @throws StackException if the stack is empty
	 */
	public int pop() throws StackException{
		Node ptr = top;

		if(isEmpty()) {
			//Checks if size ==0 and throws exception
			throw new StackException("Can't pop from an emtpy stack");
		}else {
			// Moves the next node as top
			top = top.getNext();
			// Decrements size
			size--;
			return ptr.getValue();
		}
	}

	// part 3: complete
	/** Returns the integer at the top of the stack
	 * Top method again uses isEmpty to check whether size == 0, and throws
	 * an exception if top method is called when top = null, else it will return the
	 * value in top
	 * @return the integer at the top of the stack
	 * @throws StackException is the stack is empty
	 */
	public int top() throws StackException{
		if(isEmpty()) { 
			throw new StackException("Stack is Empty");
		}else return top.getValue();

	}

}
