package com.ria.birdService.repository;

import com.ria.birdService.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository<Bird, String> {
    //List<Bird> findAll();

    //Bird save(Bird bird);

    //Optional<Bird> findById(String id);

    //void deleteById(String id);
}
