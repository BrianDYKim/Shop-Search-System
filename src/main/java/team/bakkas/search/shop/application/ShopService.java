package team.bakkas.search.shop.application;

import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.SearchWithInRequest;

import java.util.List;

public interface ShopService {

    /**
     * @param request request
     * @return list of shopId
     */
    List<String> searchWithIn(SearchWithInRequest request);

    /**
     * @param request request
     * @return list of shopId
     */
    List<String> searchByCategoryWithIn(CategoryWithInSearchRequest request);

    /**
     * @param request request
     * @return list of shopId
     */
    List<String> searchByDetailCategoryWithIn(DetailCategoryWithInSearchRequest request);

    /**
     * @param request request
     * @return list of shopId
     */
    List<String> searchByShopNameWithIn(ShopNameWithInSearchRequest request);
}
