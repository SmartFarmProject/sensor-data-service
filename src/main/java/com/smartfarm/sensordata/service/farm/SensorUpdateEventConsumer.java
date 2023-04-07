package com.smartfarm.sensordata.service.farm;

import com.smartfarm.sensordata.service.farm.event.SensorEvent;
import com.smartfarm.sensordata.service.farm.model.SensorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.smartfarm.sensordata.config.KafkaConsumerConfig.GROUP_ID;
import static com.smartfarm.sensordata.config.KafkaConsumerConfig.SENSOR_CONFIGURATION_UPDATE_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorUpdateEventConsumer {

    private final RedisTemplate<String, SensorDTO> redisTemplate;

    @KafkaListener(topics = SENSOR_CONFIGURATION_UPDATE_TOPIC, groupId = GROUP_ID, containerFactory = "generalKafkaListenerContainerFactory")
    public void update(SensorEvent message) {
        SensorDTO sensorDTO = message.getSensorDTO();
        log.info("Received event for {} of sensor with id {}, physicalId {}", message.getEventType(), sensorDTO.getId(), sensorDTO.getPhysicalId());
        if(message.getEventType().equals("CREATED") || message.getEventType().equals("UPDATED")) {
            redisTemplate.opsForValue().set(sensorDTO.getPhysicalId(), sensorDTO);
        }
        if(message.getEventType().equals("DELETED")) {
            redisTemplate.opsForValue().getAndDelete(sensorDTO.getPhysicalId());
        }
    }
}
