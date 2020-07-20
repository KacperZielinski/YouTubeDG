package com.kacper.zielinski.gui.infrastructure;

import java.util.List;

public class DownloadExecutorDispatcher {

    private List<Thread> threadList;

    public DownloadExecutorDispatcher(List<Thread> threadList) {
        this.threadList = threadList;
    }

    public void dispatch() {
        // TODO implement dispatch logic.
        for (Thread thread: threadList) {
            thread.start();
        }
    }
}
