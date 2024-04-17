package com.github.schwarzfelix.coerschkes.resourceserver.infrastructure;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.application.CampingTentRepository;
import com.github.schwarzfelix.coerschkes.resourceserver.domain.application.EntityNotFoundException;
import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    private final CampingTentRepository repository;

    public ResourceController(CampingTentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tents")
    public List<CampingTent> tents() {
        return this.repository.findAll();
    }

    @GetMapping("/tents/{id}")
    public CampingTent tent(@PathVariable long id) {
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @DeleteMapping("/tents/{id}")
    public ResponseEntity<String> deleteTent(@PathVariable long id) {
        this.repository.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
