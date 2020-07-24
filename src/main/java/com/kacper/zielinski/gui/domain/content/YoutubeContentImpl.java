package com.kacper.zielinski.gui.domain.content;

public class YoutubeContentImpl implements YoutubeContent {

    private String contentUrl;
    private YoutubeContentType contentType;

    public YoutubeContentImpl(String contentUrl, YoutubeContentType contentType) {
        this.contentUrl = contentUrl;
        this.contentType = contentType;
    }

    @Override
    public String getContentUrl() {
        return contentUrl;
    }

    @Override
    public YoutubeContentType getContentType() {
        return contentType;
    }

}
