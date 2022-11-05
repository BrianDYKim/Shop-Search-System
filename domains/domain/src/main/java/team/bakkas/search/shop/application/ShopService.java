package team.bakkas.search.shop.application;

import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.WithinSearchRequest;

import java.util.List;

public interface ShopService {

    List<String> withInSearch(WithinSearchRequest request);

    List<String> searchByCategoryWithIn(CategoryWithInSearchRequest request);

    List<String> searchByDetailCategoryWithIn(DetailCategoryWithInSearchRequest request);

    List<String> searchByShopNameWithIn(ShopNameWithInSearchRequest request);
}
