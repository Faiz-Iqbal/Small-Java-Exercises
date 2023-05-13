public class ReverseStack {


	/*
	 * 1: complete implementation
	 */
	/**
	 * Reverse Stack class takes stack st and repopulates it in reverse order
	 * 
	 * This method will 
	 * @param st
	 */
	public static void reverseStack(Stack st){
		//Creating an integer to store the size of stack st
		int size = st.size();
		//Create Queue class object
		Queue Q = new Queue(st.size()+1); // +1 to ensure that the queue does not reach max capacity

		for (int i=0; i<size; i++) {
			//Pop element from stack st and enqueue into Q
			Q.enqueue(st.pop());
		}

		for(int i=0;i<size;i++) {
			//Dequeue from Q and push to the stack
			st.push(Q.dequeue());
		}
	}
}
