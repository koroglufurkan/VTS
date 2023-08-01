package com.vts.sms.endpoints.api;
import com.vts.sms.data.Ship;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ships")
public interface Controller {
    //CreateShip
    @PostMapping
    ResponseEntity<Ship> createShip(@RequestBody Ship ship);
    //Search
    @GetMapping("/search")
    ResponseEntity<List<Ship>> searchShips(@RequestParam(required = false, defaultValue = "") String name, @RequestParam(required = false, defaultValue = "UNKNOWN") String flag);
    //Filter
    @GetMapping("/filter")
    ResponseEntity<List<Ship>> filterShips(@RequestParam(required = false, defaultValue = "") String imoNo, @RequestParam(required = false, defaultValue = "") String mmsi, @RequestParam(required = false, defaultValue = "") String name);
    //GetShipById
    @GetMapping("/{id}")
    ResponseEntity<Ship> getShipById(@PathVariable String id);
    //GetAllShips
    @GetMapping
    ResponseEntity<List<Ship>> getAllShips();
    //UpdateShipById
    @PutMapping("/{id}")
    ResponseEntity<Ship> updateShipById(@PathVariable String id, @RequestBody Ship ship);
    //DeleteShipById
    @DeleteMapping("/{id}")
    ResponseEntity<Ship> deleteShipById(@PathVariable String id);
}
