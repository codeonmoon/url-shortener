package io.twodigits.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ShortURLCallHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userAgent;
    private String referrer;
    private LocalDateTime callDate;
    private String shortLink;

    public ShortURLCallHistory() {
    }

    public ShortURLCallHistory(Long id, String userAgent, String referrer, LocalDateTime callDate, String shortLink) {
        this.id = id;
        this.userAgent = userAgent;
        this.referrer = referrer;
        this.callDate = callDate;
        this.shortLink = shortLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public LocalDateTime getCallDate() {
        return callDate;
    }

    public void setCallDate(LocalDateTime callDate) {
        this.callDate = callDate;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    @Override
    public String toString() {
        return "ShortURLCallHistory{" +
                "id=" + id +
                ", userAgent='" + userAgent + '\'' +
                ", referrer='" + referrer + '\'' +
                ", callDate=" + callDate +
                ", shortLink='" + shortLink + '\'' +
                '}';
    }
}
