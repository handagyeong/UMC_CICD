package spring.umc.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.umc.domain.member.entity.InquiryImage;

public interface InquiryImageRepository extends JpaRepository<InquiryImage, Long> {

}