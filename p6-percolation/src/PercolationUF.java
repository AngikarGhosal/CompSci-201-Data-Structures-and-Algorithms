public class PercolationUF implements IPercolate
{
	private IUnionFind myFinder;
	private boolean[][] myGrid;
	private final int VTOP;
	private final int VBOTTOM;
	private int myOpenCount;
	
	/** Constructor for PercolationUF initializes the class variables.
	 *the IUnionFind object passed as a parameter is initialized, and the IUnionFind object in the class is assigned that.
	 *the myGrid object is initialized with all the n^2 cells being 'false'.
	 *VTOP and VBOTTOM are assigned a fixed value which will not be the same as the map of any legitimate row, col pair.
	 *@param finder, an IUnionFind class object, which is used to assign the value of myFinder
	 *@param size, an integer which provides the size of the NxN grid
	 */
	
	PercolationUF (IUnionFind finder, int size)
	{
		finder.initialize((size * size) + 2);
		myFinder = finder;
		myGrid = new boolean[size][size];
		for (int i=0; i<size; i++)
		{
			for (int j=0; j<size; j++)
			{
				myGrid[i][j]=false;
			}
		}
		myOpenCount=0;
		VTOP=size*size;
		VBOTTOM=size*size + 1;
	}
	
	/** Checks if the row and col values passed are valid indices or not.
	 *@param row, the row index
	 *@param col, the column index
	 *@return boolean value representing validity of indices
	 */
	
	public boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
	/**
	 * Returns true if and only if site (row, col) is OPEN
	 * Throws exception if row, col index is invalid by calling the inBounds method
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 * @return whether the referenced cell is open or not
	 */
	 @Override
	public boolean isOpen(int row, int col)
	{
		if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return (myGrid[row][col]);
	}
	
	/**
	* Returns true if and only if site (row, col) is FULL
	 * Throws exception if rowm col index is invalid by calling the inBounds method
	 * Uses the formula mapping every row, col position to an Integer
	 * Checks using the myFinder object, if the map Integer is connected to the value of VTOP
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 * @return whether the referenced cell is full or not
	 */
	 @Override
	 public boolean isFull (int row, int col)
	 {
		 if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		int d = row*(myGrid.length) + col;
		return (myFinder.connected(d,VTOP));
	 }
	 /**
	 * Returns true if the simulated percolation actually percolates. What it
	 * means to percolate could depend on the system being simulated, but
	 * returning true typically means there's a connected path from
	 * top-to-bottom.
	 * This is achieved by using the myFinder object, and checking if VTOP and VBOTTOM are connected.
	 * @return true iff the simulated system percolates
	 */
	 @Override
	 public boolean percolates()
	 {
		 return (myFinder.connected(VTOP,VBOTTOM));
	 }
	 /**
	 * Returns the number of distinct sites that have been opened in this
	 * simulation, which is stored in the variable myOpenCount
	 * 
	 * @return number of open sites
	 */
	 @Override
	 public int numberOfOpenSites()
	 {
		 return myOpenCount;
	 }
	 
	 /**
	 * Open site (row, col) if it is not already open. By convention, (0, 0)
	 * is the upper-left site
	 * 
	 * The method modifies internal state so that determining if percolation
	 * occurs could change after taking a step in the simulation.
	 * 
	 * Throws exception if the index (row, col) is not in bounds of the size of the grid.
	 *
	 * Then checks if the referenced cell is already open or not.
	 * Then marks the cell as open, increases myOpenCount by 1
	 * Then checks if any of the neighbouring cells is open or not as well.
	 * If yes, then uses myFinder to union the cell and its neighbour
	 * If the cell is in the top row, this method unions it with VTOP
	 * If the cell is in the bottom row, this method unions it with VBOTTOM
	 * @param row
	 *            row index in range [0,N-1]
	 * @param col
	 *            column index in range [0,N-1]
	 */
	 @Override
	 public void open (int row, int col)
	 {
		 if (! inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		 if (myGrid[row][col])
		 {
			 return;
		 }
		 int d = row*(myGrid.length) + col;
		 myGrid[row][col]=true;
		 myOpenCount=myOpenCount+1;
		 if (row==0)
		 {
			 myFinder.union(d, VTOP);
		 }
		 if (row==myGrid.length - 1)
		 {
			 myFinder.union(d, VBOTTOM);
		 }
		 if (row>0)
		 {
			 if (myGrid[row-1][col])
			 {
				 int q = (row-1)*(myGrid.length) + col;
				 myFinder.union(d,q);
			 }
		 }
		 if (col>0)
		 {
			 if (myGrid[row][col-1])
			 {
				 int q = row*(myGrid.length) + col-1;
				 myFinder.union(d,q);
			 }
		 }
		 if (row<myGrid.length -1)
		 {
			 if (myGrid[row+1][col])
			 {
				 int q = (row+1)*(myGrid.length) + col;
				 myFinder.union(d,q);
			 }
		 }
		 if (col<myGrid[0].length - 1)
		 {
			 if (myGrid[row][col+1])
			 {
				 int q = row*(myGrid.length) + col+1;
				 myFinder.union(d,q);
			 }
		 }
		 return;
	 }
}









