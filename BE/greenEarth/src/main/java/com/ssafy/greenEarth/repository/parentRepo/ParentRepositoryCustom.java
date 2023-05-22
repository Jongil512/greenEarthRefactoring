package com.ssafy.greenEarth.repository.parentRepo;

import com.ssafy.greenEarth.domain.Parent;

import java.util.Optional;

public interface ParentRepositoryCustom {

    Optional<Parent> findById(int parentId);
}
