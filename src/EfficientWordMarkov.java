import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
    //setTraining and getFollows
    //WordGram
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(){
        this(3);

    }

    public EfficientWordMarkov(int order) {
        super(order);
        myOrder = order;
        myMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text){
        myWords = text.split("\\s+");
        myMap.clear();
        String next;
        for(int i = 0; i<myWords.length-myOrder+1; i++){
            int plus = i+myOrder;
            WordGram model = new WordGram(myWords, i, myOrder);
            if(!myMap.containsKey(model)){
                myMap.put(model, new ArrayList<String>());
            }
            else if(myWords.length == plus){
                myMap.get(model).add(PSEUDO_EOS);
            }
            else{
                next = myWords[plus];
                myMap.get(model).add(next);
            }
        }

    }

    @Override
    public ArrayList<String> getFollows(WordGram key){
        if(!myMap.containsKey(key)){
            throw new NoSuchElementException(key+" not in map");
        }
        else{
            return myMap.get(key);
        }
    }


}
