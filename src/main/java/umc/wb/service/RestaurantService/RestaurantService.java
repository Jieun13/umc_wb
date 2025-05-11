package umc.wb.service.RestaurantService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.domain.Category;
import umc.wb.domain.Region;
import umc.wb.domain.Restaurant;
import umc.wb.mapper.RestaurantMapper;
import umc.wb.repository.RestaurantRepository.RestaurantRepository;
import umc.wb.service.CategoryService;
import umc.wb.service.RegionService;
import umc.wb.web.dto.RestaurantRequest;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryService categoryService;
    private final RegionService regionService;

    @Transactional
    public Restaurant createRestaurant(Long regionId, RestaurantRequest.CreateRequest request) {
        Region region = regionService.findById(regionId);
        Category category = categoryService.findById(request.getCategoryId());

        Restaurant restaurant = RestaurantMapper.toRestaurant(request, region, category);
        return restaurantRepository.save(restaurant);
    }
}

