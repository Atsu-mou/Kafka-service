package com.example.demo.Controller;

import com.example.demo.Configuration.KafkaConfiguration;
import com.example.demo.Model.Pet;
import com.example.demo.Service.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class Controller {

    private final PetServiceImpl petServiceImpl;

    @Autowired
    public Controller(KafkaConfiguration config, PetServiceImpl petServiceImpl){
        this.petServiceImpl = petServiceImpl;
    }


    @PostMapping(path = "/send/foo",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> sendFoo(@RequestBody Pet pet) {
        petServiceImpl.kafkaSend("topic1",pet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
