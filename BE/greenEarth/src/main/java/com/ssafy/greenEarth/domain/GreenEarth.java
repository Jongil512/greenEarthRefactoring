package com.ssafy.greenEarth.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "green_earth")
public class GreenEarth extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "green_earth_id")
    private int id;

    private int mileageCondition;
}
