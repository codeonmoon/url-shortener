package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.ShortURLCallHistory;
import io.twodigits.urlshortener.repository.ShortURLCallHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShortURLCallHistoryServiceImpl implements ShortURLCallHistoryService {

    private final ShortURLCallHistoryRepository shortURLCallHistoryRepository;

    public ShortURLCallHistoryServiceImpl(ShortURLCallHistoryRepository shortURLCallHistoryRepository) {
        this.shortURLCallHistoryRepository = shortURLCallHistoryRepository;
    }

    @Override
    public ShortURLCallHistory saveCall(ShortURLCallHistory shortURLCallHistory) {
        return shortURLCallHistoryRepository.save(shortURLCallHistory);
    }

    @Override
    public List<ShortURLCallHistory> findByShortLink(String shortLink) {
        return shortURLCallHistoryRepository.findByShortLink(shortLink);
    }
}
