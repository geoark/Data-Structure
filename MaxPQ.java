/*
 * MaxPQ.java
 */

import java.util.Comparator;

public class MaxPQ {
    /**
     * Array based heap representation
     */
    private PrintJob[] pq;
    /**
     * The number of objects in the priority queue
     */
    private int size;
    /**
     * Comparator.
     */
    protected Comparator<PrintJob> cmp;
    
    public MaxPQ(){}
    /**
     * Creates heap with a given capacity and default comparator.
     * param capacity The capacity of the heap being created.
     */
     public MaxPQ(int capacity) {
        this(capacity, new DefaultComparator());
	}
	
	/**
     * Creates heap with a given capacity and comparator.
     * param capacity The capacity of the heap being created.
     * param cmp The comparator that will be used.
     */
    public MaxPQ(int capacity, Comparator<PrintJob> cmp) {
        if (capacity < 1) throw new IllegalArgumentException();
        this.pq = new PrintJob[capacity+1];
        this.size = 0;
        this.cmp = cmp;
    }
    
    /**
     * Inserts an object in this heap.
     * throws IllegalStateException if heap capacity is exceeded.
     * param p The object to insert.
     */
    public void insert(PrintJob p) {                     
        // Ensure object is not null
        if (p == null) throw new IllegalArgumentException();
        // Check available space
        if (size == pq.length-1) throw new IllegalStateException();
		//If the size of the queue reaches >= 75% of the length of the array, the length of the array is doubled.
		if (size > 3*(pq.length -1)/4) resize(2*pq.length); 
        // Place object at the next available position
        pq[++size] = p;
        // Let the newly added object swim
	    swim(size); 
    }
       /**
     * Removes the object at the root of this heap.
     * throws IllegalStateException if heap is empty.
     * return The object removed.
     */ 
    public PrintJob getMax() {                  
        // Ensure not empty
        if (size == 0) throw new IllegalStateException();
		 // Replace root object with the one at rightmost leaf
		 if (size > 1) pq[1] = pq[size];
		int k=0;
		PrintJob max = pq[1];
		for(int i=0; i<size-1; i++){
			PrintJob p1 = new PrintJob(pq[i+1]);
	        PrintJob p2 = new PrintJob(pq[i+2]);
	        int result = p1.compareTo(p2);
	        if(result < 1){
				max=pq[i+2];
				k=i+2;
			}else if(result > 1){
				max=pq[i+1];
				k=i+1;
			}
	    }
        // Dispose the rightmost leaf
		pq[k] = null;
		for(int i = k; i < size; i++){
			pq[i]=pq[i+1];
		}
        pq[size--] = null;
		// Sink the new root element
        sink(1);
		
		// Return the object removed
        return max;
       
	}
       
    public PrintJob peek() {                          
           // Ensure not empty
        if (size == 0) throw new IllegalStateException();
		 // Replace root object with the one at rightmost leaf
		if (size > 1) pq[1] = pq[size]; 
		PrintJob max = pq[1];
		for(int i=0; i<size-1; i++){
			PrintJob p1 = new PrintJob(pq[i+1]);
			PrintJob p2 = new PrintJob(pq[i+2]);
	        int result = p1.compareTo(p2);
	        if(result < 1){
				max=pq[i+2];
			
			}else if(result >1){
				max=pq[i+1];
				
			}
	    }
		// Return the object with the max priority
        return max;
        
    }
	
	/**
     * Shift up.
     */
    private void swim(int i) {
        while (i > 1) {  //if i root (i==1) return
            int p = i/2;  //find parent
            int result = cmp.compare(pq[i], pq[p]);  //compare parent with child
            if (result <= 0) return;    //if child <= parent return
            swap(i, p);                 //else swap and i=p
            i = p;
        }
    }
	
	/**
     * Shift down.
     */
    private void sink(int i){
        int left = 2*i, right = left+1, max1 = left;
        // If 2*i >= size, node i is a leaf
        while (left <= size) {
            // Determine the largest children of node i
            if (right <= size) {
                max1 = cmp.compare(pq[left], pq[right]) < 0 ? right : left;
            }
            // If the heap condition holds, stop. Else swap and go on.
            if (cmp.compare(pq[i], pq[max1])>= 0) return;
            swap(i, max1);
            i = max1; left = 2*i; right = left+1; max1 = left;
        }
    }
    
	
	private void swap(int i, int j) {
        PrintJob tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }
	
	private void resize(int capacity1) { 
        PrintJob[] copy = (PrintJob[]) new Comparable[capacity1];
        for(int i = 1; i <= size; i ++ ) {
			copy[i] = pq[i];
		}
            
        pq = copy;
    }
	
	public int size(){                          
		int k=0;
		PrintJob a = pq[0];
		while(a!=null)
		{
			k +=1;
			a=pq[k];
		}
		return k;
	}
	public void print() {
        for (int i=1; i<=size; i++){
        	pq[i].print();
            //System.out.print(pq[i]+ " ");
        }
        System.out.println();
    }
    /**
     * Checks if heap is empty.
     */
    public boolean isEmpty(){                           
		return size == 0;
    }
}