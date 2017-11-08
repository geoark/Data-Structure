public class ListPrintJob {
	private Node head;
	private int listCount;
	
	//ListPrintJob constructor
	public ListPrintJob()
	{
		// this is an empty list, so the reference to the head node
		// is set to a new node with no data
		head = new Node(null);
		listCount = 0;
	}
	
	public void add(PrintJob data)
	// post: appends the specified element to the end of this list.
	{
		Node temp = new Node(data);
		Node current = head;
		// starting at the head node, crawl to the end of the list
		while(current.getNext() != null)
		{
			current = current.getNext();
		}
		// the last node's "next" reference set to our new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}
	
	public void add(PrintJob data, int index)
	// post: inserts the specified element at the specified position in this list.
	{
		Node temp = new Node(data);
		Node current = head;
		// crawl to the requested index or the last element in the list,
		// whichever comes first
		for(int i = 1; i < index && current.getNext() != null; i++)
		{
			current = current.getNext();
		}
		// set the new node's next-node reference to this node's next-node reference
		temp.setNext(current.getNext());
		// now set this node's next-node reference to the new node
		current.setNext(temp);
		listCount++;// increment the number of elements variable
	}
	
	public PrintJob get(int index)
	// post: returns the element at the specified position in this list.
	{
		// index must be 1 or higher
		if(index <= 0)
			return null;
		
		Node current = head.getNext();
		for(int i = 1; i < index; i++)
		{
			if(current.getNext() == null)
				return null;
			
			current = current.getNext();
		}
		return current.getObject();
	}
	
	public boolean remove(int index)
	// post: removes the element at the specified position in this list.
	{
		// if the index is out of range, exit
		if(index < 1 || index > size())
			return false;
		
		Node current = head;
		for(int i = 1; i < index; i++)
		{
			if(current.getNext() == null)
				return false;
			
			current = current.getNext();
		}
		current.setNext(current.getNext().getNext());
		listCount--; // decrement the number of elements variable
		return true;
	}
	
	public int size()
	// post: returns the number of elements in this list.
	{
		return listCount;
	}
	
	public void print()
	{
		Node current = head.getNext();
		while(current != null)
		{
			System.out.printf( "%s ", current );
			current = current.getNext();
		}
		
	}
	
}