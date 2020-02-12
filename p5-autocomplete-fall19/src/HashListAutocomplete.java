import java.io.*;
import java.util.*;

public class HashListAutocomplete implements Autocompletor
{
	private static final int MAX_PREFIX=10;
	private Map<String, List<Term>> myMap;
	private int mySize;
	
	/**
	 * Create immutable instance with terms constructed from parameter
	 * @param terms words such that terms[k] is part of a word pair 0 <= k < terms.length
	 * @param weights weights such that weights[k] corresponds to terms[k]
	 * @throws NullPointerException if either parameter is null
	 * @throws IllegalArgumentException if terms.length != weights.length
	 * @throws IllegalArgumentException if any elements of weights is negative
	 * @throws IllegalArgumentException if any elements of terms is duplicate
	 */
	public HashListAutocomplete(String[] terms, double[] weights) {
		
		if (terms == null||weights==null) {
			throw new NullPointerException("One or more arguments null");
		}
		if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
		initialize(terms,weights);
	}
	
	@Override
	public void initialize(String[] terms, double[] weights)
	{
		int i;
		mySize=0;
		HashSet<String> words = new HashSet<>();
		myMap = new HashMap<String, List<Term>>();
		HashSet<String>ajebaje=new HashSet<>();
		ArrayList<Term>dhush=new ArrayList<>();
		for (i=0; i<terms.length; i++)
		{
			if (weights[i] < 0)
			{
				throw new IllegalArgumentException("Negative weight "+ weights[i]);
			}
			Term k = new Term(terms[i], weights[i]);
			mySize += BYTES_PER_DOUBLE + 
			    		  BYTES_PER_CHAR*k.getWord().length();	
			words.add(terms[i]);
			int s = k.getWord().length();
			if (s>MAX_PREFIX)
			{
				s=MAX_PREFIX;
			}
			int j;
			dhush.add(k);
			for (j=1;j<=s; j++)
			{
				String sub = terms[i].substring(0,j);
				if (!ajebaje.contains(sub))
				{
					ajebaje.add(sub);
					List<Term>r = new ArrayList<>();
					myMap.put(sub, r);
				}
				List<Term>q = myMap.get(sub);
				q.add(k);
				myMap.put(sub,q);
			}			
		}
		myMap.put("", dhush);
		for (String z: myMap.keySet())
		{
			mySize=mySize+z.length()*BYTES_PER_CHAR;
			List<Term> w = myMap.get(z);
			Collections.sort(w, Comparator.comparing(Term::getWeight).reversed());
			myMap.put(z,w);
		}
	}
	
	/**
	 * Required by the Autocompletor interface. Returns an array containing the
	 * k words in myTerms with the largest weight which match the given prefix,
	 * in descending weight order. If less than k words exist matching the given
	 * prefix (including if no words exist), then the array instead contains all
	 * those words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then
	 * topKMatches("b", 2) should return {"bell", "bat"}, but topKMatches("a",
	 * 2) should return {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An array of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k
	 *         such words exist, return an array containing all those words If
	 *         no such words exist, reutrn an empty array
	 * @throws NullPointerException if prefix is null
	 */
	 
	@Override
	public List<Term> topMatches(String prefix, int k)
	{
		/*if (prefix==null)
		{
			throw new NullPointerException();
		*/
		/*List<Term> all = new ArrayList<>();
		if (prefix.equals(""))
		{
			HashSet<Term>jkl = new HashSet<Term>();
			for (String j: myMap.keySet())
			{
				jkl.addAll(myMap.get(j));
			}
			for (Term t:jkl)
			{
				all.add(t);
			}
			List<Term> ans = all.subList(0, Math.min(k, all.size()));
			Collections.sort(ans, Comparator.comparing(Term::getWeight).reversed());
			return ans;
		}*/
		if (prefix.length()>MAX_PREFIX)
		{
			prefix=prefix.substring(0,MAX_PREFIX);
		}
		if (!myMap.containsKey(prefix))
		{
			return new ArrayList<Term>();
		}
		List<Term> s = myMap.get(prefix);
		List<Term> gg = s.subList(0, Math.min(k, s.size()));
		return gg;	
	}	
	
	
	@Override
	public int sizeInBytes()
	{	
		return mySize;
	}
}
