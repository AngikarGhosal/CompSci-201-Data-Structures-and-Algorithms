import java.util.*;

/**
 * Implements Autocompletor by scanning through the entire array of terms for
 * every topKMatches or topMatch query.
 */
public class BruteAutocomplete implements Autocompletor {

	protected Term[] myTerms;
	protected int mySize;

	/**
	 * Create immutable instance with terms constructed from parameter
	 * @param terms words such that terms[k] is part of a word pair 0 <= k < terms.length
	 * @param weights weights such that weights[k] corresponds to terms[k]
	 * @throws NullPointerException if either parameter is null
	 * @throws IllegalArgumentException if terms.length != weights.length
	 * @throws IllegalArgumentException if any elements of weights is negative
	 * @throws IllegalArgumentException if any elements of terms is duplicate
	 */
	public BruteAutocomplete(String[] terms, double[] weights) {

		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}

		if (terms.length != weights.length) {
			throw new IllegalArgumentException("terms and weights are not the same length");
		}
		initialize(terms,weights);
	}
	
	/**
	* Function which returns a list of the k Term objects, if there exist that many, which start with given parameter 'k'.
	* @param prefix a String object, and have the highest weights
	* @param integer k number of terms are returned if there exist that many
	* @return a list of Term objects
	* This is done by implementing a priority queue which keeps maximum length at 'k'.
	* @throws NullPointerException if prefix is null
	* @throws IllegalArgumentException if k is negative
	**/
	@Override
	public List<Term> topMatches(String prefix, int k) 
	{
		if (prefix==null)
		{
			throw new NullPointerException();
		}
		
		if (k < 0) {
			throw new IllegalArgumentException("Illegal value of k:"+k);
		}
		
		// maintain pq of size k
		PriorityQueue<Term> pq = 
				new PriorityQueue<Term>(Comparator.comparing(Term::getWeight));
		for (Term t : myTerms) {
			if (!t.getWord().startsWith(prefix))
				continue;
			if (pq.size() < k) {
				pq.add(t);
			} else if (pq.peek().getWeight() < t.getWeight()) {
				pq.remove();
				pq.add(t);
			}
		}
		int numResults = Math.min(k, pq.size());
		LinkedList<Term> ret = new LinkedList<>();
		for (int i = 0; i < numResults; i++) {
			ret.addFirst(pq.remove());
		}
		return ret;
	}

	@Override
	public void initialize(String[] terms, double[] weights) {
		myTerms = new Term[terms.length];

		HashSet<String> words = new HashSet<>();

		for (int i = 0; i < terms.length; i++) {
			words.add(terms[i]);
			myTerms[i] = new Term(terms[i], weights[i]);
			if (weights[i] < 0) {
				throw new IllegalArgumentException("Negative weight "+ weights[i]);
			}
		}
		if (words.size() != terms.length) {
			throw new IllegalArgumentException("Duplicate input terms");
		}
	}
	
	@Override
	public int sizeInBytes() {
		if (mySize == 0) {
			
			for(Term t : myTerms) {
			    mySize += BYTES_PER_DOUBLE + 
			    		BYTES_PER_CHAR*t.getWord().length();	
			}
		}
		return mySize;
	}
}
