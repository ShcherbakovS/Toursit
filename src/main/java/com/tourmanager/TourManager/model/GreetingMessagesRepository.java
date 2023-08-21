package com.tourmanager.TourManager.model;

import com.tourmanager.TourManager.entity.GreetingMessages;
import org.springframework.data.repository.CrudRepository;

public interface GreetingMessagesRepository extends CrudRepository <GreetingMessages, Long> {

}
