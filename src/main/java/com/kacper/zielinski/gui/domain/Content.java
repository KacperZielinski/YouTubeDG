package com.kacper.zielinski.gui.domain;

/**
 * Represent command for youtube-dl given by specified type e.g. mp3, mp4 etc.
 */
public interface Content {
    String getCommand(String url);
}
