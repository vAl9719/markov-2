import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
		myMap = new HashMap<String, ArrayList<String>>();
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}

	@Override
	public void setTraining(String text){
		myText = text;
		myMap.clear();
		String model;
		String next;
		for(int i = 0; i< myText.length()-myOrder+1; i++){
			int plus = i+myOrder;
			model = myText.substring(i, plus);
			if(!myMap.containsKey(model)){
				myMap.put(model, new ArrayList<String>());
			}
			else if(myText.length() == plus){
				myMap.get(model).add(PSEUDO_EOS);
			}
			else{
				next = myText.substring(plus, plus+1);
				myMap.get(model).add(next);
			}
		}

	}

	@Override
	public ArrayList<String> getFollows(String key){
		if(!myMap.containsKey(key)){
			throw new NoSuchElementException(key+" not in map");
		}
		else{
			return myMap.get(key);
		}
	}

}	
