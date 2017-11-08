/*
 * PrintJob.java
 */
public class PrintJob implements Comparable<PrintJob> {
	protected int id;
	protected int size;
	protected int waitingTime; 
	protected int arrivalTime; 
	protected int priority;
	private final static int MAXSIZE = 128;
	PrintJob p;
	
	public PrintJob(){ }
	
	public PrintJob(int id,int size,int waitingTime,int arrivalTime,int priority){ 
	    
		this.id=id;
		this.size=size;
		this.waitingTime=waitingTime;
		this.arrivalTime=arrivalTime;
		this.priority=priority;
	}
	
   public PrintJob(PrintJob p){ }
	
	public void Id(int id){
		this.id=id;
		
	}
	public int getId() {
        return id;
    } 
	public void Size(int size){
		if (size < 1 || size > MAXSIZE) throw new IllegalArgumentException(); 
	    this.size=size;
	}
	public int getSize() {
        return size;
    } 
	
	public void WaitingTime(int waitingTime){
		this.waitingTime=waitingTime;
		
	}
	public int getWaitingTime() {
        return waitingTime;
    } 
	public void ArrivalTime(int arrivalTime){
		this.arrivalTime=arrivalTime;
		
	}
	public int getArrivalTime() {
        return arrivalTime;
    } 
	
	public void Priority(int priority){
		if (priority < 0 || priority > 127) throw new IllegalArgumentException();
		this.priority=priority;
	}
	public int getPriority() {
        return priority;
    } 
    
	@Override
	public int compareTo(PrintJob ob){
		
		if (this.priority ==  ob.priority)
            return 0;
        else if (this.priority > ob.priority)
            return 1;
        else
            return -1;
	}
	public void print() {
        
        System.out.print(id+" "+size+" "+waitingTime+" "+arrivalTime+" "+ priority);
        
        System.out.println();
    }
	
	
	
}