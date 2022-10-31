package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.MenuItemService;
import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("{restaurantId}/menuitems")
    public List<MenuItem> list(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = menuItemService.getMenuItems(restaurantId);
        return menuItems;
    }

    @PatchMapping("/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable long restaurantId,  @RequestBody List<MenuItem> menuItems) {
         menuItemService.bulkUpdate(restaurantId, menuItems);
         return "";
    }
}
