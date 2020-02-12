public class PercolationDFSFast extends PercolationDFS
{

    /**
     * Initialize a grid so that all cells are blocked.
     *
     * @param n is the size of the simulated (square) grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }
	/**This method overrides the definition of updateOnOpen in PercolationDFS.
	 *This new definition is more efficient.
	 *@param row is the row-index of the cell.
	 *@param col is the column-index of the cell.
	 *@return void, it is a void function.
	 */
    @Override
    public void updateOnOpen(int row, int col) 
	{
		boolean flag=false;
		if (row==0)
		{
			flag=true;
		}
		if (row>0)
		{
			if (myGrid[row-1][col]==FULL)
			{
				flag=true;
			}
		}
		if (row<myGrid.length-1)
		{
			if (myGrid[row+1][col]==FULL)
			{
				flag=true;
			}
		}
		
		if (col>0)
		{
			if (myGrid[row][col-1]==FULL)
			{
				flag=true;
			}
		}
		if (col<myGrid[0].length-1)
		{
			if (myGrid[row][col+1]==FULL)
			{
				flag=true;
			}
		}
		if (flag)
		{
			dfs(row,col);
		}
		return;
	}
}
