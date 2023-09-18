package com.ria.birdService.service;

import com.ria.birdService.exception.BirdAppException;
import com.ria.birdService.model.Bird;
import com.ria.birdService.model.dto.BirdDTO;
import com.ria.birdService.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BirdService {
    private final BirdRepository repository;

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

    public Bird addBird(BirdDTO birdDTO) {
        try {
            Bird bird = Bird.getBirdFromDTO(birdDTO);
            Bird.validateBird(bird);
            bird.setVisible(bird.isVisible());
            bird.setAdded(bird.getAdded());
            return repository.save(bird);
        } catch (BirdAppException e) {
            throw new BirdAppException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<Bird> getBirdById(String id) {
        Optional<Bird> bird = repository.findById(id);
        if (bird.isEmpty()) {
            throw new BirdAppException("Bird with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return repository.findById(id);
    }

    public void deleteBirdById(String id) {
        Optional<Bird> bird = getBirdById(id);
        if (bird.isEmpty()) {
            throw new BirdAppException("Bird with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
