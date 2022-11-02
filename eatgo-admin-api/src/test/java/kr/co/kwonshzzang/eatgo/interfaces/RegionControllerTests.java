package kr.co.kwonshzzang.eatgo.interfaces;

import kr.co.kwonshzzang.eatgo.application.RegionService;
import kr.co.kwonshzzang.eatgo.domain.Region;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RegionController.class)
class RegionControllerTests {

    private MockMvc mvc;


    @Autowired
    private WebApplicationContext was;

    @MockBean
    private RegionService regionService;

    private Region expected;

    @BeforeEach
    void setup() {
        expected = Region.builder()
                .id(123L)
                .name("서울")
                .build();

        mvc = MockMvcBuilders
                .webAppContextSetup(was)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void list() throws Exception {
        given(regionService.list())
                .willReturn(Lists.newArrayList(expected));

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("서울"));
    }



}