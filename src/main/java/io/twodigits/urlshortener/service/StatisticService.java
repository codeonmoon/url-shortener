package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.controller.StatisticDTO;

public interface StatisticService {

    /**
     * Get the statistic for shortened URL.
     *
     * @return statistic
     * @param shortLink
     */
    StatisticDTO getStatistic(String shortLink);
}
