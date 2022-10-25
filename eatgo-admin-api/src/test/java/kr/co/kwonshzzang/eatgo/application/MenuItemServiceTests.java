package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import kr.co.kwonshzzang.eatgo.domain.MenuItemRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class MenuItemServiceTests {

    @Autowired
    private MenuItemService menuItemService;

    @MockBean
    private MenuItemRepository menuItemRepository;

    @Test
    void getMenuItems() {
        when(menuItemRepository.findAllByRestaurantId(1004L))
                .thenReturn(Lists.newArrayList(MenuItem.builder().name("Kimchi").build()));
        List<MenuItem> menuItems =  menuItemService.getMenuItems(1004L);

        MenuItem menuItem = menuItems.get(0);

        assertEquals(menuItem.getName(), "Kimchi");
    }

    @Test
    void bulkUpdate() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(MenuItem.builder().name("Kimchi").build());
        menuItems.add(MenuItem.builder().name("Gukbob").build());
        menuItems.add(MenuItem.builder().id(12L).name("KimBob").build());
        menuItems.add(MenuItem.builder().id(1004L).destory(true).build());

        menuItemService.bulkUpdate(1L, menuItems);

        verify(menuItemRepository, times(3)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(1004L));
    }

}