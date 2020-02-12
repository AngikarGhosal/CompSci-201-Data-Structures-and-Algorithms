import java.io.*;
import java.util.*;

/*The SlowBruteAutocomplete class is mostly as efficient as the BruteAutocomplete, using the same amount of memory as BruteAutocomplete. The timing to run
 *is similar to BruteAutocomplete
 */

public class SlowBruteAutocomplete extends BruteAutocomplete
{
    /**
     * Create immutable instance with terms constructed from parameter
     *
     * @param terms   words such that terms[k] is part of a word pair 0 <= k < terms.length
     * @param weights weights such that weights[k] corresponds to terms[k]
     * @throws NullPointerException     if either parameter is null
     * @throws IllegalArgumentException if terms.length != weights.length
     * @throws IllegalArgumentException if any elements of weights is negative
     * @throws IllegalArgumentException if any elements of terms is duplicate
     */
    public SlowBruteAutocomplete(String[] terms, double[] weights) {
        super(terms, weights);
    }

    public List<Term> slowTopM (String prefix, int k)
	{
		List<Term> list = new ArrayList<>();
		for (Term t:myTerms)
		{
			if (t.getWord().startsWith(prefix))
			{
				list.add(t);
			}
		}
		Collections.sort(list, Comparator.comparing(Term::getWeight).reversed());
		List<Term> ans = list.subList(0, Math.min(list.size(), k));
		return ans;
	}
}
