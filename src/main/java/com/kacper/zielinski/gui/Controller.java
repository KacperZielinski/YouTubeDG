package com.kacper.zielinski.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField newUrl;

    @FXML
    private ListView<String> youtubeListView;

    private static final ObservableList<String> youtubeUrls = FXCollections.observableList(new ArrayList<>());

    public Controller() {
        // TODO set it somewhere earlier..
        //youtubeListView.setItems(youtubeUrls);
    }

    @FXML
    protected void addYoutubeUrl(ActionEvent event) {
        youtubeUrls.add(newUrl.getText());
        youtubeUrls.forEach(System.out::println);

        // TODO refactor....
        youtubeListView.setItems(youtubeUrls);
        newUrl.clear();
    }

    @FXML
    protected void downloadAll(ActionEvent event) {
        // TODO allow to choose between movie and mp3 etc.
        youtubeUrls.forEach(this::dummyDownloadOfMp3);
        youtubeUrls.clear();
    }

    private void dummyDownloadOfMp3(String url) {
        // TODO find youtube-dl path..
        String youtubeDlPath = "C:\\Users\\Kacper\\Desktop\\Software\\YouTubeDl\\";

        // TODO output folder
        // TODO user can choose title and more...
        String command = youtubeDlPath + "youtube-dl.exe --newline -i -o \"C:\\Users\\Kacper\\Documents\\YouTubeDG\\%(title)s.%(ext)s\" -x --audio-format mp3 --ignore-config --hls-prefer-native --audio-quality 0 " + url;

        System.out.println(command);

        // TODO display more info about failure/success

        try {
            Process process = Runtime.getRuntime().exec(command);

            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // TODO two reading threads ??
            // or TODO progressbar based on String...
            // TODO output progress on some listview...
            // Read command standard output
            String s;
            System.out.println("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // Read command errors
            System.out.println("Standard error: ");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
