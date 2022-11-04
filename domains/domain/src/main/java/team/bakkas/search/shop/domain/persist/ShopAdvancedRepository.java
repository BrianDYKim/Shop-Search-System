package team.bakkas.search.shop.domain.persist;

import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

// 기본 Repository에서 지원하지 않는 기능들을 추가적으로 정의하는 repository
public interface ShopAdvancedRepository {

    /** distance 이내에 있는 모든 shop을 가져오는 메소드
     * @param geoPoint 기준점
     * @param distance 반경
     * @param unit 거리 단위
     * @return List of shop
     */
    List<Shop> withInSearch(final GeoPoint geoPoint, final Double distance, final String unit);

    // TODO 특정 반경 내에서 배달팁 낮은 순으로 검색하기
}
