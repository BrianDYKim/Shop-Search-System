package team.bakkas.search.shop.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.bakkas.search.shop.application.ShopService;
import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.WithInSearchRequest;

import java.util.List;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/07
 */
@RestController
@RequestMapping("/api/v1/shop/search")
@RequiredArgsConstructor
public class ShopSearchController {
    private final ShopService shopService;

    @GetMapping("")
    public ResponseEntity<List<String>> searchWithIn(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam Double distance,
                                                     @RequestParam String unit, @RequestParam Integer page, @RequestParam Integer size
    ) {

        WithInSearchRequest request = WithInSearchRequest.of(latitude, longitude, distance, unit, page, size);

        return ResponseEntity.status(HttpStatus.OK)
                .body(shopService.searchWithIn(request));
    }

    @PostMapping("/category")
    public ResponseEntity<List<String>> searchByCategoryWithIn(@RequestBody CategoryWithInSearchRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(shopService.searchByCategoryWithIn(request));
    }

    @PostMapping("/detail-category")
    public ResponseEntity<List<String>> searchByDetailCategoryWithIn(@RequestBody DetailCategoryWithInSearchRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(shopService.searchByDetailCategoryWithIn(request));
    }

    @PostMapping("/name")
    public ResponseEntity<List<String>> searchByShopNameWithIn(@RequestBody ShopNameWithInSearchRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(shopService.searchByShopNameWithIn(request));
    }
}
