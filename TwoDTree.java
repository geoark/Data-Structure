import java.io.*;
import java.util.*;

public class TwoDTree {
	private static final boolean HORIZONTAL = true; //horizontal orientation
	private static final boolean VERTICAL = false; //vertical orientation

	private TreeNode root; // root of the TwoTree
	private int size; // number of points in the tree
	private Point bestPoint; // nearest neighbor
	private double bestDist; // distance to nearest neighbor

	private static class TreeNode {

		private Point p; // the point
		private Rectangle rect; // the axis-aligned rectangle corresponding to
								// this node
		private TreeNode left; // the left/bottom subtree
		private TreeNode right; // the right/top subtree
		private boolean orientation; // orientation of node

		public TreeNode(Point point, boolean orientation, Rectangle rect) {
			this.p = point;
			this.orientation = orientation;
			this.rect = rect;
		}

		// is this point smaller than po?
		public int compareTo(Point po) {
			if (that == null)
				throw new NullPointerException("Null item");
			// but at the root we use the x-coordinate (if the point to be
			// inserted has a smaller x-coordinate than the point at the root,
			// go left; otherwise go right);
			if (this.p.y() == po.y() && this.p.x() == po.x())
				return 0;
			if (this.orientation == VERTICAL && po.x() < this.p.x()) //left
				return -1;
			if (this.orientation == HORIZONTAL && po.y() < this.p.y()) //left
				return -1;
			return +1; // right
		}

		public Rectangle getR(Point point) {
			int cmp = this.compareTo(point);
			if (cmp < 0) { //left
				if (this.orientation == VERTICAL)
					return new Rectangle(this.rect.xmin(), this.rect.ymin(), this.p.x(), this.rect.ymax()); // point has larger x-coordinate
				else 
					return new Rectangle(this.rect.xmin(), this.rect.ymin(), this.rect.xmax(), this.p.y()); //point has larger y- coordinate
			} else { //right
				if (this.orientation == VERTICAL)
					return new Rectangle(this.p.x(), this.rect.ymin(), this.p.x(), this.rect.ymin());
				else
					return new Rectangle(this.rect.xmin(), this.p.y(), this.rect.xmin(), this.rect.ymax());
			}
		}

	}

	// construct an empty tree of points
	public TwoDTree() {
		root = null;
		size = 0;
	}

	// is the tree empty?
	public boolean isEmpty() {
		return root == null;
	}

	// number of points in the tree
	public int size() {
		return this.size;
	}

	// add the point p to the tree (if it is not already in the tree)
	public void insert(Point p) {
		
		Rectangle r = new Rectangle(0, 0, 1, 1);
		root = insert(root, p, VERTICAL, r);

	}

	// insert the point in the subtree rooted at x
	
	private TreeNode insert(TreeNode node, Point p, boolean orient, Rectangle r) {
		if (node == null) {
			TreeNode result = new TreeNode(p, orient, r); //we create a new tree
			
			this.size++;
			return result; //return node
		}
		int cmp = node.compareTo(p); //we compare the node of the tree with the point p
		Rectangle rect = node.getR(p); //we check if the node matches the point p in the rectangle
		if (cmp < 0) { //node < p---> left
			node.left = insert(node.left, p, !node.orientation, rect);
		} else if (cmp > 0) { //node > p---> right
			node.right = insert(node.right, p, !node.orientation, rect);
		} else
			node.p = p;
		return node;
	}

	public boolean contains(Point p) {
		return search(p) != null;
	}

	private Point search(Point p) {
		return search(root, p);
	}

	private Point search(TreeNode node, Point p) {
		if (node == null)
			return null;
		int cmp = node.compareTo(p); //we compare the node with the point
		if (cmp < 0) 
			return search(node.left, p); //search to the left
		else if (cmp > 0)
			return search(node.right, p); //search to the right
		else
			return node.p;
	}

	// a nearest neighbor in the set to p; null if set is empty
	public Point nearestNeighbor(Point p) {
		// Maintain a global best estimate of the nearest neighbor, called
		// 'guess.'
		// Maintain a global value of the distance to that neighbor, called
		// 'bestDist'
		bestPoint = null;
		bestDist = 20000; // best distance is if dx=dy=100...is 100x100 +
							// 100x100 = 20000
		nearestNeighbor(root, p);
		return bestPoint;

	}

	private void nearestNeighbor(TreeNode node, Point p) {
		if (node == null)
			return;
		// If the current location is better than the best known location,
		// update the best known location.
		double distance = node.p.squareDistanceTo(p);
		if (distance < bestDist) {
			bestDist = distance;
			bestPoint = node.p;
		}
		// Recursively search the half of the tree that contains the test point.
		int cmp = node.compareTo(p);

		if (cmp < 0)
			nearestNeighbor(node.left, p);
		else if (cmp > 0)
			nearestNeighbor(node.right, p);
	}

	// all points in the set that are inside the rectangle
	public List<Point> rangeSearch(Rectangle rect) {
		List<Point> l = new List<Point>(); // we create the list 
		rangeSearch(root,l, rect); 
		return l;
	}

	private void rangeSearch(TreeNode node, List<Point> list, Rectangle rect) {
		if (node == null)
			return;
		if (rect.contains(node.p)) //if the rectangle contains the node then insert it into the list
			list.insertAtBack(node.p);
		if (node.left != null && rect.intersects(node.left.rect)) //if there is any node left at the left of the tree and it intersects the rectangle then
			rangeSearch(node.left, list, rect); //call the rangeSearch method and put the node into the list
		if (node.right != null && rect.intersects(node.right.rect))
			rangeSearch(node.right, list, rect);

	}

	public static void main(String[] args) throws IOException {
		Point[] point; //table that contains the number of elements 
		int lineNum = 0;
		try {

			String fileName = "TwoDTree.txt";
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = "";

			String[] arrs = null;
			
			lineNum = LengthLine(fileName); //we check how many points our tree contains (first line of txt)
			int num = 0;
			point = new Point[lineNum]; //the elements of each line - each point of each line 

			while ((line = br.readLine()) != null) { //we check the next lines of the txt file
				arrs = line.split(" ");
				int firstEl = 0, secondEl = 0;
				if (num == 0) {
					firstEl = Integer.valueOf(arrs[0]);
				} else {
					firstEl = Integer.valueOf(arrs[0]);
					secondEl = Integer.valueOf(arrs[1]);
				}
				if (num != 0) {
					point[num - 1] = new Point(firstEl, secondEl); //we put on each element of table each point

				}

				num++;
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSuchElementException("Cannot find the file. Exit Program!");
		}

		TwoDTree tree = new TwoDTree(); //we create the tree 
		for (int j = 0; j <lineNum ; j++) {
			tree.insert(point[j]);//insert each point that we already found
		}
		String menu = "\n Please select an option to perform" + "\n (1) Rectangle list." + "\n (2) List point."
				+ "\n (0) Exit Program"; //we create the menu with the options
		Scanner in = new Scanner(System.in);
		int answer = PrintMenu(menu, in);

		int i = 0;
		do {
			if (answer == 1) { //first choice
				
				BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));//input

				String r;

				System.out.println("Give rectangle: ");
				r = scan.readLine();
				r += " " + scan.readLine();
				String[] input = new String[4];
				while (true) {
					input = r.split(" "); //we split the input
					if (input.length == 4) {
						break;
					}
				}
				int xmin = Integer.parseInt(input[0]); //we give to the coordinates values
				int ymin = Integer.parseInt(input[1]);
				int xmax = Integer.parseInt(input[2]);
				int ymax = Integer.parseInt(input[3]);

				Rectangle e = new Rectangle(xmin, ymin, xmax, ymax);
				List<Point> et = tree.rangeSearch(e); //we create a list and check if the list contains the specific elements
				et.print();

				answer = PrintMenu(menu, in);//before we reach exit of the menu we call the method again

			} else if (answer == 2) { //second choice 

				System.out.println("Give the list point: ");
				BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
				String line;
				String[] input = new String[2];

				while ((line = st.readLine()) != null && line.length() != 0) {
					input = line.split(" "); //we split the input
					if (input.length == 2) {
						break;
					}
				    System.out.println("Error..please enter two numbers 1 to 100:");
					
				}
				int x = Integer.parseInt(input[0]); //we give to the coordinates values
				int y = Integer.parseInt(input[1]);
				Point nearest = tree.nearestNeighbor(new Point(x, y)); //we find the nearest element to the point
				System.out.println("Nearest:" + nearest);

				answer = PrintMenu(menu, in);//before we reach exit of the menu we call the method again

			} else if (answer == 0) {
				System.out.println("exit");
				i = 1; //exit
			} else {
				System.out.println("Please enter a number between 0 and 2");
				answer = PrintMenu(menu, in); //we call the method again

			}
		} while (i == 0);

	}

	public static int LengthLine(String fileName) { //returns the first number of points that the txt contains
		int i = 0;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			String[] arrs = null;

			while ((line = br.readLine()) != null) {
				arrs = line.split(" ");
				i = Integer.valueOf(arrs[0]);
				break;

			}
			br.close();
			fr.close();
			return i;

		} catch (IOException e) {
			e.printStackTrace();
			throw new NoSuchElementException("Cannot find the file. Exit Program!");
		}

	}

	private static int PrintMenu(String menu, Scanner in) { //prints the menu that we create
		System.out.println(menu);
		System.out.print("Enter your option: ");
		return in.nextInt();
	}
}
