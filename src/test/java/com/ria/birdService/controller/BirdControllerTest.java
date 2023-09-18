package com.ria.birdService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.ria.birdService.controller.exception.BirdNotFoundException;
import com.ria.birdService.model.Bird;
import com.ria.birdService.service.BirdService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ContextConfiguration(classes = {BirdController.class})
@ExtendWith(SpringExtension.class)
class BirdControllerTest {
    @Autowired
    private BirdController birdController;

    @MockBean
    private BirdService birdService;

    BirdControllerTest(BirdController birdController, BirdService birdService) {
        this.birdController = birdController;
        this.birdService = birdService;
    }


    @Test
    public void testGetAllVisibleBirds() {
        List<Bird> birdList = List.of(new Bird(), new Bird());
        when(birdService.getAllVisibleBirds()).thenReturn(birdList);

        List<Bird> result = birdController.getAllVisibleBirds();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddBird() {
        Bird bird = new Bird();
        when(birdService.addBird(any(Bird.class))).thenReturn(bird);

        Bird result = birdController.addBird(new Bird());

        assertEquals(bird, result);
    }

    @Test
    public void testGetBirdByIdFound() {
        String birdId = "123";
        Bird bird = new Bird();
        when(birdService.getBirdById(birdId)).thenReturn(Optional.of(bird));

        ResponseEntity<Optional<Bird>> response = birdController.getBirdById(birdId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bird, response.getBody().orElse(null));
    }

    @Test
    public void testGetBirdByIdNotFound() {
        String birdId = "456";
        when(birdService.getBirdById(birdId)).thenReturn(Optional.empty());

        assertThrows(BirdNotFoundException.class, () -> {
            birdController.getBirdById(birdId);
        });
    }

    @Test
    public void testDeleteBirdByIdFound() {
        String birdId = "789";
        Bird bird = new Bird();
        when(birdService.getBirdById(birdId)).thenReturn(Optional.of(bird));

        ResponseEntity<Optional<Bird>> response = birdController.deleteBirdById(birdId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bird, response.getBody().orElse(null));
        verify(birdService, times(1)).deleteBirdById(birdId);
    }

    @Test
    public void testDeleteBirdByIdNotFound() {
        String birdId = "101";
        when(birdService.getBirdById(birdId)).thenReturn(Optional.empty());

        assertThrows(BirdNotFoundException.class, () -> {
            birdController.deleteBirdById(birdId);
        });
    }
}

