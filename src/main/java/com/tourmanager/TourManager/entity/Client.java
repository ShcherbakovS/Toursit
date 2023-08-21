package com.tourmanager.TourManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity(name = "clients")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;
    @Column(name = "budget")
    String budget;
    @Column(name = "number_of_people")
    String numberOfPeople;

}



