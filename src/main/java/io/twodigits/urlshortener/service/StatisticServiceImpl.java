package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.controller.StatisticDTO;
import io.twodigits.urlshortener.model.ShortURLCallHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatisticServiceImpl implements StatisticService {

    private final ShortURLCallHistoryService shortURLCallHistoryService;

    public StatisticServiceImpl(ShortURLCallHistoryService shortURLCallHistoryService) {
        this.shortURLCallHistoryService = shortURLCallHistoryService;
    }

    /**
     * Get the statistic for shortened URL.
     *
     * @param shortLink
     * @return statistic
     */
    @Override
    public StatisticDTO getStatistic(String shortLink) {
        List<ShortURLCallHistory> shortURLCallHistoryList =
                shortURLCallHistoryService.findByShortLink(shortLink);
        StatisticDTO statisticDTO = new StatisticDTO(shortLink, shortURLCallHistoryList.size(), shortURLCallHistoryList);
        return statisticDTO;
    }
}
