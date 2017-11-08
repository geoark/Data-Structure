public class Rectangle {

	int xmin, ymin, xmax, ymax;

	// Construct the rectangle [xmin, ymin] x [ xmax , ymax ]
	// Throws IllegalArgumentException if either xmin or ymin or xmax or ymax
	// is (<0 or >100)
	// Throws IllegalArgumentException if either xmin > xmax or ymin > ymax
	// is (<0 or >100)

	public Rectangle(int xmin, int ymin, int xmax, int ymax) {
		if (xmax < xmin || ymax < ymin) {
           throw new IllegalArgumentException("Invalid rectangle");
        }
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	   
	}

	// minimum x-coordinate of rectangle

	public int xmin() {
		return xmin;
	}

	// minimum y-coordinate of rectangle

	public int ymin() {
		return ymin;
	}

	// maximum x-coordinate of rectangle

	public int xmax() {
		return xmax;
	}

	// maximum y-coordinate of rectangle

	public int ymax() {
		return ymax;
	}

	// Returns true if this rectangle contain the point.
	// Return true if this rectangle contain the point p,
	// possibly at the boundary; false otherwise

	public boolean contains(Point p) {
		return (p.x() >= xmin) && (p.x() <= xmax) && (p.y() >= ymin) && (p.y() <= ymax);
	}

	// Returns true if the two rectangles intersect.
	// Return true if this rectangle intersect the argument
	// rectangle at one or more points

	public boolean intersects(Rectangle that) {
		return this.xmax >= that.xmin && this.ymax >= that.ymin && that.xmax >= this.xmin && that.ymax >= this.ymin;
	}

	// Euclidean distance from p
	// to closest point in rectangle

	public double distanceTo(Point p) {
		return Math.sqrt(this.squareDistanceTo(p));
	}

	// square of Euclidean
	// distance from p to closest point in rectangle

	public int squareDistanceTo(Point p) {
		double dx = 0.0, dy = 0.0;
		if (p.x() < xmin)
			dx = p.x() - xmin;
		else if (p.x() > xmax)
			dx = p.x() - xmax;
		if (p.y() < ymin)
			dy = p.y() - ymin;
		else if (p.y() > ymax)
			dy = p.y() - ymax;
		int e=(int)(dx*dx + dy*dy);
		return e;
	}

	// Returns string representation:
	// [xmin, xmax] x [ymin, ymax]

	public String toString() {
		return "[" + xmin() + ", " + ymin() + "] x [" + xmax() + ", " + ymax() + "]";
	}

}