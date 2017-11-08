import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueWithOnePointer implements StringQueue {

	private StringQueueWithOnePointer next;
	private String data;
	private String name;

	public StringQueueWithOnePointer() {  //Constructor. It initializes data and sets next node with this
		                                    
		data = " ";
		next = this;
	}

	public StringQueueWithOnePointer(String value) { //constructor creates StringQueueWithOnePointer with passed data and next node
		data = value;
		next = this;
	}
    public void setData(String data){  //distinguish the reference to node's data 
		this.data=data;
	}
	public String getData(){  //returns this node's data
		                     //return the reference to node's data
		return data;
	}
	
	public StringQueueWithOnePointer getNext() //Get reference to next node
	                                           //return the next node
    {
        return next;
    }
	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty() {
		return next == null;
	}

	/**
	 * insert a string to the queue
	 */
	public void put(String v) {                                   
		
		StringQueueWithOnePointer node = new StringQueueWithOnePointer(v);
		if (this.next == this) { // only one node in the circular list

			node.next = this;
			this.next = node;
			
		} else {
			// Insert in the middle
			StringQueueWithOnePointer temp = this.next;
			node.next = temp;
			this.next = node;
			

		}

	}
	

	/**
	 * remove and return the oldest item of the queue
	 * 
	 * @return oldest item of the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public String get() throws NoSuchElementException {                 

		if (isEmpty())

		{
			throw new NoSuchElementException(name);
		}

		StringQueueWithOnePointer node = this.next;

		this.next = this.next.next;
        
		
		String x = node.getData();
		
		next = node.getNext(); 
        node= null;	
		return x;

	}

	/**
	 * return without removing the oldest item of the queue
	 * 
	 * @return oldest item of the queue
	 * @throws NoSuchElementException
	 *             if the queue is empty
	 */
	public String peek() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException(name);
		}
		StringQueueWithOnePointer node = this.next;
		String x = node.getData();
		
        node= null;		
		return x;
	}

	/**
	 * print the elements of the queue, starting from the oldest item, to the
	 * print stream given as argument. For example, to print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printQueue(PrintStream stream) {
		if (isEmpty()) {
			System.out.printf("Empty %s\n", name);
			return;
		}
		System.out.printf("The %s is:", name);
		StringQueueWithOnePointer current = this;

		while (current != null) {
			System.out.printf("%s", current.data);
			current = current.next;
		}
		System.out.println("\n");
	}
	 public int size()

	   {

	      return size(this);

	   }

	/**
	 * return the size of the queue, 0 if it is empty
	 * @return number of elements(strings) in the queue
	 */
	public int size(StringQueueWithOnePointer node)
	{
       if (node == null) {
			  
		  node = this;
	   }
	        
        int count = 0;
        StringQueueWithOnePointer startnode = node;
	    while (node != startnode) {
			  count++;
              node = node.next;
	   }
	    System.out.println("\nCurrent Node Value: " + node.data);
        System.out.println("\nTotal nodes in Circular List: " + count);
	      return count;

    }
}  
