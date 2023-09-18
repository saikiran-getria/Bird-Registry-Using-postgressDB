package com.ria.birdService.controller;

import com.ria.birdService.controller.exception.BirdNotFoundException;
import com.ria.birdService.model.Bird;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Bird addBird(@RequestBody Bird bird) {
        return service.addBird(bird);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bird>> getBirdById(@PathVariable String id) {
        Optional<Bird> bird = service.getBirdById(id);
        if (bird.isEmpty()) {
            throw new BirdNotFoundException(id);
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bird, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Bird>> deleteBirdById(@PathVariable String id) {
        Optional<Bird> bird = service.getBirdById(id);
        if (bird.isEmpty()) {
            throw new BirdNotFoundException(id);
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteBirdById(id);
        return new ResponseEntity<>(bird, HttpStatus.OK);

    }
}
