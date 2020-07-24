package com.kacper.zielinski.gui;

import com.kacper.zielinski.gui.domain.content.YoutubeContent;
import com.kacper.zielinski.gui.domain.content.YoutubeContentImpl;
import com.kacper.zielinski.gui.domain.content.YoutubeContentType;
import com.kacper.zielinski.gui.infrastructure.executor.DownloadExecutorDispatcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class MainLayoutController {

    @FXML
    private TextField newUrl;

    @FXML
    private ProgressBar overallProgressBar;

    @FXML
    private ListView<String> youtubeListView;

    @FXML
    private RadioButton videoContentType;

    private static final ObservableList<String> listViewList = FXCollections.observableList(new ArrayList<>());

    private static final List<YoutubeContent> youtubeUrls = new ArrayList<>();

    public MainLayoutController() {}

    @FXML
    public void initialize() {
        youtubeListView.setItems(listViewList);
    }

    @FXML
    protected void addYoutubeUrl(ActionEvent event) {
        String youtubeUrl = newUrl.getText();

        // TODO make really true validation
        if(youtubeUrl.contains("youtu")) {
            youtubeUrls.add(new YoutubeContentImpl(newUrl.getText(), getYoutubeContentType()));

            // TODO its temporarly, make it more common, e.g. object for it ?
            listViewList.add(newUrl.getText());
        }
        newUrl.clear();
    }

    private YoutubeContentType getYoutubeContentType() {
        YoutubeContentType youtubeContentType = YoutubeContentType.MP3;
        if(videoContentType.isSelected()) {
            youtubeContentType = YoutubeContentType.VIDEO;
        }
        return youtubeContentType;
    }

    @FXML
    protected void downloadAll(ActionEvent event) {
        // TODO start it within new Thread :)
        // TODO allow to choose between movie and mp3 etc.
        new Thread(() -> {
            DownloadExecutorDispatcher dispatcher = new DownloadExecutorDispatcher(youtubeUrls, overallProgressBar);
            dispatcher.dispatch();
        }).start();

        // TODO clear this list only when all was deleted or do sth else (just skip downloaded..), typical wait(), notify() pattern
        listViewList.clear();
    }
}
