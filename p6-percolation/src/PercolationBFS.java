import java.io.*;
import java.util.*;
public class PercolationBFS extends PercolationDFSFast
{

    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationBFS(int n) 
	{
        super(n);
    }
	/**
	 * Uses a breadth-first search approach to mark cells as FULL.
	 * Extend the fast depth-first search approach.
	 *@param row is the row-index of the cell
	 *@param col is the column-index of the cell
	 *@return void, as it is a void function, and merely updates the grid
	 */
	@Override
	public void dfs(int row, int col)
	{
		int sz = myGrid.length;
		Queue<Integer> qp = new LinkedList<>();
		myGrid[row][col]=FULL;
		qp.add(row*sz + col);
		while (qp.size() !=0)
		{
			Integer r = qp.remove();
			int nr = r/sz;
			int nc = r%sz;
			if (nr>0)
			{
				if (myGrid[nr-1][nc]==OPEN)
				{
					myGrid[nr-1][nc]=FULL;
					qp.add((nr-1)*sz + nc);
				}
			}
			if (nc>0)
			{
				if (myGrid[nr][nc-1]==OPEN)
				{
					myGrid[nr][nc-1]=FULL;
					qp.add(nr*sz + nc-1);
				}
			}
			if (nr<myGrid.length-1)
			{
				if (myGrid[nr+1][nc]==OPEN)
				{
					myGrid[nr+1][nc]=FULL;
					qp.add((nr+1)*sz + nc);
				}
			}
			if (nc<myGrid[0].length -1)
			{
				if (myGrid[nr][nc+1]==OPEN)
				{
					myGrid[nr][nc+1]=FULL;
					qp.add(nr*sz + nc+1);
				}
			}
		}
		return;
	}
}
