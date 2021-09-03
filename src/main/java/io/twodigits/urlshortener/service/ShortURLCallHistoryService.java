package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.ShortURLCallHistory;

import java.util.List;

public interface ShortURLCallHistoryService {

    ShortURLCallHistory saveCall(ShortURLCallHistory shortURLCallHistory);

    List<ShortURLCallHistory> findByShortLink(String shortLink);
}
