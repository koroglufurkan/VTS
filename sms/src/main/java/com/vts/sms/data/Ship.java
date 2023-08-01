package com.vts.sms.data;

import com.vts.sms.data.enumeration.Flag;
import com.vts.sms.data.enumeration.ShipType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@Document(collection = "Ships")
public class Ship {
    @Id
    private String id;
    private String imo; //3 letters 7 digits, 10 chars total
    private String mmsi; //9 digits in total
    @Indexed
    private String name; //Name of the ship
    private ShipType type ; //WAR, RESCUE, SHIPMENT

    private Date constructionYear;
    private Integer breadthCm;
    private Integer deadWeightTonnageTons;
    private Integer draughtCm; //Vertical length of part of the ship underwater
    private Integer lengthCm;
    private Integer grossTonnageTons;
    private Integer netTonnageTons;
    private Integer callSign;
    private Flag flag;

    public void clearId() {
        this.id = null;
    }
}
