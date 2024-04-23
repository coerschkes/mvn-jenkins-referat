package com.github.schwarzfelix.coerschkes.resourceserver.domain.application;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class InMemoryRepositoryTest {
    private InMemoryRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        final ResourceLoader resourceLoader = Mockito.mock(ResourceLoader.class);
        final Resource resource = Mockito.mock(Resource.class);
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getContentAsByteArray()).thenReturn(new byte[]{0, 1, 2, 3, 4});
        repository = new InMemoryRepository(resourceLoader);
    }

    @Test
    void findAll_should_return_list_of_initial_values() {
        assertThat(repository.findAll()).hasSize(4);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    void findById_should_return_optional_of_tent_with_id(final long id) {
        final Optional<CampingTent> byId = repository.findById(id);
        assertThat(byId).isPresent();
        assertThat(byId).get().extracting("id").isEqualTo(id);
    }

    @Test
    void findById_should_return_optional_empty_if_tent_with_id_does_not_exist() {
        final Optional<CampingTent> byId = repository.findById(399L);
        assertThat(byId).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3, 4})
    void deleteById_should_delete_tent_with_id(final long id) {
        repository.deleteById(id);
        assertThat(repository.findById(id)).isEmpty();
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    void deleteById_should_throw_EntityNotFoundException_if_tent_with_id_does_not_exist() {
        assertThatThrownBy(() -> repository.deleteById(399L));
    }

    @Test
    void orderSingle_should_throw_NoSuchElementException_if_tent_with_id_does_not_exist() {
        assertThatThrownBy(() -> repository.orderSingle(399L)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void orderSingle_should_reduce_stock_of_tent_with_id_by_one() {
        final int stockBefore = repository.findById(1L).orElseThrow().stock();
        repository.orderSingle(1L);
        assertThat(repository.findById(1L).orElseThrow().stock()).isEqualTo(stockBefore - 1);
    }


    @Test
    void orderSingle_should_throw_IllegalStateException_if_stock_of_tent_is_zero() {
        while (repository.findById(1L).orElseThrow().stock() != 0) {
            repository.orderSingle(1L);
        }

        assertThatThrownBy(() -> repository.orderSingle(1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Stock is empty!");
    }

    @Test
    void orderSingle_should_contain_list_order() {
        final List<CampingTent> allBefore = repository.findAll();
        repository.orderSingle(3L);
        assertThat(repository.findAll()).containsExactly(allBefore.get(0), allBefore.get(1), allBefore.get(2), allBefore.get(3));
    }
}