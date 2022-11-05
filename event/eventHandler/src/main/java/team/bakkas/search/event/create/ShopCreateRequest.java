package team.bakkas.search.event.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import team.bakkas.search.event.create.vo.*;
import team.bakkas.search.shop.domain.persist.Shop;
import team.bakkas.search.shop.domain.vo.DeliveryTipPerDistance;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopCreateRequest implements Serializable {
    private String shopId;

    private String shopName;

    private String businessNumber;

    private SalesInfo salesInfo;

    private AddressInfo addressInfo;

    private LatLon latLon;

    private ShopImageInfo shopImageInfo;

    private BranchInfo branchInfo;

    private CategoryInfo categoryInfo;

    private List<DeliveryTipPerDistance> deliveryTipPerDistanceList;

    private Double totalScore;

    private Integer reviewNumber;

    private String shopDescription;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    public Shop toEntity() {
        return Shop.builder()
                .shopId(shopId)
                .shopName(shopName)
                .status(salesInfo.getStatus())
                .location(new GeoPoint(latLon.getLatitude(), latLon.getLongitude()))
                .deliveryTipPerDistanceList(deliveryTipPerDistanceList)
                .category(categoryInfo.getShopCategory())
                .detailCategory(categoryInfo.getShopDetailCategory())
                .averageScore(totalScore / reviewNumber)
                .businessNumber(businessNumber)
                .build();
    }
}
