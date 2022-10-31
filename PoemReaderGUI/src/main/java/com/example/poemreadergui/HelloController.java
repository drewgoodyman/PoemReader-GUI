package com.example.poemreadergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/** *This is the controller for the whole application *@param HelloController */
public class HelloController {
    /** *These are the interface buttons, pictures, labels for the scene builder *@param buttonQuit */
    @FXML
    private Button buttonQuit;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param picture */
    @FXML
    private ImageView picture;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param buttonBasic */
    @FXML
    private Button buttonBasic;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param buttonFancy */
    @FXML
    private Button buttonFancy;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param buttonStart */
    @FXML
    private Button buttonStart;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param buttonClear */
    @FXML
    private Button buttonClear;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param labelTitle */
    @FXML
    private Label labelTitle;

    /** *These are the interface buttons, pictures, labels for the scene builder *@param mapOutput */
    @FXML
    private TextArea mapOutput;

    /** *This is the method that builds the word counting map *@param fileName */
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
        return wordMap;
    }

    /** *This is the method that sorts the wordMap into descending order *@param wordMap */
    public static  List<Map.Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
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

    /** *This is the program for counting word frequency within a file *@param PoemReader */
    public void PoemReader() {
        /** This is the method that starts the word frequency and counts application */
        Map<String, Integer> wordMap = poemMap("src/main/resources/com/example/poemreadergui/testingRaven.html");
        List<Map.Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
        for (Map.Entry<String, Integer> entry : list) {
            /** This outputs the word and its corresponding frequency to a label on the scene */
           mapOutput.appendText( entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }

    /** *This updates the scene label to display information regarding the class *@param buttonStart */
    @FXML
    public void buttonStart(ActionEvent e1) {
        buttonStart.setOnAction(e -> {
            mapOutput.setFont(Font.font("Britannic Bold", 30));
            mapOutput.setText("Andrew Goodman" + "\n" + "Module 6 UI Design Assignment" + "\n" + "Prof. Walauskis");
        });
    }

    /** *This updates the scene label to wipe the text from the scene *@param buttonClear */
    @FXML
    public void buttonClear(ActionEvent f1) {
        buttonClear.setOnAction(e -> {
            mapOutput.setText("");
                });
    }
    /** *This updates the scene label to display the PoemReader application in a fancy font *@param buttonFancy */
    @FXML
    public void buttonFancy(ActionEvent g1) {
        buttonFancy.setOnAction(e -> {
            mapOutput.setFont(Font.font("Papyrus", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
            PoemReader();
        });
    }
    /** *This updates the scene label to display the PoemReader application in a basic font *@param buttonBasic */
    @FXML
    public void buttonBasic(ActionEvent h1) {
        buttonBasic.setOnAction(e -> {
            mapOutput.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
            PoemReader();
        });
    }
    /** *This closes the program *@param buttonQuit */
    @FXML
    public void buttonQuit(ActionEvent d1) {
        System.exit(0);
    }
}





