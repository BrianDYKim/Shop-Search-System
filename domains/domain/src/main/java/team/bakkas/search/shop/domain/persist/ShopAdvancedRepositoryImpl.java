package team.bakkas.search.shop.domain.persist;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ShopAdvancedRepositoryImpl implements ShopAdvancedRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<Shop> withInSearch(final GeoPoint geoPoint, final Double distance, final String unit) {
        Criteria criteria = Criteria
                .where("location").within(geoPoint, distance.toString() + unit);

        CriteriaQuery query = CriteriaQuery.builder(criteria)
                .withSort(Sort.by(
                                new GeoDistanceOrder("location", geoPoint).withUnit(unit)
                        )
                )
                .build();

        return elasticsearchOperations.search(query, Shop.class).getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
