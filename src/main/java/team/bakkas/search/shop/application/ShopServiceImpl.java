package team.bakkas.search.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.SearchWithInRequest;
import team.bakkas.search.shop.domain.persist.Shop;
import team.bakkas.search.shop.domain.persist.ShopRepository;
import team.bakkas.search.shop.domain.vo.Category;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> searchWithIn(SearchWithInRequest request) {
        GeoPoint geoPoint = new GeoPoint(request.getLatitude(), request.getLongitude());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        return shopRepository.withInSearch(geoPoint, request.getDistance(), request.getUnit(), pageRequest)
                .stream()
                .map(Shop::getShopId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchByCategoryWithIn(CategoryWithInSearchRequest request) {
        GeoPoint geoPoint = new GeoPoint(request.getLatitude(), request.getLongitude());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        return shopRepository.searchByCategoryWithIn(request.getCategory(), geoPoint, request.getDistance(), request.getUnit(), pageRequest)
                .stream()
                .map(Shop::getShopId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchByDetailCategoryWithIn(DetailCategoryWithInSearchRequest request) {
        GeoPoint geoPoint = new GeoPoint(request.getLatitude(), request.getLongitude());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        return shopRepository.searchByDetailCategoryWithIn(request.getDetailCategory(), geoPoint, request.getDistance(), request.getUnit(), pageRequest)
                .stream()
                .map(Shop::getShopId)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchByShopNameWithIn(ShopNameWithInSearchRequest request) {
        GeoPoint geoPoint = new GeoPoint(request.getLatitude(), request.getLongitude());
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        return shopRepository.searchByShopNameWithIn(request.getShopName(), geoPoint, request.getDistance(), request.getUnit(), pageRequest)
                .stream()
                .map(Shop::getShopId)
                .collect(Collectors.toList());
    }
}
