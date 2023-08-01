package com.vts.vms.repository;

import com.vts.vms.data.Voyage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoyageRepository extends MongoRepository<Voyage, String> {

}
