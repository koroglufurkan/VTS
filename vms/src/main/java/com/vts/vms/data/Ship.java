package com.vts.vms.data;

import com.vts.vms.data.enumeration.Flag;
import com.vts.vms.data.enumeration.ShipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship{
    private String id;
    private String imo; //3 letters 7 digits, 10 chars total
    private String mmsi; //9 digits in total
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
}
