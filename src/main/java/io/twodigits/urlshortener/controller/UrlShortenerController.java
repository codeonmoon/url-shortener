package io.twodigits.urlshortener.controller;

import io.twodigits.urlshortener.model.ShortURLCallHistory;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.service.ShortURLCallHistoryService;
import io.twodigits.urlshortener.service.StatisticService;
import io.twodigits.urlshortener.service.URLShortenerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    private static final Logger log = LoggerFactory.getLogger(UrlShortenerController.class);

    private final URLShortenerService urlShortenerService;

    private final StatisticService statisticService;

    private final ShortURLCallHistoryService shortURLCallHistoryService;

    public UrlShortenerController(URLShortenerService urlShortenerService,
                                  StatisticService statisticService,
                                  ShortURLCallHistoryService shortURLCallHistoryService) {
        this.urlShortenerService = urlShortenerService;
        this.statisticService = statisticService;
        this.shortURLCallHistoryService = shortURLCallHistoryService;
    }


    @GetMapping("/urls/{user}")
    public Iterable<URL> getUrlByUser(@PathVariable String user) {
        return urlShortenerService.listURLs(user);
    }

    @GetMapping("/urls/{user}/{id}")
    public Optional<URL> getUrlByIdAndUser(@PathVariable String user, @PathVariable String id) {
        return urlShortenerService.getURL(user, id);
    }

    @GetMapping("/urlsById/{id}")
    public Optional<URL> getUrlById(@PathVariable String id) {
        return urlShortenerService.getURL(id);
    }

    @PostMapping(path = "/addURL",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ResponseEntity addUrl(@RequestBody URL url) {
        try {
            url = urlShortenerService.addURL(url.getUser(), url.getUrl());
            return new ResponseEntity<>(url, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String id,
                                                   HttpServletResponse response,
                                                   HttpServletRequest httpServletRequest) throws IOException {
        if (StringUtils.isEmpty(id)) {
            UrlErrorResponseDTO urlErrorResponseDTO = new UrlErrorResponseDTO();
            urlErrorResponseDTO.setError("Invalid URL");
            urlErrorResponseDTO.setStatus("400");
            return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);
        }
        Optional<URL> urlToReturn = urlShortenerService.getEncodedUrl(id);
        if (!urlToReturn.isPresent()) {
            UrlErrorResponseDTO urlErrorResponseDTO = new UrlErrorResponseDTO();
            urlErrorResponseDTO.setError("Url doesn't exists!");
            urlErrorResponseDTO.setStatus("404");
            return new ResponseEntity<UrlErrorResponseDTO>(urlErrorResponseDTO, HttpStatus.OK);
        }
        String referrer = httpServletRequest.getHeader("Referer");
        response.sendRedirect(urlToReturn.get().getUrl());

        ShortURLCallHistory shortURLCallHistory = new ShortURLCallHistory();
        String userAgent = httpServletRequest.getHeader("user-agent");
        shortURLCallHistory.setShortLink(id);
        shortURLCallHistory.setUserAgent(userAgent);
        shortURLCallHistory.setReferrer(referrer);
        shortURLCallHistory.setCallDate(LocalDateTime.now());
        shortURLCallHistoryService.saveCall(shortURLCallHistory);
        return null;
    }

    @GetMapping("/statistic/{shortLink}")
    public StatisticDTO getStatistic(@PathVariable String shortLink){
        return statisticService.getStatistic(shortLink);
    }


    // As soon as shortLink is called don't increment the counter.

    @DeleteMapping("/deleteUrl/{user}/{id}")
    public void delete(@PathVariable String user, @PathVariable String id) {
        urlShortenerService.deleteURL(user, id);
    }
}
