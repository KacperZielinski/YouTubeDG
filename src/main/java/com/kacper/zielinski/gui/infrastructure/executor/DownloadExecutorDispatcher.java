package com.kacper.zielinski.gui.infrastructure.executor;

import com.kacper.zielinski.gui.domain.content.YoutubeContent;
import javafx.scene.control.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class DownloadExecutorDispatcher {

    private List<YoutubeContent> youtubeContentList;
    private List<DownloadExecutor> threadList = new ArrayList<>();
    private ProgressBar overallProgressBar;

    public DownloadExecutorDispatcher(List<YoutubeContent> youtubeContentList, ProgressBar overallProgressBar) {
        this.youtubeContentList = youtubeContentList;
        this.overallProgressBar = overallProgressBar;
    }

    public void dispatch() {
        // TODO just use some thread pool...
        youtubeContentList.forEach(url -> threadList.add(new DownloadExecutor(url)));
        System.out.println("GIVEN SIZE IS: " + youtubeContentList.size());
        // TODO implement dispatch logic.

        // TODO move it somewhere else..
        Thread progressIndicator = new Thread(() -> {
            double overallStatus = 0.0;
            double tasksCount = threadList.size();

            try {
                while(overallStatus < 100 && tasksCount > 0) {
                    double progress = threadList.stream().mapToDouble(DownloadExecutor::getProgress).sum();

                    if (progress >= 0.1) {
                        overallStatus = progress / tasksCount;
                        overallProgressBar.setProgress(overallStatus / 100);
                    }
                    Thread.sleep((long) (tasksCount * 200));
                }
                Thread.sleep(1000);
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

        threadList.clear();
    }
}
