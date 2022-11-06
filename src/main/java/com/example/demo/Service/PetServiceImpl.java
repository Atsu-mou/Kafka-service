package com.example.demo.Service;

import com.example.demo.Model.Pet;
import com.example.demo.Model.PetFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    KafkaTemplate<String,Object> kafkaTemplate;
    RestTemplate restTemplate;

    @Value("${request.save}")
    String saveRequestUrl;

    @Autowired
    public PetServiceImpl(KafkaTemplate<String, Object> kafkaTemplate , RestTemplate restTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.restTemplate = restTemplate;
    }

    @Override
    public void kafkaSend(String topicName,Pet pet) {
        kafkaTemplate.send(topicName,pet);
    }

    public void saveRequest(Pet pet){

        PetFull petFull = PetFull.builder().gender(pet.getGender()).name(pet.getName()).age(pet.getAge()).animal(pet.getAnimal()).build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PetFull> request = new HttpEntity<>(petFull, headers);
        restTemplate.exchange(saveRequestUrl,HttpMethod.POST,request, Void.class);
    }


}
