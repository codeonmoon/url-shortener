package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.model.ShortURLCallHistory;

import java.util.List;

public class StatisticDTO {

    private String shortLink;
    private int noOfCalls;
    private List<ShortURLCallHistory> urlCallHistoryList;


    public StatisticDTO() {
    }

    public StatisticDTO(String shortLink, int noOfCalls, List<ShortURLCallHistory> urlCallHistoryList) {
        this.shortLink = shortLink;
        this.noOfCalls = noOfCalls;
        this.urlCallHistoryList = urlCallHistoryList;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public int getNoOfCalls() {
        return noOfCalls;
    }

    public void setNoOfCalls(int noOfCalls) {
        this.noOfCalls = noOfCalls;
    }

    public List<ShortURLCallHistory> getUrlCallHistoryList() {
        return urlCallHistoryList;
    }

    public void setUrlCallHistoryList(List<ShortURLCallHistory> urlCallHistoryList) {
        this.urlCallHistoryList = urlCallHistoryList;
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
                "shortLink='" + shortLink + '\'' +
                ", noOfCalls=" + noOfCalls +
                ", urlCallHistoryList=" + urlCallHistoryList +
                '}';
    }
}
