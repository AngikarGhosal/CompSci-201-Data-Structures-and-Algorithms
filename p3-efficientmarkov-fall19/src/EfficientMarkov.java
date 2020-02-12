import java.util.*;

public class EfficientMarkov extends BaseMarkov
{
  private Map<String,ArrayList<String>> myMap;

  public EfficientMarkov(int order)
  {
     super(order);
     myMap=new HashMap<>();
  }

  public EfficientMarkov() {this(3);}

  @Override
  public void setTraining(String text) {
     super.setTraining(text);
     myMap.clear();
     for(int index=0; index<=text.length()-myOrder;index++)
     {
        String key=myText.substring(index,index+myOrder);
        ArrayList<String>after=new ArrayList<>();
        String next="";
        if(index==text.length()-myOrder)
           next=PSEUDO_EOS;
        else
           next=myText.substring(index+myOrder,index+myOrder+1);
        if(myMap.containsKey(key))
        {
           after=myMap.get(key);
           after.add(next);
           myMap.put(key,after);
        }
        else
        {
           after.add(next);
           myMap.put(key,after);
        }
     }
  }

  @Override
  public ArrayList<String> getFollows(String key)
  {
     if(!myMap.containsKey(key))
        throw new NoSuchElementException(key+" not in map");
     return myMap.get(key);
  }

}

