package com.testlab.hotel.service;

import com.testlab.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel create(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single hotel
    Hotel get(String id);
}
