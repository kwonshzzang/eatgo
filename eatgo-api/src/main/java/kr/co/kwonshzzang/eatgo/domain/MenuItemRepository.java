package kr.co.kwonshzzang.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {
    public List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
