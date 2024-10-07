package playstore;

import java.io.*;
import java.nio.*;
import java.util.*;

public class AppRatingMain{

    public static void main(String[] args) throws FileNotFoundException, IOException{
        //process the files
        //idea: initiate a dictionary with the first line (AKA column names), create array for each row
        Reader inputFile = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(inputFile);
        
        List<Map<String, String>> processedFile = new ArrayList<>();

        String line = "x";
        //int count = 1;
        String[] line1 = br.readLine().toLowerCase().split(",");

        while (line != null){
            line = br.readLine();

            if (line ==null){
                break;
            }

            String[] currLine = line.toLowerCase().split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)");
            Map<String, String> currMap = new HashMap<>();
            for (int i = 0; i < 3; i++){
                currMap.put(line1[i],currLine[i]);
            }
            processedFile.add(currMap);
            //System.out.println(currMap);
        }
        //System.out.println(processedFile);
        //display for each category: category name, highest rated app + score, lowest rated app + score, average category rating
        String[] cats = {"category name", "highest rated app", "highest rating", "lowest rated app", "lowest rating", "average category rating"};
        List<Map<String, String>> output = new ArrayList<>();
        List<String> catNames = new ArrayList<>();
        for (Map<String,String> map:processedFile){
            //System.out.println(map);
            String word = map.get("category");
            //word = word.trim();
            //System.out.println(word);
            if (catNames.contains(word) == false){
                catNames.add(word);
                //System.out.println(word);
            }
        }
        for(String word:catNames){
            Map<String, String> map2 = new LinkedHashMap<>();
            float highRating = 0;
            String highName = "";
            float lowRating = 100;
            String lowName = "";
            float ratingCount = 0;
            int count = 0;

            for (Map<String, String> map:processedFile){
                //System.out.println(map);
                if (map.get("category").equals(word)){
                    count ++;
                    String appRating = map.get("rating");
                    if (appRating.equals("nan") || appRating.matches("[a-zA-Z]+")==true)
                        break;
                    Float appRatingInt = Float.parseFloat(appRating);
                    String appName = map.get("app");
                    //String currCat = map.get("category");
                    if (appRatingInt > highRating){
                        highRating = appRatingInt;
                        highName = appName;
                    }
                    if (appRatingInt < lowRating){
                        lowRating = appRatingInt;
                        lowName = appName;
                    }
                    ratingCount += appRatingInt;
                }
            }
            map2.put(cats[0], word);
            map2.put(cats[1], highName);
            map2.put(cats[2], String.valueOf(highRating));
            map2.put(cats[3],lowName);
            map2.put(cats[4], String.valueOf(lowRating));
            float average = ratingCount/count;
            map2.put(cats[5], String.valueOf(average));
            //System.out.println(map2);
            output.add(map2);
        }
        System.out.println(output);
    }
    

}
