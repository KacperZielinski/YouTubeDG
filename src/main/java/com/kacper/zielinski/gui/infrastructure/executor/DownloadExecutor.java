package com.kacper.zielinski.gui.infrastructure.executor;

import com.kacper.zielinski.gui.domain.content.YoutubeContent;
import com.kacper.zielinski.gui.domain.content.YoutubeContentType;
import com.kacper.zielinski.gui.infrastructure.executor.command.SimpleMp3;
import com.kacper.zielinski.gui.infrastructure.executor.command.SimpleVideo;
import com.kacper.zielinski.gui.infrastructure.executor.command.YoutubeDlCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DownloadExecutor extends Thread {

    private final YoutubeContent youtubeContent;

    private Double progress = 0.0;

    public DownloadExecutor(YoutubeContent youtubeContent) {
        this.youtubeContent = youtubeContent;
    }

    @Override
    public void run() {
        dummyDownloadOfMp3(youtubeContent);
    }

    public void dummyDownloadOfMp3(YoutubeContent youtubeContent) {
        // TODO check version of youtube-dl, ffmpeg
        // TODO find youtube-dl path..
        // TODO new dialog with settings, autodetection of youtube-dl or just provide path
        String youtubeDlPath = "C:\\Users\\Kacper\\Desktop\\Software\\YouTubeDl\\";

        // TODO output folder
        // TODO user can choose title and more...
        // TODO determine user system Linux or Windows

        String outputDirectory = "-o \"C:\\Users\\Kacper\\Documents\\YouTubeDG\\%(title)s.%(ext)s\" ";
        String command = youtubeDlPath + "youtube-dl.exe " + outputDirectory + determineContentType(youtubeContent).getCommand(youtubeContent.getContentUrl());
        System.out.println(command);

        // TODO display more info about failure/success

        try {
            Process process = Runtime.getRuntime().exec(command);

            // Get input streams
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // TODO two reading threads ??
            // or TODO progressbar based on String...
            // TODO output progress on some listview...
            // Read command standard output
            String s;
            System.out.println("Standard output: ");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);

                if(s.startsWith("[download] ") && s.contains("%") && s.length() > 16) {
                    String progressStr = s.substring(11, 16);
                    System.out.println("--> Progress is 11,16:::: " + progressStr);

                    if(progressStr.contains("%")) {
                        progressStr = "100";
                    }

                    progress = Double.parseDouble(progressStr);

                    System.out.println("Parsed to: " + progress);

                    //   6.5
                    //  51.9
                    // 100.0
                    // 100%
                }

            }

//            // Read command errors
//            System.out.println("Standard error: ");
//            while ((s = stdError.readLine()) != null) {
//                System.out.println(s);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private YoutubeDlCommand determineContentType(YoutubeContent youtubeContent) {
        return YoutubeContentType.MP3.equals(youtubeContent.getContentType()) ? new SimpleMp3() : new SimpleVideo();
    }

    public Double getProgress() {
        return progress;
    }
}
