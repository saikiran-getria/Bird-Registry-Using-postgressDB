///*
//package com.ria.birdService.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.ria.birdService.controller.exception.InternalServerException;
//import com.ria.birdService.model.Bird;
//import com.ria.birdService.repos.BirdRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ContextConfiguration(classes = {BirdService.class})
//@ExtendWith(SpringExtension.class)
//class BirdServiceTest {
//    @MockBean
//    private BirdRepository birdRepository;
//
//    @Autowired
//    private BirdService birdService;
//
//    @Test
//    void testGetAllVisibleBirds_throwInternalServerException() {
//        when(birdRepository.findAll()).thenThrow(new InternalServerException("An error occurred"));
//        assertThrows(InternalServerException.class, () -> birdService.getAllVisibleBirds());
//    }
//
//    @Test
//    void testGetAllVisibleBirds_IsEmpty() {
//        when(birdRepository.findAll()).thenReturn(new ArrayList<>());
//        assertTrue(birdService.getAllVisibleBirds().isEmpty());
//    }
//
//
//    @Test
//    void testGetAllVisibleBirds_NoVisibility_ReturnFalse() {
//        Bird bird = new Bird();
//        bird.setAdded("23-08-24");
//        bird.setContinents(new ArrayList<>());
//        bird.setFamily("Family");
//        bird.setId("42");
//        bird.setName("Name");
//
//        ArrayList<Bird> birdList = new ArrayList<>();
//        birdList.add(bird);
//        when(birdRepository.findAll()).thenReturn(birdList);
//        assertEquals(0, birdService.getAllVisibleBirds().size());
//    }
//
//
//    @Test
//    void testGetAllVisibleBirds5() {
//        Bird bird = new Bird();
//        bird.setAdded("23-08-24");
//        bird.setContinents(new ArrayList<>());
//        bird.setFamily("Family");
//        bird.setId("123");
//        bird.setName("Name");
//        bird.setVisible(true);
//
//        Bird bird2 = new Bird();
//        bird2.setAdded("23-08-24");
//        bird2.setContinents(new ArrayList<>());
//        bird2.setFamily("abc");
//        bird2.setId("Id");
//        bird2.setName("42");
//        bird2.setVisible(false);
//
//        ArrayList<Bird> birdList = new ArrayList<>();
//        birdList.add(bird2);
//        birdList.add(bird);
//        when(birdRepository.findAll()).thenReturn(birdList);
//        assertEquals(1, birdService.getAllVisibleBirds().size());
//    }
//
//
//
//    @Test
//    void testAddBird(){
//        Bird bird = new Bird();
//        bird.setAdded("23-08-24");
//        bird.setContinents(new ArrayList<>());
//        bird.setFamily("Family");
//        bird.setId("42");
//        bird.setName("Name");
//        bird.setVisible(false);
//        when(birdRepository.save(bird)).thenReturn(bird);
//        Bird addedBird = birdService.addBird(bird);
//        assertNotNull(addedBird);
//        assertFalse(addedBird.isVisible());
//    }
//
//
//    @Test
//    void testGetBirdById() {
//        Bird bird = new Bird();
//        bird.setAdded("23-08-24");
//        bird.setContinents(new ArrayList<>());
//        bird.setFamily("Family");
//        bird.setId("123");
//        bird.setName("Name");
//        bird.setVisible(true);
//        Optional<Bird> result = Optional.of(bird);
//        when(birdRepository.findById("123")).thenReturn(result);
//        Optional<Bird> actualBirdById = birdService.getBirdById("123");
//        assertSame(result, actualBirdById);
//        assertTrue(actualBirdById.isPresent());
//    }
//
//
//    @Test
//    void testGetBirdById_ThrowException() {
//        when(birdRepository.findById(Mockito.<String>any())).thenThrow(new InternalServerException("An error occurred"));
//        assertThrows(InternalServerException.class, () -> birdService.getBirdById("42"));
//    }
//
//
//    @Test
//    void testDeleteBirdById() {
//        birdService.deleteBirdById("123");
//        assertTrue(birdService.getAllVisibleBirds().isEmpty());
//    }
//
//    @Test
//    public void testAddBirdThrowsInternalServerException() {
//        Bird newBird = new Bird();
//        when(birdRepository.save(newBird)).thenThrow(new RuntimeException("Simulated exception"));
//        assertThrows(InternalServerException.class, () -> birdService.addBird(newBird));
//    }
//
//    @Test
//    void testDeleteBirdById2() {
//        doThrow(new InternalServerException("An error occurred")).when(birdRepository).deleteById(Mockito.<String>any());
//        assertThrows(InternalServerException.class, () -> birdService.deleteBirdById("42"));
//    }
//}
//
//*/
