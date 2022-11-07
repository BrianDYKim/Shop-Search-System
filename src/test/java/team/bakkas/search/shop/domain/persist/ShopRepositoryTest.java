package team.bakkas.search.shop.domain.persist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("영남대학교 반경 검색")
    void searchWithIn() {
        // given
        Double latitude = 35.831728870626556;
        Double longitude = 128.7585397591836;

        Double distance = 10.0;
        String unit = "km";

        PageRequest pageRequest = PageRequest.of(0, 100);

        // when
        List<Shop> shops = shopRepository.withInSearch(new GeoPoint(latitude, longitude), distance, unit, pageRequest);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }
}