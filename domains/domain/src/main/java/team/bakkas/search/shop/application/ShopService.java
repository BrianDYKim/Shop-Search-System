package team.bakkas.search.shop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.bakkas.search.shop.domain.persist.ShopRepository;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;


}
