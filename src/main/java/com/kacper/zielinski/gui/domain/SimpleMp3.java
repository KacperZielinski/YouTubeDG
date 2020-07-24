package com.kacper.zielinski.gui.domain;

public class SimpleMp3 implements Content {

    @Override
    public String getCommand(String url) {
        return "youtube-dl.exe --newline -i -o \"C:\\Users\\Kacper\\Documents\\YouTubeDG\\%(title)s.%(ext)s\" -x --audio-format mp3 --ignore-config --hls-prefer-native --audio-quality 0 " + url;
    }
}
