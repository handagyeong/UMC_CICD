package spring.umc.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.umc.domain.member.entity.UserMission;
import spring.umc.domain.member.dto.MissionHistoryDto;


//음..
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    //미션현황, 홈화면 유저별 미션 정보 가져오는 쿼리
    @Query("SELECT new com.example.dto.MissionHistoryDto(s.name, um.updatedAt, m.condition, m.rewardPoint) " +
            "FROM UserMission um " +
            "JOIN um.mission m " +
            "JOIN m.store s " +
            "WHERE um.user.id = :userId AND um.isFinished = :isFinished")
    Page<MissionHistoryDto> findMyMission(
            @Param("userId") String userId,
            @Param("isFinished") boolean isFinished,
            Pageable pageable
    );

    //홈화면 달성미션개수 가져오는 쿼리
    @Query("SELECT COUNT(um) "+ "FROM UserMission um "+
            "WHERE um.user.id = :userId AND um.isFinished = true")
    long countFinishedMission(@Param("userId") String userId);


}
