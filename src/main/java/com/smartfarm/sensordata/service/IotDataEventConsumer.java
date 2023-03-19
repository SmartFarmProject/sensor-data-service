package com.smartfarm.sensordata.service;

import com.smartfarm.sensordata.domain.IotSensorEvent;
import com.smartfarm.sensordata.mapper.IoTSensorProcessedDtoMapper;
import com.smartfarm.sensordata.mapper.IotSensorEventMapper;
import com.smartfarm.sensordata.model.*;
import com.smartfarm.sensordata.repository.IotSensorEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.smartfarm.sensordata.config.KafkaConsumerConfig.GROUP_ID;
import static com.smartfarm.sensordata.config.KafkaConsumerConfig.IOT_SENSOR_DATA_TOPIC;

@Slf4j
@Component
@KafkaListener(topics = IOT_SENSOR_DATA_TOPIC, groupId = GROUP_ID) //containerFactory = "multiTypeKafkaListenerContainerFactory"
@RequiredArgsConstructor
public class IotDataEventConsumer {

    private final IotProcessedDataProducer iotProcessedDataProducer;
    private final IotSensorEventRepository iotSensorEventRepository;
    private final IoTSensorProcessedDtoMapper ioTSensorProcessedDtoMapper;

    @KafkaHandler
    public void airHumidity(AirHumiditySensor message) {
        IotSensorProcessedDto iotSensorProcessedDto = ioTSensorProcessedDtoMapper.mapToSensorProcessedDto(message);
        iotProcessedDataProducer.sendMessage(iotSensorProcessedDto);
        IotSensorEvent iotSensorEvent = IotSensorEventMapper.iotSensorProcessedDtoToSensorEvent(iotSensorProcessedDto);
        iotSensorEventRepository.save(iotSensorEvent).subscribe();
        log.info("AirHumidity sensor event was saved: " + iotSensorEvent);
    }

    @KafkaHandler
    public void light(LightSensor message) {
        IotSensorProcessedDto iotSensorProcessedDto = ioTSensorProcessedDtoMapper.mapToSensorProcessedDto(message);
        iotProcessedDataProducer.sendMessage(iotSensorProcessedDto);
        IotSensorEvent iotSensorEvent = IotSensorEventMapper.iotSensorProcessedDtoToSensorEvent(iotSensorProcessedDto);
        iotSensorEventRepository.save(iotSensorEvent).subscribe();
        log.info("Light sensor event was saved: " + iotSensorEvent);
    }

    @KafkaHandler
    public void soilPh(PhSoilSensor message) {
        IotSensorProcessedDto iotSensorProcessedDto = ioTSensorProcessedDtoMapper.mapToSensorProcessedDto(message);
        iotProcessedDataProducer.sendMessage(iotSensorProcessedDto);
        IotSensorEvent iotSensorEvent = IotSensorEventMapper.iotSensorProcessedDtoToSensorEvent(iotSensorProcessedDto);
        iotSensorEventRepository.save(iotSensorEvent).subscribe();
        log.info("SoilPh sensor event was saved: " + iotSensorEvent);
    }

    @KafkaHandler
    public void soilHumidity(SoilHumiditySensor message) {
        IotSensorProcessedDto iotSensorProcessedDto = ioTSensorProcessedDtoMapper.mapToSensorProcessedDto(message);
        iotProcessedDataProducer.sendMessage(iotSensorProcessedDto);
        IotSensorEvent iotSensorEvent = IotSensorEventMapper.iotSensorProcessedDtoToSensorEvent(iotSensorProcessedDto);
        iotSensorEventRepository.save(iotSensorEvent).subscribe();
        log.info("SoilHumidity sensor event was saved: " + iotSensorEvent);
    }

    @KafkaHandler
    public void temperature(TemperatureSensor message) {
        IotSensorProcessedDto iotSensorProcessedDto = ioTSensorProcessedDtoMapper.mapToSensorProcessedDto(message);
        iotProcessedDataProducer.sendMessage(iotSensorProcessedDto);
        IotSensorEvent iotSensorEvent = IotSensorEventMapper.iotSensorProcessedDtoToSensorEvent(iotSensorProcessedDto);
        iotSensorEventRepository.save(iotSensorEvent).subscribe();
        log.info("Temperature sensor event was saved: " + iotSensorEvent);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Unknown type event received: " + object);
    }
}
