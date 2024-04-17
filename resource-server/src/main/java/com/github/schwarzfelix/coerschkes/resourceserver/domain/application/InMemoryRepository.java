package com.github.schwarzfelix.coerschkes.resourceserver.domain.application;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;

import java.util.List;
import java.util.Optional;

public class InMemoryRepository implements CampingTentRepository {
    private final List<CampingTent> campingTents;

    public InMemoryRepository() {
        this.campingTents = List.of(
                new CampingTent(1L, "BigBaba Tent", "This tent is what it's name states: big baba and great!", "399€"),
                new CampingTent(2L, "Small pit", "small but  comfortable", "20€")
        );
    }

    @Override
    public List<CampingTent> findAll() {
        return this.campingTents;
    }

    @Override
    public Optional<CampingTent> findById(long id) {
        return this.campingTents.stream().filter(tent -> tent.id() == id).findFirst();
    }

    @Override
    public void deleteById(long id) {
        this.findById(id).ifPresentOrElse(this.campingTents::remove, () -> {
            throw new EntityNotFoundException(id);
        });
    }
}
