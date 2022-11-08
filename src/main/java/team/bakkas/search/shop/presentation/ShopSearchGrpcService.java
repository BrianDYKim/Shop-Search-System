package team.bakkas.search.shop.presentation;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import team.bakkas.search.shop.application.ShopService;
import team.bakkas.search.shop.domain.dto.request.CategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.DetailCategoryWithInSearchRequest;
import team.bakkas.search.shop.domain.dto.request.SearchWithInRequest;
import team.bakkas.search.shop.domain.dto.request.ShopNameWithInSearchRequest;
import team.bakkas.search.shop.domain.vo.Category;
import team.bakkas.search.shop.domain.vo.DetailCategory;
import team.bakkas.shop.search.*;

import java.util.List;

@RequiredArgsConstructor
public class ShopSearchGrpcService extends ShopSearchGrpc.ShopSearchImplBase {

    private final ShopService shopService;

    @Override
    public void searchWithIn(SearchWithInGrpcRequest request, StreamObserver<SearchResponse> responseObserver) {
        SearchWithInRequest searchRequest = toSearchWithInRequest(request);

        List<String> searchResults = shopService.searchWithIn(searchRequest);

        SearchResponse grpcResponse = SearchResponse.newBuilder()
                .addAllIdList(searchResults)
                .build();

        responseObserver.onNext(grpcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void searchShopNameWithIn(SearchShopNameGrpcRequest request, StreamObserver<SearchResponse> responseObserver) {
        ShopNameWithInSearchRequest searchRequest = toShopNameWithInRequest(request);

        List<String> searchResults = shopService.searchByShopNameWithIn(searchRequest);

        SearchResponse grpcResponse = SearchResponse.newBuilder()
                .addAllIdList(searchResults)
                .build();

        responseObserver.onNext(grpcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void searchCategoryWithIn(SearchCategoryGrpcRequest request, StreamObserver<SearchResponse> responseObserver) {
        CategoryWithInSearchRequest searchRequest = toCategoryWithInSearchRequest(request);

        List<String> searchResults = shopService.searchByCategoryWithIn(searchRequest);

        SearchResponse grpcResponse = SearchResponse.newBuilder()
                .addAllIdList(searchResults)
                .build();

        responseObserver.onNext(grpcResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void searchDetailCategoryWithIn(SearchDetailCategoryGrpcRequest request, StreamObserver<SearchResponse> responseObserver) {
        DetailCategoryWithInSearchRequest searchRequest = toDetailCategoryWithInSearchRequest(request);

        List<String> searchResults = shopService.searchByDetailCategoryWithIn(searchRequest);

        SearchResponse grpcResponse = SearchResponse.newBuilder()
                .addAllIdList(searchResults)
                .build();

        responseObserver.onNext(grpcResponse);
        responseObserver.onCompleted();
    }

    private SearchWithInRequest toSearchWithInRequest(SearchWithInGrpcRequest request) {
        return SearchWithInRequest.of(request.getLatitude(), request.getLongitude(), request.getDistance(),
                request.getUnit(), request.getPage(), request.getSize());
    }

    private ShopNameWithInSearchRequest toShopNameWithInRequest(SearchShopNameGrpcRequest request) {
        return ShopNameWithInSearchRequest.of(request.getShopName(), request.getLatitude(), request.getLongitude(),
                request.getDistance(), request.getUnit(), request.getPage(), request.getSize());
    }

    private CategoryWithInSearchRequest toCategoryWithInSearchRequest(SearchCategoryGrpcRequest request) {
        Category category = Category.valueOf(request.getCategory());

        return CategoryWithInSearchRequest.of(category, request.getLatitude(), request.getLongitude(),
                request.getDistance(), request.getUnit(), request.getPage(), request.getSize());
    }

    private DetailCategoryWithInSearchRequest toDetailCategoryWithInSearchRequest(SearchDetailCategoryGrpcRequest request) {
        DetailCategory detailCategory = DetailCategory.valueOf(request.getDetailCategory());

        return DetailCategoryWithInSearchRequest.of(detailCategory, request.getLatitude(), request.getLongitude(),
                request.getDistance(), request.getUnit(), request.getPage(), request.getSize());
    }
}
