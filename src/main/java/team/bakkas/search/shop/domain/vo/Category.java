package team.bakkas.search.shop.domain.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Category {
    FOOD_BEVERAGE("식/음료"),
    MART("편의점/마트"),
    SERVICE("서비스업종"),
    FASHION("패션의류"),
    ACCESSORY("패션잡화"),
    ETC("그외/마켓");

    private String title;
}
