package com.vts.sms.endpoints.impl;

import com.vts.sms.core.kafka.TopicManager;
import com.vts.sms.data.Ship;
import com.vts.sms.endpoints.api.Controller;
import com.vts.sms.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipResponse implements Controller {
    TopicManager topicManager;
    MongoTemplate mongoTemplate;
    ShipRepository shipRepository;
    @Autowired
    private ShipResponse(TopicManager topicManager, MongoTemplate mongoTemplate, ShipRepository shipRepository){
        this.topicManager = topicManager;
        this.mongoTemplate = mongoTemplate;
        this.shipRepository = shipRepository;
    }

    public ResponseEntity<Ship> createShip(Ship ship) {
        ship.clearId();
        ship.setId(shipRepository.save(ship).getId());
        topicManager.shipAdded(ship);
        return new ResponseEntity<>(ship, HttpStatusCode.valueOf(200));
    }

    public ResponseEntity<List<Ship>> searchShips(String name, String flag)
    {
        Query query = new Query();
        if(!name.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex("^"+name));
        }
        if(!flag.equals("UNKNOWN")) {
            query.addCriteria(Criteria.where("flag").regex("^"+flag));
        }
        List<Ship> result = mongoTemplate.find(query, Ship.class);
        if (result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(404));
        }
        else {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }

    }

    public ResponseEntity<List<Ship>> filterShips(String imoNo, String mmsi, String name)
    {
        Query query = new Query();
        if(!imoNo.isEmpty())
        {
            query.addCriteria(Criteria.where("imo").is(imoNo));
        }
        if(!mmsi.isEmpty())
        {
            query.addCriteria(Criteria.where("mmsi").is(mmsi));
        }
        if(!name.isEmpty())
        {
            query.addCriteria(Criteria.where("name").is(name));
        }
        List<Ship> result = mongoTemplate.find(query, Ship.class);
        if (result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(404));
        }
        else {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }
    }

    public ResponseEntity<Ship> getShipById(String id) {
        Ship result;
        if ((result = shipRepository.findById(id).orElse(null)) == null) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
        else {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }

    }

    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> result = shipRepository.findAll();
        if (result.isEmpty()){
            return new ResponseEntity<>(result,HttpStatusCode.valueOf(404));
        }
        else {
            return new ResponseEntity<>(result,HttpStatusCode.valueOf(200));
        }
    }

    public ResponseEntity<Ship> updateShipById(String id, Ship ship) {
        if ((shipRepository.findById(id).orElse(null)) != null) {
            ship.setId(id);
            shipRepository.save(ship);
            topicManager.shipUpdated(ship);
            return new ResponseEntity<>(ship, HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }
    public ResponseEntity<Ship> deleteShipById(String id) {
        Ship foundShip;
        if((foundShip = shipRepository.findById(id).orElse(null)) != null)
        {
            shipRepository.deleteById(id);
            topicManager.shipDeleted(foundShip);
            return new ResponseEntity<>(foundShip, HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(foundShip, HttpStatusCode.valueOf(404));
        }

    }
}
