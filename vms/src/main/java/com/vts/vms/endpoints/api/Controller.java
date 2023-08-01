package com.vts.vms.endpoints.api;
import com.vts.vms.data.Voyage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/voyages")
public interface Controller {
    @PostMapping
    ResponseEntity<Voyage> createVoyage(@RequestBody Voyage voyage);
    //GetVoyageById
    @GetMapping("/{id}")
    ResponseEntity<Voyage> getVoyageById(@PathVariable String id);
    //GetAllVoyages
    @GetMapping
    ResponseEntity<List<Voyage>> getAllVoyages();
    //UpdateVoyageById
    @PutMapping("/{id}")
    ResponseEntity<Voyage> updateVoyageById(@PathVariable String id, @RequestBody Voyage voyage);
    //DeleteVoyageById
    @DeleteMapping("/{id}")
    ResponseEntity<Voyage> deleteVoyageById(@PathVariable String id);


}
