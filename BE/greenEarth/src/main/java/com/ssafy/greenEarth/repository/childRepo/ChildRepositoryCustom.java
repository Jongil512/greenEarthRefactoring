package com.ssafy.greenEarth.repository.childRepo;

import com.ssafy.greenEarth.domain.Child;

import java.util.Optional;

public interface ChildRepositoryCustom {

    Optional<Child> findChildById(int childId);
}
