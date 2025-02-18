import java.util.ArrayList;

public class Alphabet {

    private ArrayList<Character> alphabetArrayList;

    public Alphabet() {
        alphabetArrayList = completionAlphabetArrayList(new ArrayList<>());
    }


    private ArrayList<Character> completionAlphabetArrayList(ArrayList<Character> alphabetArrayList) {
        for (char symbol = 'A'; symbol <= 'Z'; symbol++) {
            alphabetArrayList.add(symbol);
        }
        for (char symbol = 'a'; symbol <= 'z'; symbol++) {
            alphabetArrayList.add(symbol);
        }
        for (char symbol = 'А'; symbol <= 'я'; symbol++) {
            alphabetArrayList.add(symbol);
        }

        alphabetArrayList.add('.');
        alphabetArrayList.add(',');
        alphabetArrayList.add('-');
        alphabetArrayList.add(':');
        alphabetArrayList.add(';');
        alphabetArrayList.add('!');


        return alphabetArrayList;
    }

//    public ArrayList<Character> getAlphabetArrayList() {
//        return alphabetArrayList;
//    }

    public int size() {
        return alphabetArrayList.size();
    }

    public char get(int index){
        return alphabetArrayList.get(index);
    }

    public boolean contains (char symbol){
        return alphabetArrayList.contains(symbol);
    }
    public int indexOf(char symbol){
        return alphabetArrayList.indexOf(symbol);
    }
}
