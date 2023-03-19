package com.smartfarm.sensordata.service.farm;

import com.smartfarm.sensordata.service.farm.model.SensorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorServiceRestTemplate {

    public static final String SENSORS_PATH = "/sensors";
    private final RestTemplate restTemplate;
    @Value(value = "${smart.farm.farmdata-service-host}")
    private String farmDataServiceHost;

    public List<SensorDTO> getSensors() {
        try {
            log.info("Request all sensors call");
            ResponseEntity<List<SensorDTO>> responseEntity = restTemplate
                    .exchange(farmDataServiceHost + SENSORS_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            return Optional.ofNullable(responseEntity.getBody()).orElse(emptyList());
        } catch (Exception e) {
            log.error("There is an exception during calling " + farmDataServiceHost + SENSORS_PATH);
            return emptyList();
        }
    }

    public SensorDTO getSensorByExternalId(String sensorId) {
        try {
            log.info("Request sensor by id call");
            ResponseEntity<SensorDTO> responseEntity = restTemplate
                    .exchange(farmDataServiceHost + SENSORS_PATH, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    }, sensorId);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error("There is an exception during calling " + farmDataServiceHost + SENSORS_PATH);
            return null;
        }
    }
}
