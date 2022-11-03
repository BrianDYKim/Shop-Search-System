package team.bakkas.search.shop.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryTipPerDistance {

    @Field(type = FieldType.Double)
    private Double distance;

    @Field(type = FieldType.Integer)
    private Integer price;
}
