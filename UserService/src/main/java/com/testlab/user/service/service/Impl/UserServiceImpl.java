package com.testlab.user.service.service.Impl;

import com.testlab.user.service.Repo.UserRepo;
import com.testlab.user.service.entity.Hotel;
import com.testlab.user.service.entity.Rating;
import com.testlab.user.service.entity.Users;
import com.testlab.user.service.exceptions.ResourceNotFoundException;
import com.testlab.user.service.external.services.HotelService;
import com.testlab.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Users saveUser(Users user) {
        String randomUserId =  UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<Users> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public Users getUser(String userId) {
        Users user= userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server.."+ userId));

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);

        List<Rating> ratingsList = Arrays.stream(ratingsOfUser).toList();

        ratingsList.stream().map(rating -> {
//            System.out.println(rating.getHotelId());
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());    //using feign client

//            logger.info("Response status code: "+ forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        return user;
    }
}
