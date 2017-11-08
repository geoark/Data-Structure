import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a Stack that handles String items
 */
public class StringStackImpl implements StringStack
{
	private ListNodeStringStack firstNode;
	private ListNodeStringStack lastNode;
	private String name;
	
	public StringStackImpl() 
	{
		this("stack");
	}
	
	public StringStackImpl(String listName)
	{
		name=listName;
		firstNode = lastNode= null;
	}
	/**
	 * @return true if the stack is empty
	 */
	public boolean isEmpty()
	{
		return firstNode==null;
	}
	/**
	 * Push a String item to the stack
	 */
	public void push(String item)
	{
		ListNodeStringStack node = new ListNodeStringStack(item);
		if(isEmpty())
		{
			firstNode =lastNode = node;
		}
		else
		{
			node.nextNode = firstNode;
			firstNode = node;
		}
		
	}
	/**
	 * remove and return the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public String pop() throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException(name);
		}
		String removedItem = firstNode.data;
		
		if(firstNode==lastNode)
		{
			firstNode = lastNode=null;
		}
		else
		{
			firstNode=firstNode.nextNode;
		}
		return  removedItem;
	}

	/**
	 * return without removing the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public String peek() throws NoSuchElementException
	{
		if ( isEmpty() )
		{
			throw new NoSuchElementException(name);
		}
		else
		{
		String removedItem = firstNode.data; 
		return   removedItem; 
		}
	}

	/**
	 * print the contents of the stack, starting from the item
         * on the top,
	 * to the stream given as argument. For example, 
	 * to print to the standard output you need to pass System.out as
	 * an argument. E.g., 
	 * printStack(System.out); 
	 */
	public void printStack(PrintStream stream)
	{
		if ( isEmpty() )
		{
			System.out.printf( "Empty %s\n", name );
			return;
		}

		System.out.printf( "The %s is: ", name );
		ListNodeStringStack current = firstNode;
		
		while ( current != null )
		{
			System.out.printf( "%s ", current.data );
			current = current.nextNode;
		}
		System.out.println( "\n" );
	}

 	/**
         * return the size of the stack, 0 if empty
	 * @return the number of items currently in the stack
	 */
	public int size()
	{
		int k=0;
		ListNodeStringStack first2 = firstNode;
		while(first2!=null)
		{
			k +=1;
			first2=first2.nextNode;
		}
		return k;
	}

}
