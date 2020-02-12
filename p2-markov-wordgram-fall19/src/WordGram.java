import java.io.*;
import java.util.*;
/**
 * A WordGram represents a sequence of strings
 * just as a String represents a sequence of characters
 * 
 * @ANGIKAR GHOSAL
 *
 */
public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create WordGram by creating instance variable myWords and copying
	 * size strings from source starting at index start
	 * @param source is array of strings from which copying occurs
	 * @param start starting index in source for strings to be copied
	 * @param size the number of strings copied
	 */
	public WordGram(String[] source, int start, int size)
	{
		myWords = new String[size];
		myToString = null;
		myHash = 0;
		int i;
		for (i=0; i<size; i++)
		{
			myWords[i]=source[i+start];
		}
		myToString=this.toString();
		myHash=this.hashCode();
		// TODO: initialize all instance variable
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * The length of the array which stores the strings of the wordgram is returned.
	 * @return
	 */
	public int length()
	{
		return myWords.length;
	}


	/**
	 * It is first checked if the object is expressable as a Wordgram. If yes, we see if its length is the same as the length of the first wordgram (length of array).
	 * Then, we compare the strings by concatenating the array elements to see if the objects are the same or not.
	 * @param o
	 * @return a boolean value
	 */
	@Override
	public boolean equals(Object o)
	{
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		int wgl=wg.length();
		int thisl=this.length();
		if (thisl!=wgl)return false;
		else
		{
			if ((this.toString()).equals(wg.toString()))
				return true;
			else 
				return false;
		}
	}
	

	@Override
	public int hashCode()
	{
		if (myHash==0)
		{
			myHash=myToString.hashCode();
		}
		return myHash;
	}
	

	/**
	 * The array of the initial WordGram is coped to a new WordGram, with the strings deleted and changed as needed. Then, myToString and myHash are computed automatically by setting the values to the new WordGram.
	 * @param last is last String of returned WordGram
	 * @return a new WordGram object with the additional string incorporated
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords,0,myWords.length);
		ArrayList <String> al = new ArrayList<>(Arrays.asList(wg.myWords));
		al.remove(wg.myWords[0]);
		al.add(last);
		wg.myWords = new String [al.size()];
		wg.myWords = al.toArray(wg.myWords);
		WordGram ans = new WordGram(wg.myWords, 0, wg.myWords.length);
		return ans;
	}

	@Override
	public String toString()
	{
		if (myToString==null)
		{
			myToString=String.join(" ",myWords);
		}
		return myToString;
	}
}
