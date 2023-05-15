package com.ssafy.greenEarth.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "parents")
public class Parent extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private int id;

    @Column(length = 50)
    private String email;

    @Column(length = 25)
    private String nickname;

    @Column(length = 25)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Child> childList = new ArrayList<>();


}
