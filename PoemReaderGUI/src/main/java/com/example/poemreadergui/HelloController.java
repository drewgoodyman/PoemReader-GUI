package com.example.poemreadergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class HelloController {

    @FXML
    private Button buttonQuit;

    @FXML
    private ImageView picture;

    @FXML
    private Button buttonBasic;

    @FXML
    private Button buttonFancy;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonClear;

    @FXML
    private Label labelTitle;

    @FXML
    private TextArea mapOutput;
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

    public void PoemReader() {
        Map<String, Integer> wordMap = poemMap("src/main/resources/com/example/poemreadergui/testingRaven.html");
        List<Map.Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
        Map<String, Integer> tableMap = wordMap;
        for (Map.Entry<String, Integer> entry : list) {
           mapOutput.appendText( entry.getKey() + "\t" + entry.getValue() + "\n");
        }
    }
    @FXML
    public void buttonStart(ActionEvent e1) {
        buttonStart.setOnAction(e -> {
            mapOutput.setFont(Font.font("Britannic Bold", 30));
            mapOutput.setText("Andrew Goodman" + "\n" + "Module 6 UI Design Assignment" + "\n" + "Prof. Walauskis");
        });
    }

    @FXML
    public void buttonClear(ActionEvent f1) {
        buttonClear.setOnAction(e -> {
            mapOutput.setText("");
                });
    }
    @FXML
    public void buttonFancy(ActionEvent g1) {
        buttonFancy.setOnAction(e -> {
            mapOutput.setFont(Font.font("Papyrus", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
            PoemReader();
        });
    }
    @FXML
    public void buttonBasic(ActionEvent h1) {
        buttonBasic.setOnAction(e -> {
            mapOutput.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));
            PoemReader();
        });
    }

    @FXML
    public void buttonQuit(ActionEvent d1) {
        System.exit(0);
    }
}





