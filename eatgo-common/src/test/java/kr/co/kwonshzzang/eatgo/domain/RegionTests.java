package kr.co.kwonshzzang.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTests {
    @Test
    void create() {
        Region region = Region.builder().name("서울").build();

        assertEquals(region.getName(), "서울");
    }

}