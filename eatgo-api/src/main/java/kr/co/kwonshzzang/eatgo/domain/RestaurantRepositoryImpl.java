package kr.co.kwonshzzang.eatgo.domain;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private List<Restaurant> restaurants;

    public RestaurantRepositoryImpl() {
        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
    }
    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }
}