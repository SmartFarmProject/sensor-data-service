package com.smartfarm.sensordata.service.farm.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO implements Serializable {

    private String id;
    private String unitId;
    private String physicalId;
    private String dataSourceFieldName;
    private SensorType sensorType;

    public enum SensorType {
        AIR_HUMIDITY,
        SOIL_HUMIDITY,
        TEMPERATURE,
        LIGHT,
        SOIL_PH
    }
}
