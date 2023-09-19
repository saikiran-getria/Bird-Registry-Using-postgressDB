package com.ria.birdService.model;


import com.ria.birdService.exception.BirdAppException;
import com.ria.birdService.model.dto.BirdDTO;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.util.List;

@Entity
@Table(name = "bird")
@TypeDef(name = "string-array", typeClass = ListArrayType.class)
public class Bird {
    @Id
    private String id;
    private String name;
    private String family;
    @Type(type = "string-array")
    @Column(columnDefinition = "text[]")
    private List<String> continents;
    private String added;
    private Boolean visible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public List<String> getContinents() {
        return continents;
    }

    public void setContinents(List<String> continents) {
        this.continents = continents;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean isVisible() {
        if (visible == null) {
            return false;
        } else {
            return visible;
        }
    }

    public static Bird getBirdFromDTO(BirdDTO birdDTO) {
        Bird bird = new Bird();
        bird.setId(birdDTO.getId());
        bird.setName(birdDTO.getName());
        bird.setFamily(birdDTO.getFamily());
        bird.setContinents(birdDTO.getContinents());
        bird.setAdded(birdDTO.getAdded());
        bird.setVisible(birdDTO.getVisible());
        return bird;
    }

    public static BirdDTO getDTOFromBird(Bird bird) {
        BirdDTO birdDTO = new BirdDTO();
        birdDTO.setId(bird.getId());
        birdDTO.setName(bird.getName());
        birdDTO.setFamily(bird.getFamily());
        birdDTO.setContinents(bird.getContinents());
        birdDTO.setAdded(bird.getAdded());
        birdDTO.setVisible(bird.getVisible());
        return birdDTO;
    }

    public static void validateBird(Bird bird) {
        if (bird.getId() == null || bird.getId().isEmpty()) {
            throw new BirdAppException("Bird id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (bird.getName() == null || bird.getName().isEmpty()) {
            throw new BirdAppException("Bird name cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (bird.getFamily() == null || bird.getFamily().isEmpty()) {
            throw new BirdAppException("Bird family cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (bird.getContinents() == null || bird.getContinents().isEmpty()) {
            throw new BirdAppException("Bird continents cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (bird.getAdded() == null || bird.getAdded().isEmpty()) {
            throw new BirdAppException("Bird added cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}
