import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Thiseas {
	private static char[][] maze;
	private static int rows;
	private static int columns;
	private static final char block = '1'; // dead-end
	private static final char space = '0'; // there is an open path
	private static final char tried = '#'; // we mark the spot that we are already and check if there is a 
	                                      //path or a dead-end
	private static final char path = '.'; //we mark the spot that we have visited
	

	public static void main(String[] args) { 
		
		try {
			//Read File
			String fileName = "thiseas.txt";
			
			//Create object of FileReader
			FileReader inputFile = new FileReader(fileName);
			
			BufferedReader bufferReader = new BufferedReader(inputFile);
			String line;
			
			
			int k = -2;     //counter to know the specific row that i am in the txt file
			int ro = 0;    //variable that contains the row in which the entrance of the maze
			int col = 0; //variable that contains the column in which there's the entrance of the maze
			int r=0; //variable that contains the  number of rows
			while ((line = bufferReader.readLine()) != null) {
				
				StringTokenizer tok = new StringTokenizer(line," ");
				
				if (k == -2){    //I am in the first row of the text
				 
				  rows = Integer.parseInt(tok.nextToken());
				  columns = Integer.parseInt(tok.nextToken());
				  maze = new char[rows][columns];
				  k++;
				  
				}else if (k==-1){  //I am in the second row of text
					ro = Integer.parseInt(tok.nextToken());
				    col = Integer.parseInt(tok.nextToken());
					
				    k++;
				    
					
				}else{    //Located between 3-11 row of text (1-9 row of maze)
					
					while(k!=rows){	// k will enter the while loop as many times as are the rows of maze
						int c=0;  //variable that contains the  number of columns
						
					    for (int j = 0; j < (2*columns-1); ++j) { //variable that contains the  number of columns and gaps in the text file
						
							char a=line.charAt(j); //i initiate character to check out whether  is empty or is an element of the maze
							if(a!=' '){          // if you do not empty the characters in the table
						        
								maze[r][c] = line.charAt(j);
							  
							}else{
								c++;       //If blank raises the index c columns by one
								
							}
						}
						
						if(r==rows-1){  //if r is -1 rows must stop to increase (because the table starts at 0)
							k++;
						}else{ 
						   r++;
						   k++;
						}
						if(k==r){
							break;
						}
						
					}
					
				}
		
			}
			String exit = String.valueOf(findsolution(ro,col)); //convert boolean method to string for the right exit
			if (exit=="false"){
				System.out.printf("There is no output!"); 
			}else{
				System.out.printf(exit);
			}
			
			bufferReader.close(); //close the text file
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSuchElementException("Cannot find the file. Exit Program!"); 
		}

	}

	public static boolean findsolution(int i, int j) {

		StringStackImpl s = new StringStackImpl(); // we create object s for the stack
		boolean done = false;
		
		if (blocked(i, j)) {
			return false;
		} else if (maze[0][j] == space && i==0) {  // if the output is in the first row
			System.out.printf("Exit: 0,"+j);
			System.out.println(" ");
			return true;
		} else if (maze[i][0] == space && j==0) {    // if the output is in the first column
			System.out.printf("Exit: "+ i +",0"+" ");
			System.out.println(" ");
			return true;
		} else if (maze[8][j] == space && i==8) {     // if the output is in the last row
			System.out.printf("Exit: 8,"+ j+" ");
			System.out.println(" ");
			return true;
		} else if (maze[i][6] == space && j==6) {     // if the output is in the last column
			System.out.printf("Exit: "+ i + ",6"+" ");
			System.out.println(" ");
			return true;
		} else {
			
			
			if (!done) {
				maze[i][j] = tried;             //I have checked out this item
				done = findsolution(i, j + 1); // we are going right
			}
			if (!done) {
				maze[i][j] = tried;            //I have checked out this item
				done = findsolution(i - 1, j); // we are going up
			}
			if (!done) {
				maze[i][j] = tried;             //I have checked out this item
				done = findsolution(i, j - 1); // we are going left
			}
			if (!done) {
				maze[i][j] = tried;             //I have checked out this item
			    done = findsolution(i + 1, j); // we are going down
			}else{
				if (done) {
			       maze[i][j] = path;
			       s.push(Integer.toString(maze[i][j])); //if we can continue on the path adds the valid position in the stack
		        } else {
			        maze[i][j] = tried;        //I have checked out this item
			        s.pop();                 //if I reached a dead-end and have to turn back to find a different path that leads the elements (which i can not continue) from the stack
		        }
			}
			
		}
		
		return done;
	}

	private static boolean blocked(int i, int j) { //we check if we are out of bounds or reach a dead-end or we have already visited the specific spot

		return i < 0 || j < 0 || i >= rows || j >= columns || maze[i][j] == block || maze[i][j] == tried;
	}
	
}