package com.example.poemreadergui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class HelloController implements Initializable {

    @FXML
    private Button buttonQuit;

    @FXML
    private ImageView picture;

    @FXML
    private Button buttonStart;

    @FXML
    private Label labelTitle;

    @FXML
    private TableView<tableStorage> table;

    @FXML
    private TableColumn<tableStorage, String> column1;

    @FXML
    private TableColumn<tableStorage, Integer> column2;

    ObservableList<tableStorage> tableList = FXCollections.observableArrayList(
            new tableStorage("the", 56),
            new tableStorage("and", 38),
            new tableStorage("i", 32),
            new tableStorage("my", 24),
            new tableStorage("of", 22),
            new tableStorage("that", 18),
            new tableStorage("this", 16),
            new tableStorage("a", 15),
            new tableStorage("door", 14),
            new tableStorage("is", 11),
            new tableStorage("nevermore", 11),
            new tableStorage("chamber", 11),
            new tableStorage("bird", 11),
            new tableStorage("raven", 10),
            new tableStorage("on", 10),
            new tableStorage("me", 10),
            new tableStorage("at", 9),
            new tableStorage("with", 8),
            new tableStorage("or", 8),
            new tableStorage("then", 8)
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column1.setCellValueFactory(new PropertyValueFactory<tableStorage, String>("word"));
        column2.setCellValueFactory(new PropertyValueFactory<tableStorage, Integer>("frequency"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setItems(tableList);
    }


    @FXML
    public void buttonQuit(ActionEvent d) {
        System.exit(0);
    }

    @FXML
    public void buttonStart(ActionEvent e1) {
       buttonStart.setOnAction(e -> {
           picture = new ImageView(new Image("C:\\Users\\b00kb\\eclipse-workspace\\PoemReaderGUI\\src\\main\\resources\\com\\example\\poemreadergui\\edgarallenpoe.jpg"));
        });
    }

    @FXML
    static void PoemReader() throws FileNotFoundException {
        Map<String, Integer> wordMap = poemMap("C:\\Users\\b00kb\\eclipse-workspace\\PoemReaderGUI\\src\\main\\java\\testingRaven.html");
        List<Map.Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " \t\t\t ***  " + entry.getValue());
            }
        }
    }
    @FXML
    private static Map<String, Integer> poemMap(String fileName) {
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
                        line = line.toLowerCase();
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
    @FXML
    private static List<Map.Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
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

}



