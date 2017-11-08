import java.io.PrintStream;
import java.util.NoSuchElementException; 

/**
 * Defines the methods for a FIFO queue that handles strings
 */
public class StringQueueImpl implements StringQueue {
	
	private ListNodeStringQueue firstNode;
	private ListNodeStringQueue lastNode;
	private String name;
	
	public StringQueueImpl()
	{
		this("queue");
	}
	
	public StringQueueImpl(String listName)
	{
		name = listName;
		firstNode=lastNode=null;
	}
	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty()
	{
		return firstNode==null;
	}
	/**
	 * insert a string to the queue
	 */
	public void put(String item)
	{
		ListNodeStringQueue node = new ListNodeStringQueue(item);
		if (isEmpty())
		{
			firstNode=lastNode=node;
		}
		else
		{
			lastNode.nextNode = null;
			lastNode=node;
		}
	}

	/**
 	 * remove and return the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public String get() throws NoSuchElementException
	{
	
		if (isEmpty())
		{
			throw new NoSuchElementException(name);
		}
		String removeditem = firstNode.data;
		if (firstNode==lastNode)
		{
			firstNode=lastNode=null;
		}
		else
		{
			firstNode = firstNode.nextNode;
		}
		return   removeditem;
		
	}

	/**
	 * return without removing the oldest item of the queue
 	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public String peek() throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException(name);
		}
		String removeditem = firstNode.data;
		
		return  removeditem;
	}


	/**
	 * print the elements of the queue, starting from the oldest 
         * item, to the print stream given as argument. For example, to 
         * print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printQueue(PrintStream stream)
	{
		if (isEmpty())
		{
			System.out.printf("Empty %s\n",name);
			return;
		}
		System.out.printf("The %s is:",name);
		ListNodeStringQueue current = firstNode;
		
		while(current!=null)
		{
			System.out.printf("%s",current.data);
			current = current.nextNode;
		}
		System.out.println("\n");
	}

	/**
	 * return the size of the queue, 0 if it is empty
	 * @return number of elements(strings) in the queue
	 */
	public int size()
	{
		int k=0;
		ListNodeStringQueue first1 = firstNode;
		while(first1!=null)
		{
			k +=1;
			first1=first1.nextNode;
		}
		return k;
	}
}
