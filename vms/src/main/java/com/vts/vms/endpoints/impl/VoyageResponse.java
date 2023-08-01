package com.vts.vms.endpoints.impl;
import com.vts.vms.core.service.VoyageService;
import com.vts.vms.data.Voyage;
import com.vts.vms.endpoints.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VoyageResponse implements Controller {
    VoyageService voyageService;

    @Autowired
    private VoyageResponse(VoyageService voyageService) {
        this.voyageService = voyageService;
    }


    public ResponseEntity<Voyage> createVoyage(Voyage voyage) {
        return voyageService.createVoyage(voyage);

    }


    public ResponseEntity<Voyage> getVoyageById(String id) {
        return voyageService.getVoyageById(id);

    }
    public ResponseEntity<List<Voyage>> getAllVoyages() {
        return voyageService.getAllVoyages();
    }
    public ResponseEntity<Voyage> updateVoyageById(String id, Voyage voyage) {
        return voyageService.updateVoyageById(id, voyage);
    }
    public ResponseEntity<Voyage> deleteVoyageById(String id) {
        return voyageService.deleteVoyageById(id);
    }
}
