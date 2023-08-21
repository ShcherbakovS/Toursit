package com.tourmanager.TourManager.model;

import com.tourmanager.TourManager.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findClientByChatId(Long chatID);
    @Transactional
    void deleteAllByChatId(long chatID);
}
