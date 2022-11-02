package kr.co.kwonshzzang.eatgo.application;

import kr.co.kwonshzzang.eatgo.domain.Region;
import kr.co.kwonshzzang.eatgo.domain.RegionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RegionServiceTests {
    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    @Test
    void list() {
        Region expected = Region.builder()
                .id(123L)
                .name("서울")
                .build();

        given(regionRepository.findAll())
                .willReturn(Lists.newArrayList(expected));

        List<Region> actuals = regionService.list();
        assertEquals(1, actuals.size());

        Region actual = actuals.get(0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }


}