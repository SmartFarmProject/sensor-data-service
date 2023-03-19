package com.smartfarm.sensordata.service;

import com.smartfarm.sensordata.model.IotSensorProcessedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.smartfarm.sensordata.config.KafkaProducerConfig.PROCESSED_SENSOR_DATA_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class IotProcessedDataProducer {

    private final KafkaTemplate<String, IotSensorProcessedDto> kafkaTemplate;

    public void sendMessage(IotSensorProcessedDto msg) {
        kafkaTemplate.send(PROCESSED_SENSOR_DATA_TOPIC, msg);
    }
}
