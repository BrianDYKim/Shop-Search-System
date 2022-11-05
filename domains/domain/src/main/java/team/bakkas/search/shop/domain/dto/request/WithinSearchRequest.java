package team.bakkas.search.shop.domain.dto.request;

import lombok.*;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class WithinSearchRequest {
    private Double latitude;

    private Double longitude;

    private Double distance;

    private String unit;

    private Integer page;

    private Integer size;
}
