package com.ssafy.greenEarth.repository.parentRepo;

import com.ssafy.greenEarth.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Integer>, ParentRepositoryCustom{

    Optional<Parent> findByEmail(String email);

//    @Query("select p from Parent p left join fetch p.childList where p.id =:id")
//    Optional<Parent> findById(@Param("id") int id);

}
