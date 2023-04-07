package com.smartfarm.sensordata.service.farm.event;

import com.smartfarm.sensordata.service.farm.model.SensorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorEvent implements Serializable {

    private String eventType;

    private SensorDTO sensorDTO;
}
