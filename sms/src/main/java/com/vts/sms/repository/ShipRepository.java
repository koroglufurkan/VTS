package com.vts.sms.repository;

import com.vts.sms.data.Ship;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface ShipRepository extends MongoRepository<Ship, String> {

}
