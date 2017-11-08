//Each node contains an Object reference to data and a reference to the nextNode in the list.
public class ListNodeStringQueue {

	String data;
	ListNodeStringQueue nextNode;

	//Constructor. It initializes data and sets next node to null. 
	//string: a reference to node's data.
	ListNodeStringQueue( String string )
	{
		this( string, null );
	} 
    
	
	//constructor creates ListNodeStringQueue with passed data and next node
	//string: the reference to node's data
	//node: the next node in the list
	ListNodeStringQueue( String string, ListNodeStringQueue node )
	{
		data = string;
		nextNode = node;
	} 

	//returns this node's data
	//return the reference to node's data
	String getString()
	{
		return data; 
	} 
    
	//Get reference to next node
	//return the next node
	ListNodeStringQueue getNext()
	{
		return nextNode; 
	} 
}

