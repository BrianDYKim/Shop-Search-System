package team.bakkas.search.function;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.bakkas.search.shop.application.ShopService;
import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;

import java.util.List;
import java.util.function.Function;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Component
@RequiredArgsConstructor
public class SearchByCategory implements Function<CategoryWithInSearchRequest, List<String>> {
    private final ShopService shopService;

    @Override
    public List<String> apply(CategoryWithInSearchRequest request) {
        return shopService.searchByCategoryWithIn(request);
    }
}
