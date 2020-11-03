import java.util.*;
import java.util.Set;
import java.util.List;

/**
 * This class will find the distance between two words
 *
 * @author Hla Htun
 * @version 10/14/2020
 */
public class LevenshteinFinder {

    private int diffLetters;
    private int distance;
    private String start;
    private String end;
    private Map<String, Set<String>> neighbours = new TreeMap<>();
    private List<String> pathArray = new ArrayList<>();

    /**
     * This is the main constructor, here a map will be created with words that are off by just letter then it will use the starting and ending word to implement other constructors and find the distance between those two words.
     * @param start This is the starting word
     * @param end This is the ending word
     * @param words This is the set of words from the text file
     */
    public LevenshteinFinder(String start, String end, Set<String> words){

        Iterator<String> iterate = words.iterator(); //Using iterator to remove the words from the set which are not of the same length
        String itrWord;
        if((start.length()!=end.length())){
            throw new IllegalArgumentException();
        }
        else{
            this.start = start;
            this.end = end;
            while(iterate.hasNext()){
                itrWord = iterate.next();

                if((itrWord.length()!=start.length())){
                    iterate.remove();
                }
            }
        }



        //int checkNeighbour=0;
        for(String stringA: words) {
            //Set<String> sameSize = new TreeSet<>();
            neighbours.put(stringA, new TreeSet<>()); //creating a map with the trimmed words and empty set for each key word
        }


        for(String A: words) {
            for(String B: words){
                if(DifferentLetters(A,B)==1){
                    neighbours.get(A).add(B); //if A and B are neighbours, add B to the set of A in the map and vice versa
                    neighbours.get(B).add(A);
                    //System.out.println(" "+A+" "+B+"|");
                }
            }
        }

        distance = findDistance(start,end); //call the findDistance method to find the distance between the starting and ending word
        findPath(start,end); //call findPath to find the path needed to take the starting word to the ending word
    }

    //This will find out if two words are neighbours by finding how many letters they are different by
    private int DifferentLetters(String A, String B){
        diffLetters=0;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i)!=B.charAt(i)){
                diffLetters++;
            }
        }
        //System.out.println(DiffLetters);
        return diffLetters;
    }

    /**
     * This method will return the path between the starting and ending words
     * @return It will return a string which is the path
     */
    public String getPath() {

        String stringPath = "";

        if(distance<0){
            return "There is no path for those two words!";
        }
        else {
            for (int i = 0; i < pathArray.size(); i++) {
                stringPath += (pathArray.get(i)); //add whatever is in the pathArray and return it
            }
        }

        return stringPath;

    }

    //This method will find the distance between the starting and ending word.
    //It will find out how many words does it need to change until it reaches the ending word
    private int findDistance(String A, String B){
        Set<String> setA = new TreeSet<>();
        Set<String> setB = new TreeSet<>();
        int counter = 0;

        setB.add(A);

        while((setA.size()!=setB.size()) && (!setB.contains(B))){
            for(String x: setB){
                setA.add(x); //add words from setB to setA then clear out setB
            }
            //setA.addAll(setB);
            setB.clear();

            for(String word: setA){
                setB.add(word);
                for(String wordB: neighbours.get(word)){
                    setB.add(wordB); //add the neighbouring words from setA to setB
                }
                //setB.addAll(neighbours.get(word));

            }
            //System.out.println(setB);
            counter++;

        }
        if(setB.contains(end)){
            return counter;
        }
        else
            return -1;
    }

    /**
     *
     * @return This will return the distance between the starting and ending word
     */
    public int getDistance(){
        return distance;
    }

    //This method will find the path needed to take to reach the ending word
    private void findPath(String start, String end) {

        Set<String> newSet; //new Set is created

        if(distance < 0) {
            pathArray.add("There is no path.");
            return;
        }
        else {
            
            pathArray.add(start); // Add the first element to the String list
            pathArray.add("->"); // Then add arrows
            newSet = neighbours.get(start); //add the neighbouring words of the starting word into the set
            //neighbours.get(pathArray.get(pathArray.size()-1)) //I could not seem to put this inside the for loop, it always gave me Null Exception

            for(int i=(distance-1); i>=1; i--) {
                for(String nextWord: newSet) {
                    if(findDistance(nextWord,end)==i) { //find words from the neighbours to the ending word that matches the distance-1
                        pathArray.add(nextWord); //when found, add that word to the String list
                        pathArray.add("->");
                        newSet = neighbours.get(nextWord); //find the neighbours of that word and put it in the set
                        break; //if found break so the neighbour of THAT word can be used next
                    }
                     //Then add arrows
                    //newSet = neighbours.get(nextWord);
                }
            }
            pathArray.add(end); //add the ending word to the String list to finish it off

        }
    }

}

