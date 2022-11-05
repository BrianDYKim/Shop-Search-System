package team.bakkas.search.shop.domain.persist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.stereotype.Repository;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DetailCategory;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ShopAdvancedRepositoryImpl implements ShopAdvancedRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Shop> withInSearch(final GeoPoint geoPoint, final Double distance, final String unit, final Pageable pageable) {
        Criteria criteria = Criteria
                .where("location").within(geoPoint, distance.toString() + unit);

        CriteriaQuery query = CriteriaQuery.builder(criteria)
                .withSort(Sort.by(
                                new GeoDistanceOrder("location", geoPoint).withUnit(unit)
                        )
                )
                .withPageable(pageable)
                .build();

        return elasticsearchOperations.search(query, Shop.class).getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shop> searchByCategoryWithIn(final Category category, final GeoPoint geoPoint, final Double distance, String unit, final Pageable pageable) {
        Criteria criteria = Criteria
                .where("category").is(category)
                .and("location").within(geoPoint, distance.toString() + unit);

        CriteriaQuery query = CriteriaQuery.builder(criteria)
                .withSort(Sort.by(
                                new GeoDistanceOrder("location", geoPoint).withUnit(unit)
                        )
                )
                .withPageable(pageable)
                .build();

        return elasticsearchOperations.search(query, Shop.class).getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shop> searchByDetailCategoryWithIn(final DetailCategory detailCategory, final GeoPoint geoPoint, final Double distance, String unit, final Pageable pageable) {
        Criteria criteria = Criteria
                .where("detail_category").is(detailCategory)
                .and("location").within(geoPoint, distance.toString() + unit);

        CriteriaQuery query = CriteriaQuery.builder(criteria)
                .withSort(Sort.by(
                                new GeoDistanceOrder("location", geoPoint).withUnit(unit)
                        )
                )
                .withPageable(pageable)
                .build();

        return elasticsearchOperations.search(query, Shop.class).getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Shop> searchByShopNameWithIn(final String shopName, final GeoPoint geoPoint, final Double distance, final String unit, final Pageable pageable) {
        Criteria criteria = Criteria
                .where("shop_name").is(shopName)
                .and("location").within(geoPoint, distance.toString() + unit);

        CriteriaQuery query = CriteriaQuery.builder(criteria)
                .withSort(Sort.by(
                                new GeoDistanceOrder("location", geoPoint).withUnit(unit)
                        )
                )
                .withPageable(pageable)
                .build();

        return elasticsearchOperations.search(query, Shop.class).getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}