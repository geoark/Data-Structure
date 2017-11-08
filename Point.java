public class Point {
	
	int x,y;
	
     //Construct the point (x, y).
     //Throws IllegalArgumentException if either x or y
     //  is (<0 or >100)
	
	public Point(int x,int y){
		if (x<0 || x>100)throw new IllegalArgumentException();
		if (y<0 || y>100)throw new IllegalArgumentException();
		this.x=x;
		this.y=y;
	}
	
	//Returns the x-coordinate.
	
	public int x(){return x;}
    
	//Returns the y-coordinate.
	
     public int y(){return y;}
	 
     //Returns the Euclidean distance between 2 points.
     
	public double distanceTo(Point z){
		double dx=x - z.x;
		double dy=y - z.y;
		return Math.sqrt(dx*dx + dy*dy);    
	}
	
	//Returns the square of the Euclidean distance between 2 points.
	
    public int squareDistanceTo(Point z){
		double dx=x - z.x;
		double dy=y - z.y;
		int e=(int)(dx*dx + dy*dy);
		return e;                        
	}
    
    //Returns string representation: (x, y)
    
	public String toString(){
		return "("+x()+", "+y()+")";
	}
	
}