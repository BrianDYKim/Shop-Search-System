package team.bakkas.search.shop.domain.persist;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends ElasticsearchRepository<Shop, String>, ShopAdvancedRepository {

    List<Shop> findAllByShopName(String shopName);
}