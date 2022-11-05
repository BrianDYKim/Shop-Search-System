package team.bakkas.search.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import team.bakkas.search.config.KafkaConsumerGroups;
import team.bakkas.search.config.KafkaTopics;
import team.bakkas.search.event.create.ShopCreateRequest;
import team.bakkas.search.shop.domain.persist.Shop;
import team.bakkas.search.shop.domain.persist.ShopRepository;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/06
 */
@Component
@RequiredArgsConstructor
public class ShopEventHandler {
    private final ShopRepository shopRepository;

    @KafkaListener(topics = KafkaTopics.SHOP_CREATE_TOPIC, groupId = KafkaConsumerGroups.SHOP_SEARCH_CONSUMER_GROUP)
    public void createShop(ShopCreateRequest request) {
        Shop requestedShop = request.toEntity();

        shopRepository.save(requestedShop);
    }
}
