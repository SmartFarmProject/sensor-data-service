package com.smartfarm.sensordata.mapper;

import com.smartfarm.sensordata.model.*;
import com.smartfarm.sensordata.service.farm.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class IoTSensorProcessedDtoMapper {

    private final SensorService sensorService;

    public IotSensorProcessedDto mapToSensorProcessedDto(AirHumiditySensor airHumiditySensor){
        return IotSensorProcessedDto.builder()
                .sensorId(airHumiditySensor.getId())
                .type(SensorType.AIR_HUMIDITY.name())
                .value(Double.parseDouble(airHumiditySensor.getValue()))
                .status(SensorStatus.ON.name())
                .farmUnitId(sensorService.getFarmUnitBySensorId(airHumiditySensor.getId()))
                .unit(airHumiditySensor.getUnit())
                .timestamp(airHumiditySensor.getTimestamp().toInstant().atOffset(ZoneOffset.UTC))
                .build();
    }

    public IotSensorProcessedDto mapToSensorProcessedDto(LightSensor lightSensor){
        return IotSensorProcessedDto.builder()
                .sensorId(lightSensor.getId())
                .type(SensorType.LIGHT.name())
                .value(lightSensor.getLightIntensity())
                .status(SensorStatus.ON.name())
                .farmUnitId(sensorService.getFarmUnitBySensorId(lightSensor.getId()))
                .unit(lightSensor.getUnit())
                .timestamp(lightSensor.getTimestamp().toInstant().atOffset(ZoneOffset.UTC))
                .build();
    }

    public IotSensorProcessedDto mapToSensorProcessedDto(PhSoilSensor phSoilSensor){
        return IotSensorProcessedDto.builder()
                .sensorId(phSoilSensor.getId())
                .type(SensorType.SOIL_PH.name())
                .value(phSoilSensor.getPhLevel())
                .status(SensorStatus.ON.name())
                .farmUnitId(sensorService.getFarmUnitBySensorId(phSoilSensor.getId()))
                .unit(phSoilSensor.getUnit())
                .timestamp(phSoilSensor.getTimestamp().toInstant().atOffset(ZoneOffset.UTC))
                .build();
    }

    public IotSensorProcessedDto mapToSensorProcessedDto(SoilHumiditySensor soilHumiditySensor){
        return IotSensorProcessedDto.builder()
                .sensorId(soilHumiditySensor.getId())
                .type(SensorType.SOIL_HUMIDITY.name())
                .value(soilHumiditySensor.getHumidity())
                .status(SensorStatus.ON.name())
                .farmUnitId(sensorService.getFarmUnitBySensorId(soilHumiditySensor.getId()))
                .unit(soilHumiditySensor.getUnit())
                .timestamp(soilHumiditySensor.getTimestamp().toInstant().atOffset(ZoneOffset.UTC))
                .build();
    }

    public IotSensorProcessedDto mapToSensorProcessedDto(TemperatureSensor temperatureSensor){
        return IotSensorProcessedDto.builder()
                .sensorId(temperatureSensor.getId())
                .type(SensorType.TEMPERATURE.name())
                .value(temperatureSensor.getTemperature())
                .status(SensorStatus.ON.name())
                .farmUnitId(sensorService.getFarmUnitBySensorId(temperatureSensor.getId()))
                .unit(temperatureSensor.getUnit())
                .timestamp(temperatureSensor.getTimestamp().toInstant().atOffset(ZoneOffset.UTC))
                .build();
    }
}
