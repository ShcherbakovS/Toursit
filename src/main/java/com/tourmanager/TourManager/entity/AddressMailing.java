package com.tourmanager.TourManager.entity;

import lombok.Data;

import java.util.PrimitiveIterator;
@Data
public class AddressMailing {
    private String text;
    private String direction;
    private byte[] imageBytes;

}
