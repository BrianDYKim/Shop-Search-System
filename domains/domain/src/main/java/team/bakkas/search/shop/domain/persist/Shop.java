package team.bakkas.search.shop.domain.persist;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DeliveryTipPerDistance;
import team.bakkas.search.shop.domain.vo.DetailCategory;
import team.bakkas.search.shop.domain.vo.Status;

import java.util.List;

@Document(indexName = "shops")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Shop {
    @Id
    private String shopId;

    @Field(type = FieldType.Text, name = "shop_name")
    private String shopName;

    @Field(type = FieldType.Keyword, name = "status")
    private Status status; // 해당 가게의 상태 정보 (오픈, 닫힘, etc...)

    @GeoPointField
    private GeoPoint location; // 해당 가게의 위도 경도 정보

    @Field(type = FieldType.Nested, name = "delivery_tip_per_distance_list")
    private List<DeliveryTipPerDistance> deliveryTipPerDistanceList;

    @Field(type = FieldType.Keyword, name = "category")
    private Category category;

    @Field(type = FieldType.Keyword, name = "detail_category")
    private DetailCategory detailCategory;

    @Field(type = FieldType.Double, name = "average_score")
    private Double averageScore; // 평점의 평균 값

    @Field(type = FieldType.Keyword, name = "business_number")
    private String businessNumber; // 사업자 등록번호
}
