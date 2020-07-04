package com.kacper.zielinski.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
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
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
