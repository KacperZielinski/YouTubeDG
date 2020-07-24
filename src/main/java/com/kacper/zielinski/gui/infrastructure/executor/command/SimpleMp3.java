package com.kacper.zielinski.gui.infrastructure.executor.command;

public class SimpleMp3 implements YoutubeDlCommand {

    @Override
    public String getCommand(String url) {
        return "--newline -i -o \"C:\\Users\\Kacper\\Documents\\YouTubeDG\\%(title)s.%(ext)s\" -x --audio-format mp3 --ignore-config --hls-prefer-native --audio-quality 0 " + url;
    }
}
