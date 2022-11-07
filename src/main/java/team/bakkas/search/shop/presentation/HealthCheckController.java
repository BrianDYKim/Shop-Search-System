package team.bakkas.search.shop.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Doyeop Kim
 * @version 0.0
 * @since 2022/11/08
 */
@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("")
    public String healthCheck() {
        return "healthy!";
    }
}
