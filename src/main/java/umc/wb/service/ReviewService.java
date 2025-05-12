package umc.wb.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.wb.domain.Member;
import umc.wb.domain.Restaurant;
import umc.wb.domain.Review;
import umc.wb.mapper.ReviewMapper;
import umc.wb.repository.ReviewRepository;
import umc.wb.web.dto.ReviewRequest;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review createReview(ReviewRequest.CreateReviewRequest request, Member member, Restaurant restaurant) {
        Review review = ReviewMapper.toReview(request, member, restaurant);
        return reviewRepository.save(review);
    }
}
