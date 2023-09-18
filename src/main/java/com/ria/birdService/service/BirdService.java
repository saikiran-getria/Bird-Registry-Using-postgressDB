package com.ria.birdService.service;

import com.ria.birdService.controller.exception.InternalServerException;
import com.ria.birdService.model.Bird;
import com.ria.birdService.repos.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BirdService {
    private BirdRepository repository;
    @Autowired
    public BirdService(BirdRepository repository) {
        this.repository = repository;
    }
    public List<Bird> getAllVisibleBirds() {
        List<Bird> allBirds = repository.findAll();
        return allBirds.stream()
                .filter(Bird::isVisible)
                .collect(Collectors.toList());
    }
    public Bird addBird(Bird bird) {
        try {
            bird.setVisible(bird.isVisible());
            bird.setAdded(bird.getAdded());
            return repository.save(bird);
        }catch (Exception e) {
            throw new InternalServerException("An internal server error occurred.");
        }
    }
    public Optional<Bird> getBirdById(String id) {
        return repository.findById(id);
    }
    public void deleteBirdById(String id) {
        repository.deleteById(id);
    }
}
