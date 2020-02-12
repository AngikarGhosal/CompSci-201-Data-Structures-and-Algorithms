public class LinkStrand implements IDnaStrand
{
	/**
	 * This method implements a StringBuilder object which takes in the data of
	 * all the Nodes and a string is created by using the StringBuilder toString() method
	 * @return String object containing the String representation of the LinkStrand object
	 */
	@Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	Node tmp = myFirst;
    	while(tmp!=null) {
    		s.append(tmp.info);
    		tmp = tmp.next;
		}
    	return s.toString();
	}

	/**
	 * This method returns the sum of the length of the strings in the LinkStrand,
	 * which is stored as mySize, a long variable within the class
	 * @return long object containing the size of the LinkStrand stored as mySize
	 */
	@Override
    public long size() {
        return mySize;
    }

	/**
	 * This method initializes the myFirst and myLast as Node objects with the value
	 * source and sets myAppends to its initial value of 0
	 * @param String object source containing the info value of first Node
	 */
    @Override
    public void initialize(String source)
	{
		myFirst = new Node (source);
		mySize=source.length();
		myAppends = 0;
		myLast=myFirst;
		myCurrent = myFirst;
		myIndex = 0;
		myLocalIndex = 0;
    }
	/**
	 * This method returns a new instance LinkStrand object which is initialized
	 * with the value source
	 * @param String object containing the value that the returned LinkStrand object is initialized with
	 * @return IDnaStrand object containing the created instance variable LinkStrand object
	 */
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

	/**
	 * Overrides the append method in the previous IDnaStrand to add the new Node containing
	 * the String dna to the end of the LinkStrand list at hand while incrementing myAppends
	 * @param String object dna containing the value to be stored in the new Node that is appended
	 * @return IDnaStrand object containing the new LinkStrand object with the added node at the end
	 */
    @Override
    public IDnaStrand append(String dna) {
        Node q = new Node (dna);
		myLast.next=q;
		myLast=q;
		mySize=mySize+dna.length();
		myAppends=myAppends+1;
		return this;
    }

	/**
	 * This method reverses the current LinkStrand object by creating a new one
	 * and returning that one
	 * @return IDnaStrand object containing the reversed LinkStrand object
	 */
    @Override
    public IDnaStrand reverse() {
        if (myFirst==null)
		{
			return null;
		}
		LinkStrand back = new LinkStrand();
		Node curr = myFirst;
		while (curr!=null)
		{
			Node temp=new Node(revstr(curr.info));
			temp.next=back.myFirst;
			back.myFirst=temp;
			back.myAppends=back.myAppends+1;
			curr=curr.next;
		}
		back.mySize=mySize;
		return back;
    }

	/**
	 * This is a helper method which reverses the String in that is stored in a Node given
	 * the String as an input
	 * @param String object s to be reversed
	 * @return String object that is the reversed version of s
	 */
	private String revstr (String s)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		sb=sb.reverse();
		return sb.toString();
	}

	/**
	 * This method is a getter method which returns the myAppends property of the
	 * LinkStrand class
	 * @return int object containing the number of total appends or myAppends
	 */
    @Override
    public int getAppendCount() {
        return myAppends;
    }

	/**
	 * This method is an O(1) version of the regular charAt which returns the value
	 * at a given index in the LinkStrand without looping through all the values
	 * every time
	 * @param int index which contains the index of the char in the linkStrand that should be returned
	 * @return char object containing the character value at the index location in the LinkStrand
	 */
    @Override
	public char charAt(int index)
	{
		int count=0;
		int dex=0;
		Node list=myFirst;
		while(count!=index)
		{
			count++;
			dex++;
			if (dex>=list.info.length())
			{
				dex=0;
				list=list.next;
			}
			if (list==null)
			{
				throw new IndexOutOfBoundsException();
			}
		}
		return list.info.charAt(dex);
	}
	
	
	/*
    public char charAt(int index) 
	{
		myIndex=index;
		myLocalIndex=myIndex;
		char c;
		while (myCurrent!=null)
		{
			if (myCurrent.info.length()<=myLocalIndex)
			{
				myLocalIndex=myLocalIndex-myCurrent.info.length();
				myCurrent=myCurrent.next;
			}
			else
			{
			return myCurrent.info.charAt(myLocalIndex);
			}
		}
		throw new IndexOutOfBoundsException();
    }
*/
	/**
	 * This is a private helper class that contains the information which defines
	 * a single Node object within the LinkStrand
	 */
	private class Node
	{
		String info;
		Node next;

		/**
		 * The constructor for the Node class which initiales the next as null and
		 * sets the info value of the created Node to String s
		 * @param String s which contains the String value that the Node stores
		 */
		public Node(String s)
		{
			info=s;
			next=null;
		}
	}
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

	/**
	 * This constructor of LinkStrand contains a call to initialize and leads to the
	 * creation of the LinkStrand with a single Node with value String k
	 * @param String object k containing the String that the LinkStrand object should have
	 * in its first Node upon creation
	 */
	public LinkStrand (String k)
	{
		initialize(k);
	}

	/**
	 * This default constructor of LinkStrand contains a call to initialize and leads to the
	 * creation of the LinkStrand with a single Node with value of empty String ""
	 * @return String object k containing the String that the LinkStrand object should have
	 * in its first Node upon creation
	 */
	public LinkStrand()
	{
		this("");
	}
}


















