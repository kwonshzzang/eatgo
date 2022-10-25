package kr.co.kwonshzzang.eatgo.domain;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    public List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
