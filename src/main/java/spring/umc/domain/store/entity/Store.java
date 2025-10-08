package spring.umc.domain.store.entity;


import spring.umc.global.BaseEntity;
import spring.umc.domain.mission.entity.Mission;
import spring.umc.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lane;

    @Column(length = 20)
    private String phoneNum;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String detailAddress;

    private LocalTime openingHours;

    private LocalTime closingHours;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id")
    private Location location;


    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();


    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> missionList = new ArrayList<>();
}