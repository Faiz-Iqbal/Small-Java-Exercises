public class LSearch {


	private class Node {
		private Entry value;
		private Node nextNode;

		public Node(Entry v) {
			value = v;
			nextNode = null;
		}

		public Entry getValue() {
			return value;
		}

		public Node getNextNode() {
			return nextNode;
		}

		// Sets the NextNode to the given Node
		public void setNextNode(Node n) {
			nextNode = n;
		}
	}

	// Holds a reference to the head of the list
	private Node headNode;

	public LSearch() {
		headNode = null;
	}

	public void addAtHead(Entry e) {
		Node newNode = new Node(e); 
		newNode.setNextNode(headNode); 
		headNode = newNode; 
	}

	/*
	 * Part 3: complete
	 */	
	/**
	 * This method uses linear search to search through a linked list, it follows a
	 * similar approach like the one used in linear search for arrays. Here we start at
	 * the headNode and compare until the name is found then return the corresponding 
	 * number.
	 * @param name	The name being searched
	 * @return
	 */
	public int linearSearch(String name){
		// Set headNode to a temporary node.
		Node curr = headNode;
		// While loop to go through each node till it reaches the last one whose nextNode
		// will be null
		while(curr != null) {
			// Checks if The name in curr node is the same as the name being searched for
			if(curr.value.getName()==name) { 
				// Returns the number corresponding to the Name
				return curr.value.getNumber();
			}
			//Sets curr node to the next node
			curr = curr.getNextNode();
		}
		return -1; 
	}
}
