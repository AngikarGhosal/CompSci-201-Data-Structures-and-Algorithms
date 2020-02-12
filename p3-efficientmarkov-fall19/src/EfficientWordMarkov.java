import java.io.*;
import java.util.*;


public class EfficientWordMarkov extends BaseWordMarkov
{
	private Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov()
	{
		this(2);
	}
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap=new HashMap<>();
	}
	
	@Override	
	public void setTraining(String text)
	{
		super.setTraining(text);
		myMap.clear();
		for (int index=0; index<=myWords.length-myOrder; index++)
		{
			WordGram key = new WordGram(myWords, index, myOrder);
			ArrayList<String>af = new ArrayList<>();
			String n="";
			if (index==myWords.length - myOrder)
			{
				n=PSEUDO_EOS;
			}
			else
			{
				n=myWords[index+myOrder];
			}
			if (!myMap.containsKey(key))
			{
				af.add(n);
				myMap.put(key,af);
			}
			else
			{
				af=myMap.get(key);
				af.add(n);
				myMap.put(key,af);
			}
		}
	}	
	@Override
	public ArrayList<String> getFollows(WordGram key)
	{
		if (myMap.containsKey(key))
		{
			return myMap.get(key);
		}
		else
		{
			throw new NoSuchElementException(key +" not in map");
		}
	}	

}
