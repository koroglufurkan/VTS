package com.vts.vms.core.kafka;
import com.vts.vms.endpoints.impl.VoyageResponse;
import com.vts.vms.data.Ship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicManager {
    private final VoyageResponse voyageResponse;
    @Autowired
    private TopicManager(VoyageResponse voyageResponse) {
        this.voyageResponse = voyageResponse;
    }
    @KafkaListener(topics = "shipUpdate")
    public void listenShipUpdated(Ship ship) {
        voyageResponse.updateVoyageByShip(ship);
    }
    @KafkaListener(topics = "shipDelete")
    public void listenShipDeleted(Ship ship) {
        String shipId = ship.getId();
        voyageResponse.deleteVoyageByShipId(shipId);
    }


}
