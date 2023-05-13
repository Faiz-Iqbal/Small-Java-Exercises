/**
 * 
 * @author mohammedfaiziqbal
 *
 */
public class DLinkedList {

	private class Node {
		private Object value;
		private Node nextNode;
		private Node prevNode;

		public Node(Object v) {
			value = v;
			nextNode = null;
			prevNode = null;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object v) {
			value = v;
		}
		
		public Node getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node n) {
			nextNode = n;
		}
		
		public Node getPrevNode() {
			return prevNode;
		}

		public void setPrevNode(Node n) {
			prevNode = n;
		}
	
	}

	// Holds a reference to the head and tail of the list
	private Node headNode;
	private Node tailNode;

	public DLinkedList() {
		headNode = null;
		tailNode = null;
	}
	
	public Object getHeadValue(){
		if (headNode == null)
			return null;
		return headNode.value;
	}
	
	public Object getTailValue(){
		if (tailNode == null)
			return null;
		return tailNode.value;
	}
	
	public void addAtHead(Object o) {
		Node newNode = new Node(o); 
		newNode.setNextNode(headNode); 
		if (headNode != null)
			headNode.setPrevNode(newNode);
		headNode = newNode; 
		// special case for empty list
		if (tailNode == null)
			tailNode = newNode;
	}

	public void addAtTail(Object o) {
		Node newNode = new Node(o);
		// this means that headNode == null too!
		if(tailNode == null){
			tailNode = newNode;
			headNode = newNode;
		}else{
			newNode.setPrevNode(tailNode);
			tailNode.setNextNode(newNode);
			tailNode = newNode;
		}
	}
		
	public Object deleteAtHead() {
		// list is empty 
		if(headNode == null){
			headNode = null;
			tailNode = null;
			return null;
		}
		// singleton: must update tailnode too
		if(headNode == tailNode){
			Object res = headNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}
		
		Object res = headNode.getValue();
		headNode = headNode.getNextNode();
		headNode.setPrevNode(null);
		return res;
	}

	public Object deleteAtTail() {
		// list is empty 
		if(tailNode == null){
			headNode = null;
			tailNode = null;
			return null;
		}
		// singleton: must update tailnode too
		if(headNode == tailNode){
			Object res = tailNode.getValue();
			headNode = null;
			tailNode = null;
			return res;
		}
		Object res = tailNode.getValue();
		tailNode = tailNode.getPrevNode();
		tailNode.setNextNode(null);
		return res;
	}

	/**
	 * @param idx the index position of the value
	 * @return the value in the list at a given index
	 */
	public Object get(int idx) {
		Object value = null;
		int i = 0;
		Node n = headNode;
		while (i <= idx) {
			if (n == null) {
				return -1;
			}
			else {
				value = n.getValue();
				n = n.getNextNode();
				i++;
			}
		}
		return value;
	}

	
	// Part 4: complete
	/**
	 * Reverse method reverses the Doubly linked list without using stacks or an array.
	 * Instead it reverses the object reference arrows of next and prev node
	 */
	public void reverse(){
		//Stores the headNode in a temporary Node
		Node tmp = headNode;
		
		while(tmp!=null) {
			// Swaps next nodes reference with previous nodes in a
			// temporary node
			Node move = tmp.nextNode;
			// Previous nodes reference stored in next node
			tmp.nextNode=tmp.prevNode;
			// Moves in previous node the reference of next node
			tmp.prevNode=move;
			tmp = tmp.prevNode;
		}
		//Store headNode reference in temp node moveHead
		Node moveHead = headNode;
		//Store tailNodes reference into headNode (i.e points to the same
		// memory location)
		headNode = tailNode;
		// Set headNodes reference which was stored in moveHead
		// to tailNode
		tailNode = moveHead;
		
	
	}	

}


