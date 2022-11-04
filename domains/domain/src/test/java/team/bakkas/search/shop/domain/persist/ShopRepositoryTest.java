package team.bakkas.search.shop.domain.persist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.geo.Point;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DeliveryTipPerDistance;
import team.bakkas.search.shop.domain.vo.DetailCategory;
import team.bakkas.search.shop.domain.vo.Status;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopRepositoryTest {
    @Autowired
    private ShopRepository shopRepository;

    @Test
    @DisplayName("Create Test")
    void createTest() {
        // given
        // lat: 35.8395559, lon: 128.7073095
        String shopId = UUID.randomUUID().toString();
        String shopName = "버거킹 대구시지점";
        List<DeliveryTipPerDistance> deliveryTipPerDistanceList = List.of(
                DeliveryTipPerDistance.of(10.0, 1500),
                DeliveryTipPerDistance.of(20.0, 2500)
        );
        Shop targetShop = Shop.builder()
                .shopId(shopId)
                .shopName(shopName)
                .status(Status.CLOSE)
                .location(GeoPoint.fromPoint(new Point(128.7073095, 35.8395559))) // longitude, latitude 순
                .category(Category.MART)
                .detailCategory(DetailCategory.SUPER_MARKET)
                .averageScore(0.0)
                .deliveryTipPerDistanceList(deliveryTipPerDistanceList)
                .build();

        // when
        Shop savedShop = shopRepository.save(targetShop);

        // then
        assertEquals(targetShop.getShopId(), savedShop.getShopId());
        assertEquals(targetShop.getShopName(), savedShop.getShopName());
    }

    @Test
    @DisplayName("모두 읽어오기")
    void readAll() {
        Iterable<Shop> allShops = shopRepository.findAll();

        allShops.forEach(shop -> System.out.println(shop.getShopName()));
    }

    @Test
    @DisplayName("영남대학교 기준으로 가까운 순으로 가져오기")
    void searchWithInTest() {
        // given
        // 영남대학교의 위도, 경도
        Double latitude = 35.8331655;
        Double longitude = 128.7580143;
        GeoPoint geoPoint = new GeoPoint(latitude, longitude);

        // 검색 반경
        Double radius = 10.0;
        String unit = "km";

        // when
        List<Shop> shops = shopRepository.withInSearch(geoPoint, radius, unit);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }


}