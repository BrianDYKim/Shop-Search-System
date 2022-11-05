package team.bakkas.search.shop.domain.dto.request;

import lombok.*;
import team.bakkas.search.shop.domain.vo.Category;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryWithInSearchRequest {
    private Category category;

    private Double latitude;

    private Double longitude;

    private Double distance;

    private String unit;

    private Integer page;

    private Integer size;
}
