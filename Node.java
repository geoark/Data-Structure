public class Node {
	// package access members; List can access these directly
		 PrintJob data;
	     Node nextNode;

		// constructor creates a Node that refers to object
		Node( PrintJob object )
		{
			this( object, null );
		} // end ListNode one-argument constructor

		// constructor creates Node that refers to
		// Object and to next Node
		Node( PrintJob object, Node node )
		{
			data = object;
			nextNode = node;
		} // end Node two-argument constructor

		// return reference to data in node
		PrintJob getObject()
		{
			return data; // return Object in this node
		} // end method getObject

		// return reference to next node in list
		Node getNext()
		{
			return nextNode; // get next node
		} // end method getNext
		void setNext(Node _nextNode)
		{
			_nextNode =nextNode; // set next node
		} // end method setNext

    }
