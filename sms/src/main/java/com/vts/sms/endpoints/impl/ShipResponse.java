package com.vts.sms.endpoints.impl;

import com.vts.sms.core.service.ShipService;
import com.vts.sms.data.Ship;
import com.vts.sms.endpoints.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipResponse implements Controller {
    ShipService shipService;
    @Autowired
    private ShipResponse(ShipService shipService){
        this.shipService = shipService;
    }

    public ResponseEntity<Ship> createShip(Ship ship) {
        return shipService.createShip(ship);
    }

    public ResponseEntity<List<Ship>> searchShips(String name, String flag)
    {
        return shipService.searchShips(name,flag);

    }

    public ResponseEntity<List<Ship>> filterShips(String imoNo, String mmsi, String name)
    {
        return shipService.filterShips(imoNo, mmsi, name);
    }

    public ResponseEntity<Ship> getShipById(String id) {
        return shipService.getShipById(id);

    }

    public ResponseEntity<List<Ship>> getAllShips() {
        return shipService.getAllShips();
    }

    public ResponseEntity<Ship> updateShipById(String id, Ship ship) {
        return shipService.updateShipById(id, ship);
    }
    public ResponseEntity<Ship> deleteShipById(String id) {
        return shipService.deleteShipById(id);

    }
}
