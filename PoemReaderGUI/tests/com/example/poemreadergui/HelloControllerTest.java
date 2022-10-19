package com.example.poemreadergui;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {

    public static Map<String, Integer> poemMap(String fileName) {
        Map<String, Integer> wordMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis);
             BufferedReader br = new BufferedReader(new InputStreamReader(dis))) {
            Pattern pattern = Pattern.compile("[^a-zA-Z]");
            String start = "<div class=\"chapter\">";
            String end = "</div><!--end chapter-->";
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains(start))
                    while (!line.contains(end)) {
                        line = line.toUpperCase();
                        line = line.replaceAll("<[^>]*>", "");
                        line = line.replaceAll("\\!@#$%^&*()<.*?>", "");
                        line = line.replaceAll("&.\"'*?;", "");
                        String[] words = pattern.split(line);
                        for (String word : words) {
                            if (wordMap.containsKey(word)) {
                                wordMap.put(word, (wordMap.get(word) + 1));
                            } else {
                                wordMap.put(word, 1);
                            }
                        }
                        line = br.readLine();
                    }
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        System.out.println(wordMap);
        return wordMap;
    }

    public static List<Map.Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
        Set<Map.Entry<String, Integer>> entries = wordMap.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        return list.subList(1, 21);
    }

    public void PoemReader() {
        Map<String, Integer> wordMap = poemMap("src/main/resources/com/example/poemreadergui/testingRaven.html");
        List<Map.Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
        Map<String, Integer> tableMap = wordMap;
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }

    @Test
    public void testPoemReaderMethod() {
        PoemReader();
    }

    @Test
    public void testPoemMapMethod() {
        poemMap("C:\\Users\\b00kb\\OneDrive\\Desktop\\Software Development I\\Module 6\\PoemReaderGUI\\src\\main\\resources\\com\\example\\poemreadergui\\testingRaven.html");
    }

}