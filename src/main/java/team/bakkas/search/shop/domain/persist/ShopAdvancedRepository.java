package team.bakkas.search.shop.domain.persist;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DetailCategory;

import java.util.List;

// 기본 Repository에서 지원하지 않는 기능들을 추가적으로 정의하는 repository
public interface ShopAdvancedRepository {

    /**
     * @param geoPoint 기준점
     * @param distance 반경
     * @param unit     거리 단위
     * @param pageable pageable
     * @return List of shop
     */
    List<Shop> withInSearch(GeoPoint geoPoint, Double distance, String unit, Pageable pageable);

    /**
     * @param category category of shop
     * @param geoPoint user's location
     * @param distance radius from geoPoint
     * @param unit     distance unit
     * @param pageable pageable
     * @return List of shop satisfying the conditions
     */
    List<Shop> searchByCategoryWithIn(Category category, GeoPoint geoPoint, Double distance, String unit, Pageable pageable);

    /**
     * @param detailCategory detailCategory of shop
     * @param geoPoint       user's location
     * @param distance       radius from geoPoint
     * @param unit           distance unit
     * @param pageable       pageable
     * @return List of shop satisfying the conditions
     */
    List<Shop> searchByDetailCategoryWithIn(DetailCategory detailCategory, GeoPoint geoPoint, Double distance, String unit, Pageable pageable);

    /**
     * @param shopName shopName you wanna search
     * @param geoPoint user's location
     * @param distance radius
     * @param unit     distance unit
     * @param pageable pageable
     * @return list of shops what you wanna search
     */
    List<Shop> searchByShopNameWithIn(String shopName, GeoPoint geoPoint, Double distance, String unit, Pageable pageable);
}
