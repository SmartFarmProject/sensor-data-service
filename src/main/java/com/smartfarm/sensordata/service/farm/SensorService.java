package com.smartfarm.sensordata.service.farm;

import com.smartfarm.sensordata.service.farm.model.SensorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorService {

    private final RedisTemplate<String, SensorDTO> redisTemplate;
    private final SensorServiceRestTemplate sensorServiceRestTemplate;

    @PostConstruct
    public void populateCache() {
        List<SensorDTO> sensorConfigs = sensorServiceRestTemplate.getSensors();
        if (sensorConfigs != null && !sensorConfigs.isEmpty()) {
            Map<String, SensorDTO> sensorConfigMap = sensorConfigs.stream().collect(Collectors.toMap(SensorDTO::getPhysicalId, Function.identity()));
            redisTemplate.opsForValue().multiSet(sensorConfigMap);
            log.info("Sensor data have been populated to Redis cache");
        }
    }

    public String getFarmUnitBySensorId(String sensorId) {
        SensorDTO sensorConfig = redisTemplate.opsForValue().get(sensorId);
        if (sensorConfig == null) {
            // query the external service to retrieve the configuration
            sensorConfig = sensorServiceRestTemplate.getSensorByExternalId(sensorId);
            if (sensorConfig != null) {
                // store the configuration in the cache
                redisTemplate.opsForValue().set(sensorId, sensorConfig);
            }
        }
        return Optional.ofNullable(sensorConfig).map(SensorDTO::getUnitId).orElse(null);
    }
}
