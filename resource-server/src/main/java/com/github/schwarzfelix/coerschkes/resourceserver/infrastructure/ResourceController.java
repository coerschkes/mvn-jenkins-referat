package com.github.schwarzfelix.coerschkes.resourceserver.infrastructure;

import com.github.schwarzfelix.coerschkes.resourceserver.domain.application.CampingTentRepository;
import com.github.schwarzfelix.coerschkes.resourceserver.domain.application.EntityNotFoundException;
import com.github.schwarzfelix.coerschkes.resourceserver.domain.entity.CampingTent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PutMapping("/tents/{id}")
    public ResponseEntity<String> updateTent(@PathVariable long id) {
        this.repository.orderSingle(id);
        return ResponseEntity.created(URI.create("/tents/" + id)).build();
    }


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Void> handleEntityNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IllegalStateException.class})
    public ResponseEntity<Void> handleIllegalStateException() {
        return ResponseEntity.badRequest().build();
    }
}
