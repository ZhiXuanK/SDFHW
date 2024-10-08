package nextword;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //read the file

        boolean stop = false;

        //take in user input for desired word
        Console cons = System.console();

        while (!stop){

            String word = cons.readLine("Word: ").toLowerCase();
            Reader inputFile = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(inputFile);

            if (word.equals("brk")){
                stop = true;
                break;
            }
            
            Map<String, Integer> wordCount = new HashMap<>();

            String curr = "x";
            List<String> fullText = new ArrayList<>();

            while (curr != null){
                String line = br.readLine();
                if (line == null){
                    break;
                }
                for (String word2: line.split(" ")){
                    fullText.add(word2.toLowerCase());
                }
            }
            int highCount = 1;
            for (int i = 0; i < fullText.size()-1; i++){
                int currCount = 1;
                String currWord = fullText.get(i);
                String nextWord = fullText.get(i+1);
                if (word.equals(currWord)){
                    if (wordCount.containsKey(currWord)){
                        currCount = wordCount.get(currWord);
                        currCount += 1;
                        wordCount.put(nextWord, currCount);
                        if (currCount > highCount){
                            highCount = currCount;
                        }
                    } else {
                        wordCount.put(nextWord, currCount);
                    }
                }
            }
            List<String> answer = new ArrayList<>();
            for (String word3: wordCount.keySet()){
                if (wordCount.get(word3) == highCount){
                    answer.add(word3);
                }
            }
            br.close();
            System.out.println(answer);
        }

/*      String curr = "x";
        List<String> unique = new ArrayList<>();

        //gemerate a list of unique words
        while (curr != null){

            curr = br.readLine().toLowerCase();
            if (curr == null)
                break;
            for (String word:curr.split(" ")){
                if (unique.contains(word) == false){
                    unique.add(word);
                }
            }
        }*/



        //for each unique word, generate a dictionary that keeps a count on each word that appear after the word
        

        
        //take in user input and return the most probable next word



    }

}
