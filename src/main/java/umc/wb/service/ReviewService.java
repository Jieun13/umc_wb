package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;
import umc.wb.mapper.ReviewMapper;
import umc.wb.repository.RestaurantRepository.RestaurantRepository;
import umc.wb.repository.ReviewRepository;
import umc.wb.web.dto.ReviewRequest;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Review createReview(ReviewRequest.CreateReviewRequest request, Member member, Restaurant restaurant) {
        Review review = ReviewMapper.toReview(request, member, restaurant);
        return reviewRepository.save(review);
    }

    public Page<Review> getReviewList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
        return reviewRepository.findAllByRestaurant(restaurant, PageRequest.of(page, 10));
    }
}
