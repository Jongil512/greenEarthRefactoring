package com.ssafy.greenEarth.repository.childRepo;

import com.ssafy.greenEarth.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChildRepository extends JpaRepository<Child, Integer>, ChildRepositoryCustom{

//    @Query("select c from Child c " +
//            "join fetch c.parent " +
//            "left join fetch c.missionLogList ml " +
//            "left join fetch ml.mission " +
//            "where c.id = :id")
//    Optional<Child> findChildById(@Param("id") int id);

    Optional<Child> findByNickname(String nickname);

}
