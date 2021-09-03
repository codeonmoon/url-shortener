package io.twodigits.urlshortener.service;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.URLRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class URLShortenerServiceImpl implements URLShortenerService {

    @Autowired
    private URLRepository repository;

    @Override
    public Iterable<URL> listURLs(String user) {
        return repository.findByUser(user);
    }

    @Override
    public URL addURL(String user, String url) {
        // TODO: Implement
        if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(user)) {
            String encodedUrl = encodeUrl(url);
            URL urlToReturn = new URL(encodedUrl, url, user);
            repository.save(urlToReturn);
            if (urlToReturn != null) {
                return urlToReturn;
            }
            return null;
        }
        return null;
    }

    private String encodeUrl(String url) {
        url = RandomStringUtils.randomAlphanumeric(8); // Can be set as environment variable
        return url;
    }

    @Override
    public Optional<URL> getURL(String user, String id) {
        return repository.findByIdAndUser(id, user);
    }

    @Override
    public Optional<URL> getURL(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteURL(String user, String id) {
        Optional<URL> url = repository.findByIdAndUser(id, user);
        if (url.isPresent()) {
            repository.delete(url.get());
        }
    }

    @Override
    public Optional<URL> getEncodedUrl(String url) {
        Optional<URL> urlToReturn = repository.findById(url);
        return urlToReturn;
    }
}
