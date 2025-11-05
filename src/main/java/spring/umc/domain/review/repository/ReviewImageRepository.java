package spring.umc.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.review.entity.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

}