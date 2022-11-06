package com.example.demo.Kafka;

import com.example.demo.Model.Pet;
import com.example.demo.Service.PetServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    public KafkaConsumer(PetServiceImpl petService, ObjectMapper objectMapper) {
        this.petService = petService;
        this.objectMapper = objectMapper;
    }
    PetServiceImpl petService;
    ObjectMapper objectMapper;

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String consumedMessage) {
        try {
            Pet pet = objectMapper.readValue(consumedMessage,Pet.class);
            petService.saveRequest(pet);
        }catch (Exception e){
            log.error(e.getMessage());
        }}
}
