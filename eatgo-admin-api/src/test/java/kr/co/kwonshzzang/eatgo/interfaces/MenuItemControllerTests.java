package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.MenuItemService;
import kr.co.kwonshzzang.eatgo.domain.MenuItem;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MenuItemControllerTests {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext was;

    @MockBean
    private MenuItemService menuItemService;

    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
                .webAppContextSetup(was)
                .alwaysDo(print())
                .build();
    }

    @Test
    void list() throws Exception {
        given(menuItemService.getMenuItems(1L))
                .willReturn(Lists.newArrayList(MenuItem.builder().name("Kimchi").build()));

        mvc.perform(MockMvcRequestBuilders.get("/restaurants/1/menuitems"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Kimchi")));
    }

    @Test
    void bulkUpdate() throws Exception {
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/12/menuitems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]"))
                .andExpect(status().isOk());

        verify(menuItemService).bulkUpdate(eq(12L), any());
    }

}