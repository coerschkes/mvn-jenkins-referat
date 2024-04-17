package com.github.schwarzfelix.coerschkes.resourceserver.domain.application;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(final long id) {
        super("Could not find entity with id " + id);
    }
}
