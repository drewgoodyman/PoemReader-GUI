package com.example.poemreadergui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WordCounterApp extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("main.fxml"));
        Parent rootFrame = loader.load();
        window = primaryStage;
        window.setScene(new Scene(rootFrame, 605, 400));
        window.setTitle("Word Counter");
        window.show();
    }
    public static void main(String[] args) { launch();
    }
}

      /*  Button buttonStart = new Button();
        buttonStart.setOnAction(e -> {
            try {
                PoemReader();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

    static void PoemReader() throws FileNotFoundException {
        Map<String, Integer> wordMap = poemMap("C:\\Users\\b00kb\\eclipse-workspace\\PoemReaderGUI\\src\\main\\java\\testingRaven.html");
        List<Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
        System.out.println("list of the top 20 most frequently used words");
        System.out.println("*********************************************");
        for (Map.Entry<String, Integer> entry : list) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " \t\t\t ***  " + entry.getValue());
            }
        }
    }
    private static Map<String, Integer> poemMap(String fileName) {
        Map<String, Integer> wordMap = new HashMap<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis);
             BufferedReader br = new BufferedReader( new InputStreamReader(dis))) {
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
            ioex.printStackTrace(); }
        return wordMap;
    }
    private static List<Entry<String, Integer>> sortByValueInDecreasingOrder( Map<String, Integer> wordMap) {
        Set<Entry<String, Integer>> entries = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        return list.subList(1, 21);
    }

     */