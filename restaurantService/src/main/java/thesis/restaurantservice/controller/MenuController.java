package thesis.restaurantservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thesis.restaurantservice.entities.Menu;
import thesis.restaurantservice.service.MenuService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant/menu")
public class MenuController {


    @Autowired
    private MenuService menuService;


    @PostMapping("")
    public ResponseEntity<Menu> saveRestaurant(@RequestBody Menu menu){

        return ResponseEntity.ok(menuService.saveMenu(menu));
    }

    @GetMapping("")
    public ResponseEntity<List<Menu>> getAllMenu(){

        return ResponseEntity.of(Optional.ofNullable(menuService.getAllMenu()));
    }

    @PutMapping("")
    public ResponseEntity<Menu> updateMenuInfo(@RequestBody Menu menu){

        return ResponseEntity.ok(menuService.updateMenu(menu));
    }
}
