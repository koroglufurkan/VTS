package com.vts.sms.service;
import com.vts.sms.data.Ship;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
@DataMongoTest
public class ControllerTests {
    @Value("http://localhost:${server.port}")
    private String host;
    private final RestTemplate restTemplate;
    public ControllerTests() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        this.restTemplate = restTemplateBuilder.build();
    }

    @Test
    void createShip_shipReturned() {
        //Arrange
        Ship shipToCreate = Ship.builder().name("createTestShip").build();
        ResponseEntity<Ship> responseEntity;
        RequestEntity<Ship> requestEntity = new RequestEntity<>(shipToCreate, HttpMethod.POST, URI.create(host+"/ships") );
        //Act
        responseEntity = restTemplate.exchange(requestEntity, Ship.class);
        Ship shipReturned = responseEntity.getBody();
        //Assert
        Assertions.assertThat(shipReturned).isNotNull();
        Assertions.assertThat(shipReturned.getName()).isEqualTo("createTestShip");
        Assertions.assertThat(shipReturned.getId()).isNotNull();

    }
}

