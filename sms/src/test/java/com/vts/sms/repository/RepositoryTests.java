package com.vts.sms.repository;

import com.vts.sms.data.Ship;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class RepositoryTests {
    ShipRepository shipRepository;
    @Autowired
    public RepositoryTests(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Test
    public void shipRepository_saveShip(){
        //Arrange
        Ship ship = Ship.builder()
                .name("testShip0")
                .build();


        //Act
        Ship savedShip = shipRepository.save(ship);
        //Assert
        Assertions.assertThat(savedShip).isNotNull();
        Assertions.assertThat(savedShip.getId()).isNotEmpty();
    }
}
