package thesis.restaurantservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.restaurantservice.entities.Restaurant;
import thesis.restaurantservice.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;


    @PostMapping("")
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant){

        return  ResponseEntity.ok(restaurantService.saveRestaurant(restaurant));
    }

    @GetMapping("")
    public ResponseEntity<List<Restaurant>> getRestaurants(){

        return ResponseEntity.ok(restaurantService.getRestaurants());
    }

    @GetMapping("{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable String id){

        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name){

        return ResponseEntity.ok(restaurantService.getRestaurantByName(name));
    }

    @PutMapping("")
    public ResponseEntity<Restaurant> updateRestaurantInfo(@RequestBody Restaurant restaurant){

        return ResponseEntity.ok(restaurantService.updateRestaurantInfo(restaurant));
    }
}
