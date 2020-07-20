package com.kacper.zielinski.gui;

import com.kacper.zielinski.gui.infrastructure.DownloadExecutorDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField newUrl;

    @FXML
    private ProgressBar overallProgressBar;

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
        // TODO start it within new Thread :)
        // TODO allow to choose between movie and mp3 etc.
        DownloadExecutorDispatcher dispatcher = new DownloadExecutorDispatcher(youtubeUrls);
        dispatcher.dispatch();
    }

}
