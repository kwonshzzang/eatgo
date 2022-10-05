package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.MenuItemRepository;
import kr.co.kwonshzzang.eatgo.domain.Restaurant;
import kr.co.kwonshzzang.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }


    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id).get();
        List<MenuItem>  menuItems = menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant resource) {
        return restaurantRepository.save(resource);
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant resource) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        restaurant.setName(resource.getName());
        restaurant.setAddress(resource.getAddress());
        return restaurant;
    }
}
