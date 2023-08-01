package com.vts.sms.repository;

import com.vts.sms.data.Ship;
import com.vts.sms.data.enumeration.Flag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.random.RandomGenerator;

@DataMongoTest
public class CRUDTests {
    ShipRepository shipRepository;

    MongoTemplate mongoTemplate;

    String testedShipId;
    @Autowired
    public CRUDTests(ShipRepository shipRepository, MongoTemplate mongoTemplate) {
        this.shipRepository = shipRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Test
    public void shipRepository_saveUpdateDeleteRandomShip(){
        //Arrange
        int random = RandomGenerator.getDefault().nextInt();
        Ship ship = Ship.builder()
                .name(Integer.toString(random))
                .build();
        System.out.println("RandomShipCreated with randomized name of: "+ random);
        System.out.println(ship);
        //Act
        ship.setId(shipRepository.save(ship).getId());
        System.out.println("RandomShipCreated");
        System.out.println(ship);
        //Assert
        Assertions.assertThat(ship).isNotNull();
        System.out.print("Ship is there, ");
        Assertions.assertThat(ship.getId()).isNotEmpty();
        System.out.print("has Id: "+ ship.getId());
        Assertions.assertThat(ship.getName()).isEqualTo(Integer.toString(random));
        System.out.println("has name: "+ ship.getName());
        this.testedShipId = ship.getId();
        System.out.println("testedShipId: "+ testedShipId);





        //Arrange
        List<Ship> foundShip;
        String shipToUpdateId = this.testedShipId;
        System.out.println("shipToUpdateId: "+ shipToUpdateId);
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(shipToUpdateId));
        Ship newShip = Ship.builder().imo("3245").id(shipToUpdateId).flag(Flag.TURKISH).build();
        Ship shipToControl;
        //Act
        shipRepository.save(newShip);
        System.out.println("Ship is saved (should be changed with its older form):");
        System.out.println(newShip);
        System.out.println("Finding ships with Id: "+testedShipId);
        foundShip = mongoTemplate.find(query, Ship.class);
        if (foundShip.size() == 1){
            shipToControl = foundShip.get(0);
            //Assert
            Assertions.assertThat(shipToControl.getId()).isEqualTo(testedShipId);
            Assertions.assertThat(shipToControl.getImo()).isEqualTo("3245");
            Assertions.assertThat(shipToControl.getFlag()).isEqualTo(Flag.TURKISH);
        }
        else {
            throw new RuntimeException("!!!Duplicated ships. Somethings wrong!!!");
        }








        //Arrange
        query = new Query();
        query.addCriteria(Criteria.where("id").is(testedShipId));
        Ship shipDeleted = mongoTemplate.findOne(query, Ship.class);
        //Act
        if (shipDeleted != null)
        {
            shipRepository.deleteById(shipDeleted.getId());
            //Assert
            Assertions.assertThat(shipRepository.findById(shipDeleted.getId()).isPresent()).isFalse();
            System.out.println("Couldn't find the ship, successfully deleted.");

        }
        else {
            throw new RuntimeException("NoShipToDelete: Please first run a test to create tested Ship.");
        }




    }

}
