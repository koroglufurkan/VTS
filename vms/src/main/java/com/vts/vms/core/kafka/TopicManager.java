package com.vts.vms.core.kafka;
import com.vts.vms.core.service.VoyageService;
import com.vts.vms.data.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicManager {
    private final VoyageService voyageService;
    @Autowired
    private TopicManager(VoyageService voyageService) {
        this.voyageService = voyageService;
    }
    @KafkaListener(topics = "shipUpdate")
    public void listenShipUpdated(Ship ship) {
        voyageService.updateVoyageByShip(ship);
    }
    @KafkaListener(topics = "shipDelete")
    public void listenShipDeleted(Ship ship) {
        String shipId = ship.getId();
        voyageService.deleteVoyageByShipId(shipId);
    }


}
