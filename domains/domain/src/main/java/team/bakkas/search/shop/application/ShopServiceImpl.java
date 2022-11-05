package team.bakkas.search.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.WithinSearchRequest;
import team.bakkas.search.shop.domain.persist.ShopRepository;

import java.util.List;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/05
 */
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    @Override
    public List<String> withInSearch(WithinSearchRequest request) {
        return null;
    }

    @Override
    public List<String> searchByCategoryWithIn(CategoryWithInSearchRequest request) {
        return null;
    }

    @Override
    public List<String> searchByDetailCategoryWithIn(DetailCategoryWithInSearchRequest request) {
        return null;
    }

    @Override
    public List<String> searchByShopNameWithIn(ShopNameWithInSearchRequest request) {
        return null;
    }
}
