package thesis.restaurantservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import thesis.restaurantservice.entities.Menu;
import thesis.restaurantservice.exceptions.ApiError;
import thesis.restaurantservice.repositories.MenuRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {


    @Autowired
    private MenuRepo menuRepo;


    public Menu saveMenu(Menu menu){

        return menuRepo.save(menu);
    }

    public List<Menu> getAllMenu(){

        return menuRepo.findAll();
    }

    public Menu updateMenu(Menu menu){

        Menu existingMenu = menuRepo.findById(menu.getId()).orElseThrow(() -> new ApiError(HttpStatus.NOT_FOUND,
                "No menu with id " + menu.getId(), new ArrayList<>()));

        return menuRepo.saveAndFlush(existingMenu);
    }



}
