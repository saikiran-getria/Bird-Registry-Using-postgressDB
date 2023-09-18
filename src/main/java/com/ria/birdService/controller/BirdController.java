package com.ria.birdService.controller;

import com.ria.birdService.model.Bird;
import com.ria.birdService.model.dto.BirdDTO;
import com.ria.birdService.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/birds")
public class BirdController {
    private BirdService service;

    @Autowired
    public BirdController(BirdService service) {
        this.service = service;
    }

    @GetMapping
    public List<Bird> getAllVisibleBirds() {
        return service.getAllVisibleBirds();
    }

    @PostMapping
    public ResponseEntity<Bird> addBird(@RequestBody BirdDTO bird) {
        return ResponseEntity.ok(service.addBird(bird));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bird>> getBirdById(@PathVariable String id) {
        return ResponseEntity.ok(service.getBirdById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirdById(@PathVariable String id) {
        service.deleteBirdById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
