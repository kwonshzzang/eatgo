package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;

    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        for(MenuItem menuItem: menuItems) {
            if(menuItem.isDestory()) {
                menuItemRepository.deleteById(menuItem.getId());
            } else {
                menuItem.setRestaurantId(restaurantId);
                menuItemRepository.save(menuItem);
            }
        }
    }

    public List<MenuItem> getMenuItems(long resturantId) {
        return menuItemRepository.findAllByRestaurantId(resturantId);
    }
}
