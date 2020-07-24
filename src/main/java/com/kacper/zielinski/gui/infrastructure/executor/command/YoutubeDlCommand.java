package com.kacper.zielinski.gui.infrastructure.executor.command;

/**
 * Represent command for youtube-dl given by specified type e.g. mp3, mp4 etc.
 */
public interface YoutubeDlCommand {
    String getCommand(String url);
}
