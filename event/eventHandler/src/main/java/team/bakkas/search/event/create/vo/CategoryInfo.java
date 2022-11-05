package team.bakkas.search.event.create.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DetailCategory;

import java.io.Serializable;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryInfo implements Serializable {
    private Category shopCategory;

    private DetailCategory shopDetailCategory;
}
