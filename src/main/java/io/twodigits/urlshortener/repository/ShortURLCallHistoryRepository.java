package io.twodigits.urlshortener.repository;

import io.twodigits.urlshortener.model.ShortURLCallHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortURLCallHistoryRepository extends CrudRepository<ShortURLCallHistory, Long> {

    List<ShortURLCallHistory> findByShortLink(String shortLink);
}
