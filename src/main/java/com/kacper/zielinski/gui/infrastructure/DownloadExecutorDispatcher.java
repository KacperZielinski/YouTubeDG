package com.kacper.zielinski.gui.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class DownloadExecutorDispatcher {

    private List<String> youtubeUrls;
    private List<Thread> threadList = new ArrayList<>();

    public DownloadExecutorDispatcher(List<String> youtubeUrls) {
        this.youtubeUrls = youtubeUrls;
    }

    public void dispatch() {
        // TODO just use some thread pool...
        youtubeUrls.forEach(url -> threadList.add(new Thread(new DownloadExecutor(url))));
        // TODO implement dispatch logic.
        for (Thread thread: threadList) {
            thread.start();
        }

        // TODO clear this list only when all was deleted or do sth else (just skip downloaded..)
        youtubeUrls.clear();
    }
}
