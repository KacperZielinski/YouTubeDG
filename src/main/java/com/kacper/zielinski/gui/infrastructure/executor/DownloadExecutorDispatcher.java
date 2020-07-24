package com.kacper.zielinski.gui.infrastructure.executor;

import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class DownloadExecutorDispatcher {

    private List<String> youtubeUrls;
    private List<DownloadExecutor> threadList = new ArrayList<>();
    private ProgressBar overallProgressBar;

    public DownloadExecutorDispatcher(List<String> youtubeUrls, ProgressBar overallProgressBar) {
        this.youtubeUrls = youtubeUrls;
        this.overallProgressBar = overallProgressBar;
    }

    public void dispatch() {
        // TODO just use some thread pool...
        youtubeUrls.forEach(url -> threadList.add(new DownloadExecutor(url)));
        System.out.println("GIVEN SIZE IS: " + youtubeUrls.size());
        // TODO implement dispatch logic.

        // TODO move it somewhere else..
        Thread progressIndicator = new Thread(() -> {
            double overallStatus = 0;
            double tasksCount = threadList.size();

            while(overallStatus < 100 && tasksCount > 0) {
                overallStatus = threadList.stream().mapToDouble(DownloadExecutor::getProgress).sum() / tasksCount;
                overallProgressBar.setProgress(overallStatus / 100);

                System.out.println("overallStatus " + overallStatus);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            overallProgressBar.setProgress(0.0);
        });

        progressIndicator.setDaemon(true);
        progressIndicator.start();

        for (DownloadExecutor thread: threadList) {
            System.out.println("<_> NEW DOWNLOAD THREAD HAS STARTED!");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        threadList.clear();
    }
}
