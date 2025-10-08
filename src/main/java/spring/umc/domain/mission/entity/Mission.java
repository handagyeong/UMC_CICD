package spring.umc.domain.mission.entity;


import spring.umc.global.BaseEntity;
import spring.umc.domain.member.entity.UserMission;
import spring.umc.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String missionCondition; //예약어?때문에 수정

    private Integer point;

    private Integer rewardPercent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    private List<UserMission> userMissionList = new ArrayList<>();
}