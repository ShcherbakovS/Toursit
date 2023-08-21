package com.tourmanager.TourManager.model;

import com.tourmanager.TourManager.entity.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CountryRepository extends CrudRepository<Country, Long> {

    List<Country> findCountriesByClientChat(Long chatID);
    List<Country> findCountriesByName(String name);
    @Transactional
    void deleteAllByClientChat(Long chatID);


}
