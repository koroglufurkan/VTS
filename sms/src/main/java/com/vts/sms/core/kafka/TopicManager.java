package com.vts.sms.core.kafka;

import com.vts.sms.data.Ship;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
//Also a bean, meaning constructor is called initially within the bean cycle.
@Service
public class TopicManager {
    private final KafkaTemplate<Integer, Ship> shipTemplate;
//    private final KafkaTemplate<Integer, Ship[]> shipsTemplate;
    private final NewTopic shipAdd;
    private final NewTopic shipUpdate;
    private final NewTopic shipDelete;

    //shipTemplate will be auto-wired
    @Autowired
    public TopicManager(KafkaTemplate<Integer, Ship> shipTemplate)
    {
        shipAdd = TopicBuilder.name("shipAdd").build();
        shipUpdate = TopicBuilder.name("shipUpdate").build();
        shipDelete = TopicBuilder.name("shipDelete").build();
        this.shipTemplate = shipTemplate;
    }

    public void shipAdded(Ship ship) {
        shipTemplate.send("shipAdd",ship);
    }
    public void shipUpdated(Ship ship) {
        shipTemplate.send("shipUpdate",ship);
    }
    public void shipDeleted(Ship ship) {
        shipTemplate.send("shipDelete",ship);
    }


}
