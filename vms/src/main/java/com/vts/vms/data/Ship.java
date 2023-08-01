package com.vts.vms.data;

import com.vts.vms.data.enumeration.Flag;
import com.vts.vms.data.enumeration.ShipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Data
@AllArgsConstructor
@Document(collection = "voyages")
public class Ship{
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

    public Ship(String imo, String mmsi, String name, ShipType type, Date constructionYear, int breadthCm, int deadWeightTonnageTons, int draughtCm, int lengthCm, int grossTonnageTons, int netTonnageTons, Integer callSign, Flag flag) {
        this.imo = imo;
        this.mmsi = mmsi;
        this.name = name;
        this.type = type;
        this.constructionYear = constructionYear;
        this.breadthCm = breadthCm;
        this.deadWeightTonnageTons = deadWeightTonnageTons;
        this.draughtCm = draughtCm;
        this.lengthCm = lengthCm;
        this.grossTonnageTons = grossTonnageTons;
        this.netTonnageTons = netTonnageTons;
        this.callSign = callSign;
        this.flag = flag;

    }

    public Ship(){
    }
}
