package team.bakkas.search.shop.domain.persist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
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

    // 영남대학교 기준 위치
    private final GeoPoint yeoungnamUniversityLocation = new GeoPoint(35.8331655, 128.7580143);

    @Test
    @DisplayName("Create Test")
    void createTest() {
        // given
        // lat: 35.8395559, lon: 128.7073095
        String shopId = UUID.randomUUID().toString();
        String shopName = "버거킹 경산사동점";
        List<DeliveryTipPerDistance> deliveryTipPerDistanceList = List.of(
                DeliveryTipPerDistance.of(10.0, 1500),
                DeliveryTipPerDistance.of(20.0, 2500)
        );
        Shop targetShop = Shop.builder()
                .shopId(shopId)
                .shopName(shopName)
                .businessNumber("3333-33333-33333")
                .status(Status.CLOSE)
                .location(new GeoPoint(35.8112231, 128.7538589))
                .category(Category.FOOD_BEVERAGE)
                .detailCategory(DetailCategory.FAST_FOOD)
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
        Double radius = 20.0;
        String unit = "km";

        // Pagination
        PageRequest pageRequest = PageRequest.of(0, 100);

        // when
        List<Shop> shops = shopRepository.withInSearch(geoPoint, radius, unit, pageRequest);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }

    @Test
    @DisplayName("카테고리 10km 반경검색 테스트")
    void searchCategoryTest() {
        // given
        Category category = Category.FOOD_BEVERAGE;
        Double distance = 10.0;
        String unit = "km";

        PageRequest pageRequest = PageRequest.of(0, 100);

        // when
        List<Shop> shops = shopRepository.searchByCategoryWithIn(Category.FOOD_BEVERAGE, yeoungnamUniversityLocation, distance, unit, pageRequest);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }

    @Test
    @DisplayName("세부 카테고리 10km 반경 검색 테스트")
    void searchDetailCategoryTest() {
        // given
        DetailCategory detailCategory = DetailCategory.CAFE_BREAD;
        Double distance = 10.0;
        String unit = "km";

        PageRequest pageRequest = PageRequest.of(0, 100);

        // when
        List<Shop> shops = shopRepository.searchByDetailCategoryWithIn(detailCategory, yeoungnamUniversityLocation, distance, unit, pageRequest);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }

    @Test
    @DisplayName("shopName 10km 반경 검색 테스트")
    void searchByShopNameWithInTest() {
        // given
        String shopName = "버거킹";
        Double distance = 10.0;
        String unit = "km";

        PageRequest pageRequest = PageRequest.of(0, 100);

        // when
        List<Shop> shops = shopRepository.searchByShopNameWithIn(shopName, yeoungnamUniversityLocation, distance, unit, pageRequest);

        // then
        shops.forEach(shop -> System.out.println(shop.getShopName()));
    }
}