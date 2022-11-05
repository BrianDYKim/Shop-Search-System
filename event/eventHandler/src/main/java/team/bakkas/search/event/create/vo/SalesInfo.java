package team.bakkas.search.event.create.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.bakkas.search.shop.domain.vo.Status;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesInfo implements Serializable {
    private Status status;

    private LocalTime openTime;

    private LocalTime closeTime;
}
