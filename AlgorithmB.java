import java.io.*;

public class AlgorithmB extends PrintJob{   
	
	public static void runB {

        FileReader fr=new FileReader("test.txt");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        int num=1;
        
		
        ListPrintJob l1=new ListPrintJob();
		PrintJob t=new PrintJob();
	    MaxPQ al = new MaxPQ(128); 
		
        while ((line=br.readLine())!=null) {
            arrs=line.split(" ");
			int a =Integer.valueOf(arrs[0]);
			int b =Integer.valueOf(arrs[1]);
			int y=a+b;
     
		   t=new PrintJob(num,b,a,y,num);
		   
		   l1.add(t); //add items in linked list
		    
		   al.insert(t); //insert elements in pq
		  
		   
		   
		   num++;
        }
        l1.print(); //print the elements of linked list
        al.print(); //print the elements of MaxPq
        
        
        br.close();
        fr.close();
    
    }
}