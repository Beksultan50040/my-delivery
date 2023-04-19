package thesis.restaurantservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import thesis.restaurantservice.entities.Restaurant;
import thesis.restaurantservice.exceptions.ApiError;
import thesis.restaurantservice.repositories.RestaurantRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {


    @Autowired
    private RestaurantRepo restaurantRepo;


    public Restaurant saveRestaurant(Restaurant restaurant){



        return restaurantRepo.save(restaurant);

    }

    public List<Restaurant> getRestaurants(){

        return restaurantRepo.findAll();

    }

    public Restaurant getRestaurantById(String id){

        return restaurantRepo.findById(Long.valueOf(id)).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND,
                "No restaurant with id " + id, new ArrayList<>()));
    }

    public Restaurant getRestaurantByName(String name){

        return restaurantRepo.findByName(name).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND,
                "No restaurant with name " + name, new ArrayList<>()));
    }

    public Restaurant updateRestaurantInfo(Restaurant restaurant){

        Restaurant existingRestaurant = restaurantRepo.findById(restaurant.getId())
                .orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND,
                        "No restaurant with id " + restaurant.getId(), new ArrayList<>()));

        return restaurantRepo.saveAndFlush(restaurant);
    }
}
