package com.github.schwarzfelix.coerschkes.resourceserver.domain.application;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CampingTentRepository {
    List<CampingTent> findAll();

    Optional<CampingTent> findById(long id);

    void deleteById(long id);
}