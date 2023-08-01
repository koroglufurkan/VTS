package com.vts.vms.core.service;

import com.vts.vms.data.Ship;
import com.vts.vms.data.Voyage;
import com.vts.vms.repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VoyageService {
    VoyageRepository voyageRepository;
    MongoTemplate mongoTemplate;

    @Autowired
    private VoyageService(VoyageRepository voyageRepository, MongoTemplate mongoTemplate) {
        this.voyageRepository = voyageRepository;
        this.mongoTemplate = mongoTemplate;
    }


    public ResponseEntity<Voyage> createVoyage(Voyage voyage) {
        if (findVoyageIdByShipId(voyage.getShip().getId()) == null)
        {
            voyage.clearId();
            String voyageId = voyageRepository.save(voyage).getId();
            voyage.setId(voyageId);
            return new ResponseEntity<>(voyage, HttpStatusCode.valueOf(200));

        }
        else{
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(409));
        }

    }


    public ResponseEntity<Voyage> getVoyageById(String id) {
        Voyage result;
        if ((result = voyageRepository.findById(id).orElse(null)) == null) {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
        else {
            return new ResponseEntity<>(result, HttpStatusCode.valueOf(200));
        }

    }
    public ResponseEntity<List<Voyage>> getAllVoyages() {
        List<Voyage> result = voyageRepository.findAll();
        if (result.isEmpty()) {
            return new ResponseEntity<>(result,HttpStatusCode.valueOf(404));
        }
        else{
            return new ResponseEntity<>(result,HttpStatusCode.valueOf(200));
        }
    }
    public ResponseEntity<Voyage> updateVoyageById(String id, Voyage voyage) {
        Voyage voyageToReturn;
        if ((voyageToReturn = updateVoyageByIdMethod(id, voyage)) != null) {
            return new ResponseEntity<>(voyageToReturn, HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }
    public void deleteVoyageByShipId(String id) {
        Voyage foundVoyage;
        Query query = new Query();
        query.addCriteria(Criteria.where("ship.id").is(id));
        if((foundVoyage = mongoTemplate.findOne(query, Voyage.class)) != null)
        {
            voyageRepository.deleteById(foundVoyage.getId());
        }
    }
    public ResponseEntity<Voyage> deleteVoyageById(String id) {
        Voyage foundVoyage;
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        if((foundVoyage = mongoTemplate.findOne(query, Voyage.class)) != null)
        {
            voyageRepository.deleteById(foundVoyage.getId());
            return new ResponseEntity<>(foundVoyage, HttpStatusCode.valueOf(200));
        }
        else {
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
        }
    }
    public Voyage updateVoyageByIdMethod(String id,Voyage voyage) {
        if (voyageRepository.findById(id).orElse(null) != null) {
            voyage.setId(id);
            return voyageRepository.save(voyage);
        }
        else {
            return null;
        }
    }
    public String findVoyageIdByShipId(String shipId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ship.id").is(shipId));
        Voyage voyage= mongoTemplate.findOne(query,Voyage.class);
        if(voyage != null) {
            return voyage.getId();
        }
        else {
            return null;
        }
    }
    public void updateVoyageByShip(Ship ship) {
        Query query = new Query();
        query.addCriteria(Criteria.where("ship.id").is(ship.getId()));
        Voyage voyageToUpdate = mongoTemplate.findOne(query,Voyage.class);
        if (voyageToUpdate != null) {
            voyageToUpdate.setShip(ship);
            voyageRepository.save(voyageToUpdate);
        }
    }
}
