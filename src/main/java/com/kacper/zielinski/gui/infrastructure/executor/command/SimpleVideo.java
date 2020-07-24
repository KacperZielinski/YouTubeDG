package com.kacper.zielinski.gui.infrastructure.executor.command;

public class SimpleVideo implements YoutubeDlCommand {

    @Override
    public String getCommand(String url) {
        return url;
    }
}
