package team.bakkas.search.shop.domain.persist;

import org.elasticsearch.client.license.LicensesStatus;
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
        String shopId = UUID.randomUUID().toString();
        String shopName = "가짜 가게";
        List<DeliveryTipPerDistance> deliveryTipPerDistanceList = List.of(
                DeliveryTipPerDistance.of(10.0, 1500),
                DeliveryTipPerDistance.of(20.0, 2500)
        );
        Shop targetShop = Shop.builder()
                .shopId(shopId)
                .shopName(shopName)
                .status(Status.CLOSE)
                .location(GeoPoint.fromPoint(new Point(128.0, 36.0)))
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


}