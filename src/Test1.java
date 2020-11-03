import java.util.Dictionary;

public class Test1 {
    public static void main(String argsp[]){
        for(int i=3; i>=1; i--){
            System.out.println("HI"+i);
        }
        Test1 yes = new Test1();
    }
    public Test1(){
        String A = "aeroplanefa";
        String B = "azhgeahgeog";
        int different;
        different = Hi(A,B);
        System.out.println(different);
    }
    /*
    private static int DifferentLetters(String A, String B){
        int SameLetters = 0;
        int DiffLetters=0;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i)!=B.charAt(i)){
                SameLetters++;
                //System.out.print(SameLetters);
            }
            /*if(SameLetters==(A.length()-1)){
                DiffLetters = A.length()-SameLetters;
            }*/
        //}

        //return SameLetters;
    //}

    private int Hi(String A, String B){
        int SameLetters = 0;
        int DiffLetters=0;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i)!=B.charAt(i)){
                DiffLetters++;
            }
        }

        return DiffLetters;
    }

         /*
        Iterator<String> iterate = words.iterator();
        String itrWord;
        if((start.length()!=end.length())){
            throw new IllegalArgumentException();
        }
        else {
            while (iterate.hasNext()) {
                itrWord = iterate.next();

                if ((itrWord.length() == start.length())) {
                    aryList.add(itrWord);
                }
            }

            //System.out.println(aryList);


            for (int i = 0; i < aryList.size(); i++) {
                neighbours.put(aryList.get(i), sameSize);
            }

            System.out.println(neighbours);
            for (int i = 0; i < aryList.size(); i++) {
                for (int j = 0; j < aryList.size(); j++) {
                    //check = DifferentLetters(aryList.get(i), aryList.get(j));
                    if (DifferentLetters(aryList.get(i), aryList.get(j)) == 1) {
                        neighbours.get(aryList.get(i)).add(aryList.get(j));
                        //System.out.println(aryList.get(i)+" "+(aryList.get(j)));
                        neighbours.get(aryList.get(j)).add(aryList.get(i));
                        //System.out.println(aryList.get(j));
                    }
                }
            }
            //System.out.println(neighbours);
        }
        */

}




/*
/*
import java.util.*;
/*
/**
 * This class uses the Levenshtein method to
 * find the distance between two words.
 *
 * @author Lucas D. Dahl
 * @version 10/06/20
 *
 */
/*
public class LevenshteinFinder {

    // **************************** Fields ****************************
    private String start, end;
    private Map<String, Set<String>> neighbours = new TreeMap<>();
    private int distance = -1;
    private List<String> pathArray = new ArrayList<>();


    public LevenshteinFinder(String start, String end, Set<String> words) {

        if(start.length() != end.length()) {
            throw new IllegalArgumentException("Both words must be the same length!");
        } else {
            this.start = start;
            this.end = end;
        }

        // Set up the map
        Iterator<String> iterate = words.iterator();
        String itrWord;

        while(iterate.hasNext()){
            itrWord = iterate.next();

            if((itrWord.length()!=start.length())){
                iterate.remove();
            }
        }
        for(String word: words) {
            neighbours.put(word, setMapValues(word, words));
        }

        // Find the distance.
        distance = findDistance(start, end);

        // Find the path
        findPath(start, end);

    }


    private Set<String> setMapValues(String wordKey, Set<String> setWords) {

        // Properties
        Set<String> wordSet = new TreeSet<>();

        // Loop through the set.
        for(String word: setWords) {
            if(DifferentLetters(wordKey, word) == 1) {
                wordSet.add(word);
            }
        }
        return wordSet;
    }


    private int DifferentLetters(String A, String B){
        int diffLetters=0;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i)!=B.charAt(i)){
                diffLetters++;
            }
        }
        //System.out.println(DiffLetters);
        return diffLetters;
    }

    private int findDistance(String A, String B) {

        // Properties
        Set<String> setA = new TreeSet<>();
        Set<String> setB = new TreeSet<>();
        int counter = 0;

        // Set the second set
        setB.add(A);

        while(setA.size() != setB.size() && !setB.contains(B)) {

            // Add all the words from the second set into the first set.
            for(String word: setB) {
                setA.add(word);
            }

            // Clear the second set
            setB.clear();

            // Add every word in the first set and their neighbor into the second set.
            for(String word: setA) {
                setB.add(word);
                for(String wordNeighbor: neighbours.get(word)) {
                    setB.add(wordNeighbor);
                }
            }

            // Increase the counter
            counter++;

        }
        // If the second set does not contain the end word, return -1
        if (!setB.contains(B)) {
            counter = -1;
        }
        return counter;
    }

    private void findPath(String startWord, String endWord) {

        // Properties
        Set<String> pathSet;

        if(distance < 0) {
            pathArray.add("There is no path.");
            return;
        } else {

            // Add the first element.
            pathArray.add(startWord);
            pathArray.add("->");
            pathSet = neighbours.get(startWord);

            for(int i = (distance - 1); i >= 1; i--) {
                for(String x: pathSet) {
                    if(findDistance(x, endWord) == i) {
                        pathArray.add(x);
                        pathArray.add("->");
                        pathSet = neighbours.get(x);
                    }
                }
            }
            // Add the last word to the path
            pathArray.add(endWord);

        }
    }

    /**
     * This method will return the distance between the two words.
     * @return will return the distance between the two words.
     */
/*    public int getDistance() {
        return distance;
    }

    /**
     *  This method will return the path between the two words
     * @return will return the string path.
     */
/*    public String getPath() {

        String stringPath = "";

        for(int i = 0; i < pathArray.size(); i++) {
            stringPath += (pathArray.get(i));
        }
        return stringPath;
    }

}*/
