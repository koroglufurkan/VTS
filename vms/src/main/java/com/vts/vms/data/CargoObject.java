package com.vts.vms.data;

import com.vts.vms.data.enumeration.CargoUnit;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CargoObject {
    private String cargoName;
    private CargoUnit cargoUnit;
    private Integer cargoAmount;
}
